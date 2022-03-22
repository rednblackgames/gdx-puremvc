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

import games.rednblack.puremvc.interfaces.IMediator;
import games.rednblack.puremvc.interfaces.INotification;
import games.rednblack.puremvc.util.Interests;


/**
 *  Implementation of a standard PureMVC Mediator.
 * @param <T> ViewComponent type of the current Mediator
 * @author fgnm
 */
public class Mediator<T> extends Notifier implements IMediator {

    private final String name;
    protected T viewComponent;

    public Mediator(String name, T component) {
        viewComponent = component;
        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    public void setViewComponent(T viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Override
    public void listNotificationInterests(Interests interests) {

    }

    @Override
    public void handleNotification(INotification notification) {

    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }

    @Override
    public T getViewComponent() {
        return viewComponent;
    }

    @Override
    public final void notify(INotification notification) {
        handleNotification(notification);
    }
}
