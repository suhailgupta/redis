package com.suhail.redisson.tests;

import com.suhail.redisson.tests.models.ProductMetadata;
import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RMap;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.time.Duration;
import java.util.Map;

public class MapTest extends BaseTest{

    @Test
    public void mapTest1(){
        RMap<Object, Object> map = this.redissonClient.getMap("product:1", StringCodec.INSTANCE);

        map.put("id", "1");
        map.put("name", "Iphone");
        map.put("price", "10000");
        map.put("description", "Iphone 17 Pro");
        map.expire(Duration.ofSeconds(10));
    }

    @Test
    public void mapTest2(){
        RMap<Object, Object> map = this.redissonClient.getMap("product:2", StringCodec.INSTANCE);

        Map<String, String> javaMap = Map.of("id", "2",
                "name", "Samsung",
                "price", "20000",
                "description", "Samsung S27");

        map.putAll(javaMap);
    }

    @Test
    public void mapTest3(){
        RMap<Object, Object> map = this.redissonClient.getMap("product:3", StringCodec.INSTANCE);

        map.fastPut("id", "3");
        map.fastPut("name", "Iphone");
        map.fastPut("price", "11000");
        map.fastPut("description", "Iphone 16 Pro");

        Double price = (double)map.get("price");
        //
        price = price + 10d;
        map.fastPut("price", String.valueOf(price));

    }


    @Test
    public void mapTest4(){
        // static info
        // Map<1, ProductMetadata>
        TypedJsonJacksonCodec codec =  new TypedJsonJacksonCodec(Integer.class, ProductMetadata.class);
        RMap<Object, ProductMetadata> map = this.redissonClient.getMap("products:metadata", codec);
        ProductMetadata productMetadata1 = new ProductMetadata(1, "Iphone", "Iphone 16 Pro");
        ProductMetadata productMetadata2 = new ProductMetadata(2, "Samsung", "Samsung S27");

        map.put(1, productMetadata1);
        map.put(2, productMetadata2);

    }

    @Test
    public void mapTest5(){

        RMap<String, Integer> map = this.redissonClient.getMap("user:42:stats", StringCodec.INSTANCE);

        map.put("logins",1 );
        map.put("likes",10 );
        map.put("payments",2 );

        Integer likes = map.addAndGet("likes", 3);
        System.out.println("Updated Likes: "+ likes);

    }

    @Test
    public void mapTest6(){

        RMap<String, Integer> map = this.redissonClient.getMap("user:42:stats", StringCodec.INSTANCE);

        Integer likes = map.addAndGet("likes", -5);
        System.out.println("Updated Likes: "+ likes);

    }

}
