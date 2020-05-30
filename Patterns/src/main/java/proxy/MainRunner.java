package proxy;

import lombok.AllArgsConstructor;

public class MainRunner {
    public static void main(String[] args) {
        Video video = new YouTubeVideoProxy(new YouTubeVideo(1000));
        Video video2 = new YouTubeVideo(10);

        video.play();
        System.out.println("----------------------------------------");
        video2.play();
    }
}

@FunctionalInterface
interface Video {
    void play();
}

@AllArgsConstructor
class YouTubeVideo implements Video {

    private int duration;

    @Override
    public void play() {
        System.out.println("Video is playing... with duration = " + duration);
    }
}

@AllArgsConstructor
class YouTubeVideoProxy implements Video {

    private Video video;

    @Override
    public void play() {
        System.out.println("Showing an advertisement...");
        video.play();
    }
}


