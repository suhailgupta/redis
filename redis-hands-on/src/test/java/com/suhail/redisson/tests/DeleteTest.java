package com.suhail.redisson.tests;

import org.junit.jupiter.api.Test;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RKeys;
import org.redisson.api.RKeysAsync;
import org.redisson.api.options.KeysScanParams;

public class DeleteTest extends BaseTest{

    @Test
    public void deleteAllKeys(){
        RKeys keys = redissonClient.getKeys();
        keys.flushdb();
    }

    @Test
    public void deleteKeysWithPattern(){
        RKeys keys = redissonClient.getKeys();
        for (String key : keys.getKeysByPattern("user:1:*")){
            keys.delete(key);
        }

    }

    @Test
    public void batchDeleteTest(){

        RBatch batch = redissonClient.createBatch();
        RKeysAsync keys = batch.getKeys();
        Iterable<String> userIdKeys = redissonClient.getKeys()
                .getKeys(new KeysScanParams()
                .pattern("user:1:*"));

        for (String key : userIdKeys){
            keys.deleteAsync(key);
        }
        BatchResult<?> result = batch.execute();
        result.getResponses().forEach(System.out::println);

    }



}
