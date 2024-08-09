package ru.themleaf.jmx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Setter
@Component
@ManagedResource(description = "Log processor bean")
public class LogFileMBean {

    public static final String ERROR_LOG_LEVEL = "ERROR";

    private List<String> internalErrors = new ArrayList<>();

    private List<String> businessErrors = new ArrayList<>();

    private List<String> externalErrors = new ArrayList<>();

    private String internalError;

    private String businessError;

    private String externalError;

    private List<String> allErrors = new ArrayList<>();

    private int totalErrorsCountLast5Mim;
    private int totalErrorsCountAccumulator;

    @ManagedAttribute(description = "Get errors message attribute")
    public String getInternalErrors() {
        return internalError;
    }

    @ManagedAttribute(description = "Get errors message attribute")
    public String getBusinessErrors() {
        return businessError;
    }

    @ManagedAttribute(description = "Get errors message attribute")
    public String getExternalErrors() {
        return externalError;
    }

    @ManagedAttribute(description = "Get internal errors count attribute")
    public int getInternalErrorsCount() {
        return internalErrors.size();
    }

    @ManagedAttribute(description = "Get business errors count attribute")
    public int getBusinessErrorsCount() {
        return businessErrors.size();
    }

    @ManagedAttribute(description = "Get external errors count attribute")
    public int getExternalErrorsCount() {
        return externalErrors.size();
    }

    @ManagedAttribute(description = "Get all errors count for last 5 minutes attribute")
    public int getTotalErrorsCountLast5Mim() {
        return this.totalErrorsCountLast5Mim;
    }

//    public void process(List<String> contentLines) {
//        this.allErrors = contentLines.stream()
//                .filter(line -> line.contains(ERROR_LOG_LEVEL))
//                .toList();
//        fillErrorsOutByType();
//    }

    public void addError(String error) {
        this.allErrors.add(error);
        fillErrorsOutByType(error);
    }

    private void fillErrorsOutByType(String error) {

        if (error.contains("[BUSINESS]")) {
            this.businessErrors.add(error);
            this.businessError = error;
        } else if (error.contains("[EXTERNAL]")) {
            this.externalErrors.add(error);
            this.externalError = error;
        } else {
            this.internalErrors.add(error);
            this.internalError = error;
        }

    }

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    public void countErrorsCountFor5Min() {
        if (this.totalErrorsCountAccumulator == 0) {
            this.totalErrorsCountAccumulator = this.allErrors.size();
            return;
        }

        this.totalErrorsCountLast5Mim = this.allErrors.size() - this.totalErrorsCountAccumulator;
        this.totalErrorsCountAccumulator += this.totalErrorsCountLast5Mim;
    }

}
