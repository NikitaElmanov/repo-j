package patterns.behavioral.observer.core;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyEverybody();
}
