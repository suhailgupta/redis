package com.suhail.redisson.tests.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String name;
    private double price;
    private String description;

}
