package games.rednblack.puremvc.util;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public interface PoolsProvider {
    <T> T obtain(Class<T> type);
    void free(Object object);
    void freeAll(Array objects);
    void freeAll(Array objects, boolean samePool);
    <T> Pool<T> get(Class<T> type);
    <T> Pool<T> get(Class<T> type, int max);
    <T> void set(Class<T> type, Pool<T> pool);
}
