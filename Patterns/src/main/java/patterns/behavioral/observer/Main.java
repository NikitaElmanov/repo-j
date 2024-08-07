package patterns.behavioral.observer;

import patterns.behavioral.observer.core.Subscriber;
import patterns.behavioral.observer.core.VideoHostingSite;

public class Main {

    public static void main(String[] args) {
        var videoHostingSite = new VideoHostingSite();

        var video2 = "video 2";
        videoHostingSite.addVideo("video 1");
        videoHostingSite.addVideo(video2);

        var subscriber1 = new Subscriber("Tom");
        var subscriber2 = new Subscriber("Ann");

        videoHostingSite.addObserver(subscriber1);
        videoHostingSite.addObserver(subscriber2);

        videoHostingSite.addVideo("video 3");

        videoHostingSite.removeVideo(video2);
    }

}
