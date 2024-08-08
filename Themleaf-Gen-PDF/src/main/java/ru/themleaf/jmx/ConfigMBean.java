package ru.themleaf.jmx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.themleaf.pdf.service.ConfigService;

@Component
@RequiredArgsConstructor
@ManagedResource(description = "Application config bean")
public class ConfigMBean {

    @Getter
    @Setter
    private int value = 12;

    private List<String> errors = new ArrayList<>();

    private final ConfigService configService;

    @ManagedOperation(description = "Get all config keys")
    public Collection<String> getConfigs() {
        return configService.getConfigs();
    }

    @ManagedOperation(description = "Get config by key")
    @ManagedOperationParameters(
            @ManagedOperationParameter(name = "key", description = "Config key")
    )
    public String getConfig(String key) {
        return configService.getConfig(key);
    }

    @ManagedOperation(description = "Set config")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "key", description = "Config key"),
            @ManagedOperationParameter(name = "value", description = "Config value")
    })
    public void setConfig(String key, String value) {
        configService.setConfig(key, value);
    }

    @ManagedAttribute(description = "Get just integer attribute")
    public int getValue() {
        return this.value;
    }

    @Scheduled(fixedDelay = 20, timeUnit = TimeUnit.SECONDS)
    public void schedule() {
        value = new Random().nextInt(100);
    }

}
