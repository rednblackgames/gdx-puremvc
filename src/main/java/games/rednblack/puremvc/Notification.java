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

import com.badlogic.gdx.utils.Pool;

import games.rednblack.puremvc.interfaces.INotification;

/**
 * Implementation of standard PureMVC notification. Ready to be used with {@link com.badlogic.gdx.utils.Pool}
 *
 * @author fgnm
 */
public class Notification implements INotification, Pool.Poolable {

    private String name;
    private Object body;
    private String type;

    public Notification() {
        this(null, null, null);
    }

    public Notification(String name) {
        this(name, null, null);
    }

    public Notification(String name, Object body) {
        this(name, body, null);
    }

    public Notification(String name, Object body, String type) {
        this.name = name;
        this.body = body;
        this.type = type;
    }

    void setBody(Object body) {
        this.body = body;
    }

    void setName(String name) {
        this.name = name;
    }

    void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Object getBody() {
        return body;
    }

    public Notification copy() {
        Notification copy = new Notification();

        copy.setName(name);
        copy.setType(type);
        copy.setBody(body);

        return copy;
    }

    @Override
    public void reset() {
        this.body = null;
        this.name = null;
        this.type = null;
    }
}
