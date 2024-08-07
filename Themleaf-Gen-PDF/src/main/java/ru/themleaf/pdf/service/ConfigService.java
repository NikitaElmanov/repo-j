package ru.themleaf.pdf.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    private Map<String, String> config = new HashMap<>() {{
        put("one", "one_value");
    }};

    public void setConfig(String key, String value) {
        config.put(key, value);
    }

    public String getConfig(String key) {
        return config.get(key);
    }

    public Collection<String> getConfigs() {
        return config.keySet().stream().toList();
    }

}
