package com.suhail.redisson.tests.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    private String name;
    private int age;
    private String country;
    private List<Integer> scores;

}
