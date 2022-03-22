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

import games.rednblack.puremvc.interfaces.INotifier;

/**
 * Implementation of Notifier in PureMVC.
 *
 * @author fgnm
 */
public class Notifier implements INotifier {
    protected final Facade facade = Facade.getInstance();

    @Override
    public void sendNotification(String notification) {
        facade.sendNotification(notification);
    }

    @Override
    public void sendNotification(String notification, Object body) {
        facade.sendNotification(notification, body);
    }

    @Override
    public void sendNotification(String notification, Object body, String type) {
        facade.sendNotification(notification, body, type);
    }
}
