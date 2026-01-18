package com.suhail.redisson.tests;

import org.junit.jupiter.api.Test;
import org.redisson.api.*;


public class NumberTest extends BaseTest{

    @Test
    public void numberTest(){
        // INCR, DECR
        RAtomicLong atomicLong = this.redissonClient.getAtomicLong("show:1:seats");
        long value = atomicLong.decrementAndGet(); // decr show:1:seats
        System.out.println("Seats Value is : "+ value);
        // incrby show:1:seats 10
        long updatedValue = atomicLong.addAndGet(-20);
        System.out.println("Seats Updated Value is : "+ updatedValue);

    }

}
