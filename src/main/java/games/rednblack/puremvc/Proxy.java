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

import games.rednblack.puremvc.interfaces.IProxy;

/**
 * Implementation of PureMVC Proxy.
 * @param <T> Data type of the Model object associated to the Proxy
 * @author fgnm
 */
public class Proxy<T> extends Notifier implements IProxy<T> {

    private final String name;
    protected T data;

    public Proxy(String name, T data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }
}
