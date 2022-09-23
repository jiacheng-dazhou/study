package com.study.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public final class RedisUtil {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 自定义锁时间
     * @param key
     * @return
     */
    public boolean setIfAbsent(String key, Integer value, Long time, TimeUnit timeUnit){
        return redisTemplate.opsForValue().setIfAbsent(key,value,time,timeUnit);
    }

    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key,Object value,Long time){
        try {
            redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key){
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setIfAbsent(String key,Object value,Long time){
        try{
            return redisTemplate.opsForValue().setIfAbsent(key,value,time,TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean del(String key){
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean executeLuaScript(String script,List<String> keys,Object ... args){
       return redisTemplate.execute(new DefaultRedisScript<>(script,Boolean.class),keys,args);
    }

    public Long executeLuaScriptReturnLong(String script,List<String> keys,Object ... args){
        return redisTemplate.execute(new DefaultRedisScript<>(script,Long.class),keys,args);
    }
}
