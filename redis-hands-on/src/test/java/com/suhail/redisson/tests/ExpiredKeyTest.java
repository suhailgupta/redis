package com.suhail.redisson.tests;

import org.junit.jupiter.api.Test;
import org.redisson.api.ExpiredObjectListener;
import org.redisson.api.RBucket;
import org.redisson.client.codec.StringCodec;

import java.time.Duration;

public class ExpiredKeyTest extends BaseTest{

    @Test
    public void keyValueExpireTest(){

        RBucket<String> bucket = redissonClient.getBucket("student:1:name",
                StringCodec.INSTANCE);

        bucket.set("suhail", Duration.ofSeconds(30));
        String value = bucket.get();
        System.out.println("Value from Redis: "+ value);

    }

    @Test
    public void keyValueExpireExtendTest(){

        RBucket<String> bucket = redissonClient.getBucket("student:1:name",
                StringCodec.INSTANCE);

        bucket.set("suhail", Duration.ofSeconds(30));
        String value = bucket.get();
        System.out.println("Value from Redis: "+ value);

         sleep(5000);

         boolean updated = bucket.expire(Duration.ofSeconds(60));
        System.out.println("Expire Updated: "+ updated);

        // access remaining TTL
        long ttl = bucket.remainTimeToLive();
        System.out.println("TTL: "+ ttl);
    }

    @Test
    public void expiredEventTest(){
        RBucket<String> bucket = redissonClient.getBucket("student:1:name",
                StringCodec.INSTANCE);

        bucket.set("suhail", Duration.ofSeconds(10));
        String value = bucket.get();
        System.out.println("Value from Redis: "+ value);

        // add expiration listener
        int listenerId = bucket.addListener(new ExpiredObjectListener() {
            @Override
            public void onExpired(String s) {
                System.out.println("Key Expired: "+ s);
            }
        });
        // sleep for sometime i.e. 11 seconds
        sleep(11_000);

        bucket.removeListener(listenerId);

    }
}
