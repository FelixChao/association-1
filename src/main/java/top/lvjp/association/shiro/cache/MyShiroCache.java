package top.lvjp.association.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;

import java.io.Serializable;

public class MyShiroCache {

    private Cache<Serializable, Session> sessionCache;

    // shiro 的缓存管理器
    public MyShiroCache(CacheManager cacheManager) {
        this.sessionCache = cacheManager.getCache("shiro-activeSessionCache");
    }

    public Cache<Serializable, Session> getSessionCache() {
        return sessionCache;
    }
}
