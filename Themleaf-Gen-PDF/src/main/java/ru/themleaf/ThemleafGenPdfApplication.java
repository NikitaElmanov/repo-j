package ru.themleaf;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.AbortException;
import org.aspectj.weaver.BCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.themleaf.aop.ExceptionsHandled;

//@Slf4j
@EnableScheduling
@EnableMBeanExport
@SpringBootApplication
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class ThemleafGenPdfApplication {

    private static final Logger log = LoggerFactory.getLogger(ThemleafGenPdfApplication.class);

    private static List<String> STATUSES = List.of(
            "INTERNAL",
            "BUSINESS",
            "EXTERNAL"
    );

    private final ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ThemleafGenPdfApplication.class, args);

        log.info("Hello Slf4j");
        log.error("uh-oh", new Exception("oops!"));
        log.error("WTF dude?!?");
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
        log.error("[{}] error message test!!!", STATUSES.get(new Random().nextInt(3)));
    }

//    @Component
//    class PreStartExecutor implements CommandLineRunner {
//
//        @Override
//        public void run(final String... args) {
//            var beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
//            beanFactory.registerSingleton(logger.getClass().getCanonicalName(), logger);
//        }
//    }

}
