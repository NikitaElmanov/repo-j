package ru.themleaf.jmx;

import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(description = "Log processor bean")
public class LogFileMBean {

    @Setter
    private List<String> errors = new ArrayList<>();

    @ManagedAttribute(description = "Get errors message attribute")
    public List<String> getErrors() {
        return errors;
    }

    public void process(List<String> contentLines) {
        this.errors = contentLines.stream()
                .filter(line -> line.contains("ERROR"))
                .toList();
    }

}
