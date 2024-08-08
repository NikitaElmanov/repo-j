package ru.themleaf;

import java.io.IOException;
import java.util.List;
import java.util.Random;
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

    private List<String> statuses = List.of(
            "INTERNAL",
            "BUSINESS",
            "EXTERNAL"
    );

    public static void main(String[] args) throws IOException {
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

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void schedule() {
        log.error("[%s] error message test!!!".formatted(statuses.get(new Random().nextInt(3))));
    }

}
