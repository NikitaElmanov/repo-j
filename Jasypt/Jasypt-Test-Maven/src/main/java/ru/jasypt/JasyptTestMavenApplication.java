package ru.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class JasyptTestMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasyptTestMavenApplication.class, args);
    }

}
