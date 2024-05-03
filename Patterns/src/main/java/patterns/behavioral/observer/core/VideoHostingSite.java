package patterns.behavioral.observer.core;

import java.util.ArrayList;
import java.util.List;

public class VideoHostingSite implements Observable {

    private List<String> videos = new ArrayList<>();
    private List<Observer> subscribers = new ArrayList<>();

    public void addVideo(String video) {
        this.videos.add(video);
        notifyEverybody();
    }

    public void removeVideo(String video) {
        this.videos.remove(video);
        notifyEverybody();
    }

    @Override
    public void addObserver(Observer observer) {
        this.subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.subscribers.remove(observer);
    }

    @Override
    public void notifyEverybody() {
        this.subscribers.forEach(subscriber -> subscriber.handleEvent(this.videos));
    }
}
