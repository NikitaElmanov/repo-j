package patterns.behavioral.observer.core;

import java.util.List;

@FunctionalInterface
public interface Observer {

    void handleEvent(List<String> videos);
}
