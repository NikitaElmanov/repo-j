package patterns.behavioral.observer;

import patterns.behavioral.observer.core.Observer;
import patterns.behavioral.observer.core.Subscriber;
import patterns.behavioral.observer.core.VideoHostingSite;

public class Main {

    public static void main(String[] args) {
        VideoHostingSite videoHostingSite = new VideoHostingSite();
        final String video2 = "video 2";

        videoHostingSite.addVideo("video 1");
        videoHostingSite.addVideo(video2);

        Observer subscriber1 = new Subscriber("Tom");
        Observer subscriber2 = new Subscriber("Ann");

        videoHostingSite.addObserver(subscriber1);
        videoHostingSite.addObserver(subscriber2);

        videoHostingSite.addVideo("video 3");

        videoHostingSite.removeVideo(video2);
    }

}
