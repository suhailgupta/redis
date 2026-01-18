package com.suhail.redisson.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.redisson.api.RedissonClient;

@TestInstance( TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    private RedissonConfig redissonConfig = new RedissonConfig();

    protected RedissonClient redissonClient;

    @BeforeAll
    public void setClientId(){
        redissonClient = redissonConfig.getRedissonClient();
    }

    public void shutdown(){
        redissonClient.shutdown();
    }

    protected void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
