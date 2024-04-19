package patterns.structure.proxy;

import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

public class MainRunner {
    public static void main(String[] args) {
        Video videoWithProxy = new YouTubeVideoProxy(new YouTubeVideo(1000));
        Video videoSimple = new YouTubeVideo(10);

        videoWithProxy.play();
        System.out.println("----------------------------------------");
        videoSimple.play();
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
    @SneakyThrows
    public void play() {
        System.out.println("Showing an advertisement...");
        TimeUnit.SECONDS.sleep(1);
        video.play();
    }
}


