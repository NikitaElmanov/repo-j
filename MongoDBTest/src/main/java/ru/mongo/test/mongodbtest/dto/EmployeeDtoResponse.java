package ru.mongo.test.mongodbtest.dto;

import lombok.Data;
import ru.mongo.test.mongodbtest.model.Address;

import java.time.LocalDate;

@Data
public class EmployeeDtoResponse {

    String firstName;
    String lastName;
    float salary;
    Address address;
    LocalDate joiningDate;
}
