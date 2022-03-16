package games.rednblack.puremvc;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;

import games.rednblack.puremvc.interfaces.ICommand;
import games.rednblack.puremvc.interfaces.IMediator;
import games.rednblack.puremvc.interfaces.INotifiable;
import games.rednblack.puremvc.interfaces.IProxy;
import games.rednblack.puremvc.util.Interests;

public class Facade {

    private static Facade instance;

    public synchronized static Facade getInstance() {
        if (instance == null) instance = new Facade();
        return instance;
    }

    public synchronized static void dispose() {
        instance.proxiesMap.clear();
        instance.mediatorsMap.clear();
        for (SnapshotArray<INotifiable> observers : instance.observersMap.values()) {
            observers.clear();
        }
        instance.observersMap.clear();
        instance = null;
        System.gc();
    }

    private final ObjectMap<String, SnapshotArray<INotifiable>> observersMap;
    private final ObjectMap<String, IMediator> mediatorsMap;
    private final ObjectMap<String, IProxy<?>> proxiesMap;

    private Facade() {
        observersMap = new ObjectMap<>();
        mediatorsMap = new ObjectMap<>();
        proxiesMap = new ObjectMap<>();
    }

    public void registerMediator(IMediator mediator) {
        mediatorsMap.put(mediator.getName(), mediator);
        mediator.onRegister();

        Interests interests = Pools.obtain(Interests.class);
        mediator.listNotificationInterests(interests);
        if (interests.size == 0) {
            Pools.free(interests);
            return;
        }

        for (String notification : interests) {
            SnapshotArray<INotifiable> observers = observersMap.get(notification);
            if (observers == null) {
                observers =  new SnapshotArray<>(INotifiable.class);
                observersMap.put(notification, observers);
            }

            observers.add(mediator);
        }

        Pools.free(interests);
    }

    public <T extends IMediator> T retrieveMediator(String name) {
        return (T) mediatorsMap.get(name);
    }

    public void removeMediator(String mediatorName) {
        IMediator mediator = mediatorsMap.get(mediatorName);
        mediatorsMap.remove(mediatorName);

        if (mediator == null)
            return;

        mediator.onRemove();

        Interests interests = Pools.obtain(Interests.class);
        mediator.listNotificationInterests(interests);
        if (interests.size == 0) {
            Pools.free(interests);
            return;
        }

        for (String notification : interests) {
            SnapshotArray<INotifiable> observers = observersMap.get(notification);
            observers.removeValue(mediator, true);
        }

        Pools.free(interests);
    }

    public void registerCommand(String notification, ICommand command) {
        SnapshotArray<INotifiable> observers = observersMap.get(notification);
        if (observers == null) {
            observers =  new SnapshotArray<>(INotifiable.class);
            observersMap.put(notification, observers);
        }
        observers.add(command);
        command.onRegister();
    }

    public void removeCommand(String notification) {
        SnapshotArray<INotifiable> observers = observersMap.get(notification);
        if (observers == null)
            return;

        observersMap.remove(notification);

        INotifiable[] obs = observers.begin();
        for (int i = 0; i < observers.size; i++) {
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

    public void removeProxy(String proxyName) {
        IProxy<?> proxy = proxiesMap.get(proxyName);
        proxiesMap.remove(proxyName);
        proxy.onRemove();
    }

    public void sendNotification(String notification) {
        sendNotification(notification, null);
    }

    public void sendNotification(String notification, Object body) {
        sendNotification(notification, body, null);
    }

    public void sendNotification(String notification, Object body, String type) {
        Notification n = Pools.obtain(Notification.class);
        n.setName(notification);
        n.setBody(body);
        n.setType(type);

        SnapshotArray<INotifiable> observers = observersMap.get(notification);
        if (observers != null) {
            INotifiable[] obs = observers.begin();
            for (int i = 0; i < observers.size; i++) {
                INotifiable observer = obs[i];

                if (observer instanceof IMediator)
                    ((IMediator) observer).handleNotification(n);
                if (observer instanceof ICommand)
                    ((ICommand) observer).execute(n);
            }
            observers.end();
        }

        Pools.free(n);
    }
}