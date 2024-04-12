package patterns.abstractFactiry;

public class Main {
    public static void main(String[] args) {
//        make(new AudioFactory(), UDPMediaContent.class);
        ((UDPVideoContent) MediaMaker.createVideo(UDPVideoContent.class)).transform();
        MediaMaker.createAudio(UDPAudioContent.class).play();
    }

    static void make(AbstractMediaFactory<UDPMediaContent, TCPMediaContent> abstractMediaFactory, Class<?> clazz) {
        System.out.println(clazz.getSimpleName());
    }
}

class MediaMaker {

    static MediaContent createVideo(Class<?> clazz) {
        switch (clazz.getSimpleName()) {
            case "UDPVideoContent":
                return new UDPVideoContent();
            case "TCPVideoContent":
                return new TCPVideoContent();
            default:
                throw new RuntimeException(clazz.getSimpleName() + " is not supported");
        }
    }

    static MediaContent createAudio(Class<?> clazz) {
        switch (clazz.getSimpleName()) {
            case "UDPAudioContent":
                return new UDPAudioContent();
            case "TCPAudioContent":
                return new TCPAudioContent();
            default:
                throw new RuntimeException(clazz.getSimpleName() + " is not supported");
        }
    }
}

interface AbstractMediaFactory <UDP extends UDPMediaContent, TCP extends TCPMediaContent> {
    UDP createMediaObjUDP();
    TCP createMediaObjTCP();
}

class AudioFactory implements AbstractMediaFactory <UDPAudioContent, TCPAudioContent> {

    @Override
    public UDPAudioContent createMediaObjUDP() {
        return new UDPAudioContent();
    }

    @Override
    public TCPAudioContent createMediaObjTCP() {
        return new TCPAudioContent();
    }
}

class VideoFactory implements AbstractMediaFactory <UDPVideoContent, TCPVideoContent> {

    @Override
    public UDPVideoContent createMediaObjUDP() {
        return new UDPVideoContent();
    }

    @Override
    public TCPVideoContent createMediaObjTCP() {
        return new TCPVideoContent();
    }
}

//----------------------------------------------------------------------------------------------

interface MediaContent {
    void play();
}

abstract class TCPMediaContent implements MediaContent {}

abstract class UDPMediaContent implements MediaContent {
    abstract void transform();
}

class TCPAudioContent extends TCPMediaContent {

    @Override
    public void play() {
        System.out.println(this.getClass().getSimpleName() + " is playing...");
    }
}

class UDPAudioContent extends UDPMediaContent{

    @Override
    public void play() {
        System.out.println(this.getClass().getSimpleName() + " is playing...");
    }

    @Override
    void transform() {
        System.out.println(this.getClass().getSimpleName() + ", audio is transforming...");
    }
}

class TCPVideoContent extends TCPMediaContent {

    @Override
    public void play() {
        System.out.println(this.getClass().getSimpleName() + ", audio is playing...");
    }
}

class UDPVideoContent extends UDPMediaContent{

    @Override
    public void play() {
        System.out.println(this.getClass().getSimpleName() + ", video is playing...");
    }

    @Override
    void transform() {
        System.out.println(this.getClass().getSimpleName() + ", video is transforming...");
    }
}
