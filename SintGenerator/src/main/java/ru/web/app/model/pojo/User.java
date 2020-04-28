package ru.web.app.model.pojo;

/**
 * POJO class with params: id, login and password.
 * Course this class has only setters and getters
 * for presented upper fields.
 */
public class User {
    private Integer id;
    private String login;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
