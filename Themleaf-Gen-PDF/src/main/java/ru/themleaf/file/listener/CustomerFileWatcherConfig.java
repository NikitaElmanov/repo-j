package ru.themleaf.file.listener;

import jakarta.annotation.PreDestroy;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomerFileWatcherConfig {

    private final CustomerAddFileChangeListener customerAddFileChangeListener;

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        var fileSystemWatcher = new FileSystemWatcher(
                true,
                Duration.ofSeconds(5),
                Duration.ofSeconds(1)
        );
        fileSystemWatcher.addSourceDirectory(
                Path.of("\\Users\\Nikita.Elmanov\\IdeaProjects\\repo-j\\Themleaf-Gen-PDF\\log").toFile());
        fileSystemWatcher.addListener(customerAddFileChangeListener);

        var postfix = getPostfix();

//        fileSystemWatcher.setTriggerFilter(f -> f.getAbsolutePath().contains(postfix));
        fileSystemWatcher.start();

        log.info(String.format("FileSystemWatcher initialized. Monitoring directory %s", "Themleaf-Gen-PDF/log"));

        return fileSystemWatcher;
    }

    private String getPostfix() {
        var date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return "%s.log".formatted(date);
    }

//    @PreDestroy
//    public void onDestroy() {
//        log.info("Shutting Down File System Watcher.");
//        fileSystemWatcher()..stop();
//    }

}