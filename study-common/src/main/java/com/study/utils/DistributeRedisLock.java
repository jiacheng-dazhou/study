package com.study.utils;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class DistributeRedisLock implements Lock {

    private RedisUtil redisUtil;

    private String lockName;

    private String uuid;

    private Long expire = 30L;

    public DistributeRedisLock(RedisUtil redisUtil, String lockName, String uuid) {
        this.redisUtil = redisUtil;
        this.lockName = lockName;
        this.uuid = uuid + ":" + Thread.currentThread().getId();
    }

    @Override
    public void lock() {
        this.tryLock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            return this.tryLock(-1L,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (time != -1) {
            this.expire = unit.toSeconds(time);
        }
        String script = "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 " +
                "then "+
                "   redis.call('hincrby',KEYS[1],ARGV[1],1) " +
                "   redis.call('expire',KEYS[1],ARGV[2]) " +
                "   return 1 " +
                "else " +
                "   return 0 " +
                "end";
        while (!redisUtil.executeLuaScript(script, Arrays.asList(lockName),uuid,expire)){
            Thread.sleep(50);
        }
        this.renewExpire();
        return true;
    }

    @Override
    public void unlock() {
        String script = "if redis.call('hexists',KEYS[1],ARGV[1]) == 0 " +
                "then " +
                "   return nil " +
                "elseif redis.call('hincrby',KEYS[1],ARGV[1],-1) == 0 " +
                "   then return redis.call('del',KEYS[1]) " +
                "else " +
                "   return 0 " +
                "end";
        Long flag = redisUtil.executeLuaScriptReturnLong(script, Arrays.asList(lockName), uuid);
        if (flag == null) {
            throw new IllegalMonitorStateException("this lock doesn‘t belong to you");
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private void renewExpire(){
        String script = "if redis.call('hexists',KEYS[1],ARGV[1]) == 1 " +
                "then " +
                "   return redis.call('expire',KEYS[1],ARGV[2]) " +
                "else " +
                "   return 0 " +
                "end";
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(redisUtil.executeLuaScript(script,Arrays.asList(lockName),uuid,expire)){
                    renewExpire();
                }
            }
        },this.expire*1000/3,this.expire*1000/3);
    }
}
