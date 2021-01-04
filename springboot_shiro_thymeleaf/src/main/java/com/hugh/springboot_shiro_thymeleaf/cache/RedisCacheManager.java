package com.hugh.springboot_shiro_thymeleaf.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * 自定义shiro中的缓存管理器
 *
 * Created by hugh on 2021/1/1
 */
@Component
public class RedisCacheManager implements CacheManager {

    /**
     *
     * @param s: 认证或授权缓存的名称
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new RedisCache<K,V>(s);
    }
}
