package ru.mongo.test.mongodbtest.model;

import lombok.Data;

@Data
public class Address {

    String line1;
    String line2;
    String city;
    String state;
    long zipZone;
}
