package com.study.stock.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.study.config.DistributedLockFactory;
import com.study.entity.StockEntity;
import com.study.mapper.stock.StockMapper;
import com.study.stock.service.StockService;
import com.study.utils.DistributeRedisLock;
import com.study.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
//@Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockServiceImpl implements StockService {

//    ReentrantLock lock = new ReentrantLock();

    @Resource
    private StockMapper stockMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private DistributedLockFactory distributedLockFactory;

    //mysql悲观锁
    @Transactional
    public void deduct1() {
//        lock.lock();
        try {
            StockEntity stockEntity = stockMapper.selectByCondition("1001");
            if (stockEntity != null && stockEntity.getCount() >= 1) {
                stockEntity.setCount(stockEntity.getCount() - 1);
                stockMapper.updateById(stockEntity);
                System.out.println("库存余量：" + stockEntity.getCount());
            }
        } finally {
//            lock.unlock();
        }
    }

    //mysql乐观锁
    @Override
    public void deduct2() {
        StockEntity stockEntity = stockMapper.selectOne(new QueryWrapper<StockEntity>()
                .eq("product_code", "1001"));
        if (stockEntity != null && stockEntity.getCount() >= 1) {
            stockEntity.setCount(stockEntity.getCount() - 1);
            Integer version = stockEntity.getVersion();
            stockEntity.setVersion(version + 1);
            int update = stockMapper.update(stockEntity, new UpdateWrapper<StockEntity>()
                    .eq("product_code", "1001")
                    .eq("version", version));
            if (update == 0) {
                deduct1();
            }
            System.out.println("库存余量：" + stockEntity.getCount());
        }
    }

    //redis分布式可重入锁
    @Override
    public void deduct() {
        DistributeRedisLock redisLock = distributedLockFactory.getRedisLock("lock");
        redisLock.lock();
        try {
            StockEntity stockEntity = stockMapper.selectOne(new QueryWrapper<StockEntity>()
                    .eq("product_code", "1002"));
            if (stockEntity != null && stockEntity.getCount() >= 1) {
                stockEntity.setCount(stockEntity.getCount() - 1);
                stockMapper.updateById(stockEntity);
                System.out.println("库存余量：" + stockEntity.getCount());
            }
            test();
        } finally {
            redisLock.unlock();
        }
    }

    private void test() {
        DistributeRedisLock redisLock = distributedLockFactory.getRedisLock("lock");
        redisLock.lock();
        System.out.println("测试可重入锁...");
        redisLock.unlock();
    }

    //redis分布式锁
    @Override
    public void deduct3() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        while (!redisUtil.setIfAbsent("lock", uuid, 10L)) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        renewExpire("lock",uuid,10L);
        try {
            StockEntity stockEntity = stockMapper.selectOne(new QueryWrapper<StockEntity>()
                    .eq("product_code", "1002"));
            if (stockEntity != null && stockEntity.getCount() >= 1) {
                stockEntity.setCount(stockEntity.getCount() - 1);
                stockMapper.updateById(stockEntity);
                System.out.println("库存余量：" + stockEntity.getCount());
            }
        } finally {
            //这样操作不能保证原子性，这里通过lua脚本实现判断删除一起执行
/*            if (StringUtils.equals((String)redisUtil.get("lock"),uuid)) {
                redisUtil.del("lock");
            }*/
            String script = "if redis.call('get',KEYS[1]) == ARGV[1] then redis.call('del',KEYS[1]) return 1 else return 0 end";
            if (redisUtil.executeLuaScript(script, Arrays.asList("lock"), uuid)) {
                System.out.println("执行lua脚本删除锁成功");
            }
        }
    }

    private void renewExpire(String lockName, String uuid, long expire) {
        String script = "if redis.call('hexists',KEYS[1],ARGV[1]) == 1 " +
                "then " +
                "   return redis.call('expire',KEYS[1],ARGV[2]) " +
                "else " +
                "   return 0 " +
                "end";
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (redisUtil.executeLuaScript(script, Arrays.asList(lockName), uuid, expire)) {
                    renewExpire(lockName, uuid, expire);
                }
            }
        }, expire * 1000 / 3, expire * 1000 / 3);
    }
}
