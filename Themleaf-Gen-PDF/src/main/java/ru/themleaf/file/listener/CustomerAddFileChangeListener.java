package ru.themleaf.file.listener;

import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.devtools.filewatch.ChangedFile.Type;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;
import ru.themleaf.jmx.LogFileMBean;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerAddFileChangeListener implements FileChangeListener {

    private final LogFileMBean logFileMBean;

    @Override
    public void onChange(final Set<ChangedFiles> changeSet) {
        for (var files : changeSet) {
            for (var file : files.getFiles()) {
                if (file.getType().equals(Type.MODIFY)) {
                    try {
                        logFileMBean.process(FileUtils.readLines(file.getFile(), UTF_8));
                    } catch (IOException ex) {
                        log.error(ex.getMessage(), ex);
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

}
