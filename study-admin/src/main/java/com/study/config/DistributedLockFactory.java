package com.study.config;

import com.study.utils.DistributeRedisLock;
import com.study.utils.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class DistributedLockFactory {

    @Resource
    private RedisUtil redisUtil;

    private String uuid;

    public DistributedLockFactory() {
        this.uuid = UUID.randomUUID().toString();
    }

    public DistributeRedisLock getRedisLock(String lockName){
        return new DistributeRedisLock(redisUtil,lockName,uuid);
    }
}
