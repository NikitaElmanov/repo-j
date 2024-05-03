package patterns.behavioral.observer.core;

import java.util.List;

public interface Observer {
    void handleEvent(List<String> videos);
}
