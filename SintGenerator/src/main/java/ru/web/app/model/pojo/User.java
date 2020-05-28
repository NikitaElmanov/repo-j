package ru.web.app.model.pojo;

import lombok.Data;

/**
 * POJO class with params: id, login and password.
 * Course this class has only setters and getters
 * for presented upper fields.
 */
@Data
public class User extends Entity{
    private String login;
    private String password;
}
