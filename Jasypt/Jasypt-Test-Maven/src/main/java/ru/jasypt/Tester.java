package ru.jasypt;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE)
public class Tester implements CommandLineRunner {

    @Value("${secret.login}")
    String login;
    @Value("${secret.password}")
    String password;

    @Override
    public void run(String... args) {
        log.info("login: {}, password: {}", login, password);
    }
}
