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

package games.rednblack.puremvc.util;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * Utility class that stores notification interests for {@link games.rednblack.puremvc.interfaces.IMediator} objects.
 * Based on {@link Array}, designed to be used in {@link com.badlogic.gdx.utils.Pool}.
 *
 * @author fgnm
 */
public class Interests extends Array<String> implements Pool.Poolable {

    public Interests() {
        super(String.class);
    }

    public Interests(int capacity) {
        super(capacity);
    }

    public Interests(boolean ordered, int capacity) {
        super(ordered, capacity, String.class);
    }

    public Interests(Interests array) {
        super(array);
    }

    public Interests(String[] array) {
        super(array);
    }

    public Interests(boolean ordered, String[] array, int start, int count) {
        super(ordered, array, start, count);
    }

    @Override
    public void reset() {
        clear();
    }
}
