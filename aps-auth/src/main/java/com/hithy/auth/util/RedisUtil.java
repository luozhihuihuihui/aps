package com.hithy.auth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * JedisUtil(推荐存Byte数组，存Json字符串效率更慢)
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 获取redis键值-object
     *
     * @param key
     * @return java.lang.Object
     * @author dolyw.com
     * @date 2018/9/4 15:47
     */
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置redis键值-object
     *
     * @param key
     * @param value
     * @return java.lang.String
     * @author dolyw.com
     * @date 2018/9/4 15:49
     */
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置redis键值-object-expiretime
     *
     * @param key
     * @param value
     * @param expiretime
     * @return java.lang.String
     * @author dolyw.com
     * @date 2018/9/4 15:50
     */
    public void setObject(String key, Object value, int expiretime) {
        redisTemplate.opsForValue().set(key, value, expiretime,TimeUnit.SECONDS);
    }


    /**
     * 删除key
     *
     * @param key
     * @return java.lang.Long
     * @author Wang926454
     * @date 2018/9/4 15:50
     */
    public boolean delKey(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * key是否存在
     *
     * @param key
     * @return java.lang.Boolean
     * @author Wang926454
     * @date 2018/9/4 15:51
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    public void clear() {
        redisTemplate.delete(keys());
    }

    public int size() {
        return keys().size();
    }

    public Set keys() {
        return redisTemplate.keys("*");
    }

    public List<String> values() {
        Set<String> keys = redisTemplate.keys("*");
        List<String> values=new ArrayList<>();
        keys.stream().forEach(key->values.add(redisTemplate.opsForValue().get(key).toString()));
        return values;
    }
}
