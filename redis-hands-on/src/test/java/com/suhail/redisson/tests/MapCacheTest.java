package com.suhail.redisson.tests;

import com.suhail.redisson.tests.models.ProductMetadata;
import org.junit.jupiter.api.Test;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.concurrent.TimeUnit;

public class MapCacheTest extends BaseTest{

    @Test
    public void mapCacheTest(){

        TypedJsonJacksonCodec codec =  new TypedJsonJacksonCodec(Integer.class, ProductMetadata.class);
        RMapCache<Integer, ProductMetadata> map = this.redissonClient.getMapCache("products:cache", codec);

        ProductMetadata productMetadata1 = new ProductMetadata(1, "Iphone", "Iphone 16 Pro");
        ProductMetadata productMetadata2 = new ProductMetadata(2, "Samsung", "Samsung S27");

        map.put(1, productMetadata1, 5, TimeUnit.SECONDS);
        map.put(2, productMetadata2, 10, TimeUnit.SECONDS);

        // sleep for 3 seconds
        sleep(3_000);

        System.out.println(map.get(1)); // metadata 1
        System.out.println(map.get(2)); // metadata 2
        // sleep for 3 more seconds
        sleep(3_000);

        System.out.println(map.get(1)); // null
        System.out.println(map.get(2)); // print value

    }
}
