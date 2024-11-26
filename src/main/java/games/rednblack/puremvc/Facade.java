/*******************************************************************************
 * Copyright 2022 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package games.rednblack.puremvc;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;

import games.rednblack.puremvc.commands.ReflectionCommand;
import games.rednblack.puremvc.interfaces.*;
import games.rednblack.puremvc.util.Interests;

/**
 * Facade Singleton implementation, handles Models, Views and Controls as internal fields.
 *
 * @author fgnm
 */
public class Facade implements INotifier {

    private static Facade instance;

    public synchronized static Facade getInstance() {
        if (instance == null) instance = new Facade();
        return instance;
    }

    public synchronized static void dispose() {
        if (instance == null) return;

        instance.proxiesMap.clear();
        instance.mediatorsMap.clear();
        instance.commandsMap.clear();
        for (SnapshotArray<INotifiable> observers : instance.observersMap.values()) {
            observers.clear();
        }
        instance.observersMap.clear();
        instance = null;
        System.gc();
    }

    private final ObjectMap<String, SnapshotArray<INotifiable>> observersMap;
    private final ObjectMap<String, IMediator> mediatorsMap; //View
    private final ObjectMap<String, IProxy<?>> proxiesMap; //Model
    private final ObjectMap<String, ICommand> commandsMap; //Controller

    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;

    protected Facade() {
        observersMap = new ObjectMap<>();
        mediatorsMap = new ObjectMap<>();
        proxiesMap = new ObjectMap<>();
        commandsMap = new ObjectMap<>();
    }

    public void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public void registerMediator(IMediator mediator) {
        IMediator oldMediator = mediatorsMap.get(mediator.getName());

        mediatorsMap.put(mediator.getName(), mediator);

        Interests interests = Pools.obtain(Interests.class);
        mediator.listNotificationInterests(interests);
        if (interests.size == 0) {
            Pools.free(interests);
            mediator.onRegister();
            return;
        }

        for (String notification : interests) {
            SnapshotArray<INotifiable> observers = observersMap.get(notification);
            if (observers == null) {
                observers =  new SnapshotArray<>(INotifiable.class);
                observersMap.put(notification, observers);
            }
            if (oldMediator != null)
                observers.removeValue(oldMediator, true);
            observers.add(mediator);
        }

        Pools.free(interests);
        mediator.onRegister();
    }

    public <T extends IMediator> T retrieveMediator(String name) {
        return (T) mediatorsMap.get(name);
    }

    public IMediator removeMediator(String mediatorName) {
        IMediator mediator = mediatorsMap.get(mediatorName);
        mediatorsMap.remove(mediatorName);

        if (mediator == null)
            return null;

        Interests interests = Pools.obtain(Interests.class);
        mediator.listNotificationInterests(interests);
        if (interests.size == 0) {
            Pools.free(interests);
            return mediator;
        }

        for (String notification : interests) {
            SnapshotArray<INotifiable> observers = observersMap.get(notification);
            observers.removeValue(mediator, true);
        }

        Pools.free(interests);
        mediator.onRemove();
        return mediator;
    }

    public void registerCommand(String notification, ICommand command) {
        SnapshotArray<INotifiable> observers = observersMap.get(notification);
        if (observers == null) {
            observers =  new SnapshotArray<>(INotifiable.class);
            observersMap.put(notification, observers);
        }
        observers.add(command);

        commandsMap.put(notification, command);
        command.onRegister();
    }

    public void registerCommand(String notification, Class<? extends ICommand> commandClass) {
        registerCommand(notification, new ReflectionCommand(commandClass));
    }

    public <T extends ICommand> T retrieveCommand(String notification) {
        return (T) commandsMap.get(notification);
    }

    public void removeCommand(String notification) {
        commandsMap.remove(notification);

        SnapshotArray<INotifiable> observers = observersMap.get(notification);
        if (observers == null)
            return;

        observersMap.remove(notification);

        INotifiable[] obs = observers.begin();
        for (int i = 0, n = observers.size; i < n; i++) {
            obs[i].onRemove();
        }
        observers.end();
    }

    public void registerProxy(IProxy<?> proxy) {
        proxiesMap.put(proxy.getName(), proxy);
        proxy.onRegister();
    }

    public <T extends IProxy<?>> T retrieveProxy(String name) {
        return (T) proxiesMap.get(name);
    }

    public <T extends IProxy<?>> T removeProxy(String proxyName) {
        IProxy<?> proxy = proxiesMap.get(proxyName);
        if (proxy == null) return null;

        proxiesMap.remove(proxyName);
        proxy.onRemove();
        return (T) proxy;
    }

    @Override
    public void sendNotification(String notification) {
        sendNotification(notification, null);
    }

    @Override
    public void sendNotification(String notification, Object body) {
        sendNotification(notification, body, null);
    }

    @Override
    public void sendNotification(String notification, Object body, String type) {
        Notification n = Pools.obtain(Notification.class);
        n.setName(notification);
        n.setBody(body);
        n.setType(type);

        sendNotification(n);

        Pools.free(n);
    }

    public void sendNotification(INotification notification) {
        SnapshotArray<INotifiable> observers = observersMap.get(notification.getName());
        if (observers != null) {
            INotifiable[] obs = observers.begin();
            for (int i = 0, n = observers.size; i < n; i++) {
                INotifiable observer = obs[i];
                try {
                    observer.notify(notification);
                } catch (Exception e) {
                    if (uncaughtExceptionHandler != null)
                        uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e);
                    else
                        throw e;
                }
            }
            observers.end();
        }
    }

    public void executeCommand(INotification notification) {
        ICommand command = commandsMap.get(notification.getName());
        if (command == null) return;

        command.execute(notification);
    }

    public boolean hasCommand(String name) {
        ICommand command = commandsMap.get(name);
        return command != null;
    }

    public boolean hasProxy(String name) {
        IProxy<?> proxy = proxiesMap.get(name);
        return proxy != null;
    }

    public boolean hasMediator(String name) {
        IMediator mediator = mediatorsMap.get(name);
        return mediator != null;
    }
}