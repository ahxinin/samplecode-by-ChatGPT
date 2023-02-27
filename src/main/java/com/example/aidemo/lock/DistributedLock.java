package com.example.aidemo.lock;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

/**
 * @description: 分布式锁
 * @date : 2023-02-26
 */
@Component
public class DistributedLock {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public DistributedLock(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public boolean acquire(String lockKey, int expireTime) {
        RedisScript<String> script = new DefaultRedisScript<>(
                "return redis.call('set', KEYS[1], 'locked', 'NX', 'EX', ARGV[1])",
                String.class);
        String result = redisTemplate.execute(script, Collections.singletonList(lockKey), Integer.toString(expireTime));
        System.out.println(result);
        return "OK".equals(result);
    }
    public void release(String lockKey) {
        redisTemplate.delete(lockKey);
    }
}
