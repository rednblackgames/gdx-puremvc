package games.rednblack.puremvc.util;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.PoolManager;
import games.rednblack.puremvc.Notification;

public class DefaultPoolsProvider implements PoolsProvider {
    PoolManager POOLS = new PoolManager(Interests::new, Notification::new);

    @Override
    public <T> T obtain(Class<T> type) {
        return POOLS.obtain(type);
    }

    @Override
    public void free(Object object) {
        POOLS.free(object);
    }

    @Override
    public <T> Pool<T> get(Class<T> type) {
        return POOLS.getPool(type);
    }

    @Override
    public <T> void set(Pool<T> pool) {
        POOLS.addPool(pool);
    }
}
