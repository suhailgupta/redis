package com.suhail.redisson.tests;

import com.suhail.redisson.tests.models.Student;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.Arrays;

public class KeyValueTest extends BaseTest{


    @Test
    public void keyValueTest(){
        RBucket<String> bucket = this.redissonClient.getBucket("student:1:name", StringCodec.INSTANCE);
        bucket.set("suhail"); // set k v

        String value = bucket.get();
        System.out.println("Value from Redis: "+ value);

    }


    @Test
    public void keyValueObjectAccessTest(){
        Student student = new Student("Rahul",10, "India", Arrays.asList(1,2));

        RBucket<Student> bucket = redissonClient.getBucket("student:1",
                new TypedJsonJacksonCodec(Student.class));
        // set value
        bucket.set(student);
        Student cachedStudent = bucket.get();
        System.out.println("Value from redis: "+ cachedStudent);
    }



}
