package com.suhail.redisson.tests;

import org.junit.jupiter.api.Test;
import org.redisson.api.DeletedObjectListener;
import org.redisson.api.RBucket;
import org.redisson.client.codec.StringCodec;

public class EventListenerTest extends BaseTest{

    @Test
    public void deletedEventTest(){

        RBucket<String> bucket = redissonClient
                .getBucket("user:2:Yemi:payment:method", StringCodec.INSTANCE);


        String value = bucket.get();
        System.out.println("Value from Redis: "+ value);

        // Add delete listener
        int listenerId = bucket.addListener(new DeletedObjectListener() {
            @Override
            public void onDeleted(String s) {
                System.out.println("Key Deleted: " + s);
            }
        });

        // Wait for some time so that redis can invoke the callback.
        sleep(60_000);

        bucket.removeListener(listenerId);

    }

}
