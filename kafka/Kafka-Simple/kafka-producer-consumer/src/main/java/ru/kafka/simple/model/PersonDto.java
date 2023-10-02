package ru.kafka.simple.model;

import java.util.List;

public record PersonDto(String name, String lastName, Integer age, GenderDto gender, List<String> plans) {
}
