package com.suhail.redisson.tests;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.options.KeysScanParams;

public class AllKeysTest extends BaseTest{

    @Test
    public void allKeysTest(){
        RKeys keys = redissonClient.getKeys();
        Iterable<String> allKeys = keys.getKeys();

        for (String key : allKeys){
            System.out.println("Key: "+ key + "\n");
        }
    }

    @Test
    public void existsKey(){
        RBucket<String> bucket = redissonClient.getBucket("user:1:session");
        boolean exists = bucket.isExists();
        System.out.println("Key Exists: "+ exists);

    }

    @Test
    public void existsKeyWithPattern(){
        Iterable<String> keysIterable = redissonClient.getKeys()
                .getKeysByPattern("user:1:*");

        boolean exists = keysIterable.iterator().hasNext();
        System.out.println("Key Exists: "+ exists);
    }

    @Test
    public void countKeysExistsTest(){
        RKeys keys = redissonClient.getKeys();

        long count = keys.countExists(
                "key1",
                "key2",
                "user:1:session"
        );
        System.out.println("Total Keys: "+ count);
    }

    @Test
    public void allKeysWithPatternTest(){
        RKeys keys = redissonClient.getKeys();
        Iterable<String> showKeys = keys
                .getKeys(new KeysScanParams().pattern("show:*"));

        for (String key : showKeys){
            System.out.println("Show Key: "+ key + "\n");
        }
    }

    @Test
    public void allKeysWithPatternAndCountTest(){
        RKeys keys = redissonClient.getKeys();
        Iterable<String> showKeys = keys
                .getKeys(new KeysScanParams()
                        .pattern("show:*")
                        .limit(2));

        for (String key : showKeys){
            System.out.println("Show Key: "+ key + "\n");
        }
    }
}
