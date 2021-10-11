package com.hithy.auth.cache;


import com.hithy.auth.util.JwtUtil;
import com.hithy.auth.util.RedisUtil;
import com.hithy.auth.util.common.PropertiesUtil;
import com.hithy.common.constant.ApsConstant;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 重写Shiro的Cache保存读取
 */
@Component
public class ApsCache<K, V> implements Cache<K, V> {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 缓存的key名称获取为shiro:cache:account
     *
     * @param key
     * @return java.lang.String
     * @author dolyw.com
     * @date 2018/9/4 18:33
     */
    private String getKey(Object key) {
        return ApsConstant.Redis.PREFIX_APS_CACHE + JwtUtil.getClaim(key.toString(), ApsConstant.JWT.ACCOUNT);
    }

    /**
     * 获取缓存
     */
    @Override
    public Object get(Object key) throws CacheException {
        return redisUtil.getObject(this.getKey(key));
    }

    /**
     * 保存缓存
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {
        // 读取配置文件，获取Redis的Shiro缓存过期时间
        PropertiesUtil.readProperties("config.properties");
        String shiroCacheExpireTime = PropertiesUtil.getProperty("shiroCacheExpireTime");
        // 设置Redis的Shiro缓存
        redisUtil.setObject(this.getKey(key), value, Integer.parseInt(shiroCacheExpireTime));
        return true;
    }

    /**
     * 移除缓存
     */
    @Override
    public Object remove(Object key) throws CacheException {
       redisUtil.delKey(String.valueOf(key));
        return null;
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
        redisUtil.clear();
    }

    /**
     * 缓存的个数
     */
    @Override
    public int size() {
        return redisUtil.size();
    }

    /**
     * 获取所有的key
     */
    @Override
    public Set keys() {
        return redisUtil.keys();
    }

    /**
     * 获取所有的value
     */
    @Override
    public Collection values() {
       return redisUtil.values();
    }
}
