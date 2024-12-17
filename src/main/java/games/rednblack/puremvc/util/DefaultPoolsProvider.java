package games.rednblack.puremvc.util;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class DefaultPoolsProvider implements PoolsProvider {
    @Override
    public <T> T obtain(Class<T> type) {
        return Pools.obtain(type);
    }

    @Override
    public void free(Object object) {
        Pools.free(object);
    }

    @Override
    public void freeAll(Array objects) {
        Pools.freeAll(objects);
    }

    @Override
    public void freeAll(Array objects, boolean samePool) {
        Pools.freeAll(objects, samePool);
    }

    @Override
    public <T> Pool<T> get(Class<T> type) {
        return Pools.get(type);
    }

    @Override
    public <T> Pool<T> get(Class<T> type, int max) {
        return Pools.get(type, max);
    }

    @Override
    public <T> void set(Class<T> type, Pool<T> pool) {
        Pools.set(type, pool);
    }
}
