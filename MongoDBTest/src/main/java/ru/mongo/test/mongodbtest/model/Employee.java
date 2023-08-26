package ru.mongo.test.mongodbtest.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Employee {

    @Id
    UUID id;
    String firstName;
    String lastName;
    float salary;
    Address address;
    LocalDate joiningDate;
}
