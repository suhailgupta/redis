package com.suhail.redisson.tests;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Objects;

public class RedissonConfig {

    private RedissonClient redissonClient;

    public RedissonClient getRedissonClient(){
        if(Objects.isNull(redissonClient)){
            Config config = new Config();
            config.useSingleServer().setAddress("redis://127.0.0.1:6379");
            redissonClient = Redisson.create(config);
        }
        return redissonClient;
    }

}
