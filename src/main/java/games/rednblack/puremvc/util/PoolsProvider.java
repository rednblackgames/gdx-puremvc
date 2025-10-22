package games.rednblack.puremvc.util;

import com.badlogic.gdx.utils.Pool;

public interface PoolsProvider {
    <T> T obtain(Class<T> type);
    void free(Object object);
    <T> Pool<T> get(Class<T> type);
    <T> void set(Pool<T> pool);
}
