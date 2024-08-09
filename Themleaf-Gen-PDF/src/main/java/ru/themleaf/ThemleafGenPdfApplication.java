package ru.themleaf;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.AbortException;
import org.aspectj.weaver.BCException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.themleaf.aop.ExceptionsHandled;

@Slf4j
@EnableScheduling
@EnableMBeanExport
@SpringBootApplication
@EnableAspectJAutoProxy
public class ThemleafGenPdfApplication {

    private static List<String> STATUSES = List.of(
            "INTERNAL",
            "BUSINESS",
            "EXTERNAL"
    );

    public static void main(String[] args) {
        SpringApplication.run(ThemleafGenPdfApplication.class, args);
//        // Create a WatchService
//        var watchService = FileSystems.getDefault().newWatchService();
//
//        // Define the directory to watch
//        var directoryToWatch = Path.of("log");
//
//        // Register the directory with the WatchService for ENTRY_MODIFY events
//        directoryToWatch.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
//
//        // Start an infinite loop to watch for file changes
//        while (true) {
//            WatchKey key;
//            try {
//                // Wait for events to occur
//                key = watchService.take();
//            } catch (InterruptedException e) {
//                return;
//            }
//
//            // Process the events
//            for (var event : key.pollEvents()) {
//                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
//                    // Handle file modification event
//                    System.out.println("File modified: " + event.context());
//                }
//            }
//
//            // Reset the key to receive further events
//            key.reset();
//        }
    }

    @ExceptionsHandled
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void schedule() {
        final var randomNumber = new Random().nextInt(3);
        var errorMessage = "[%s] error message test!!!".formatted(STATUSES.get(randomNumber));

        switch (randomNumber) {
            case 0 -> throw new RuntimeException(errorMessage);
            case 1 -> throw new AbortException(errorMessage);
            case 2 -> throw new BCException(errorMessage);
        }
//        log.error("[{}] error message test!!!", statuses.get(new Random().nextInt(3)));
    }

}
