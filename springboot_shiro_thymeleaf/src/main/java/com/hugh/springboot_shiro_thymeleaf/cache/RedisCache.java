package com.hugh.springboot_shiro_thymeleaf.cache;

import com.hugh.springboot_shiro_thymeleaf.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义Redis缓存实现
 *
 * Created by hugh on 2021/1/1
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private String cacheName;

    public RedisCache(){}

    public RedisCache(String cacaheName){
        this.cacheName = cacaheName;
    }

    @Override
    public V get(K k) throws CacheException {
        RedisTemplate redisTemplate = getRedisTemplate();
        return (V) redisTemplate.opsForHash().get(this.cacheName, k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.opsForHash().put(this.cacheName, k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return (V) getRedisTemplate().opsForHash().delete(this.cacheName,k.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().opsForHash().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }

    private RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
