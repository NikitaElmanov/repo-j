package ru.themleaf.jmx;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import ru.themleaf.pdf.service.ConfigService;

@Component
@RequiredArgsConstructor
@ManagedResource(description = "Application config bean")
public class ConfigMXBean {

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

}
