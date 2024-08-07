package ru.themleaf;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@EnableMBeanExport
@SpringBootApplication
public class ThemleafGenPdfApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThemleafGenPdfApplication.class, args);
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void schedule() {
        log.error("error message test!!!");
    }

}
