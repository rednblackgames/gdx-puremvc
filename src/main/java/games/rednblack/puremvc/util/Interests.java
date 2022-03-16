package games.rednblack.puremvc.util;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

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
