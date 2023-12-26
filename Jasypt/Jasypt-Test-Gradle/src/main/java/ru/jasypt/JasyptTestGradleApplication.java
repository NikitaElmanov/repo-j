package ru.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class JasyptTestGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasyptTestGradleApplication.class, args);
    }

}
