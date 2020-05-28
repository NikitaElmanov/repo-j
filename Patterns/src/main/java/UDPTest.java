import test.EchoClient;
import test.EchoServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UDPTest {

    public static void main(String[] args) throws IOException {
        new EchoServer().start();
        EchoClient client = new EchoClient();

        client.sendEcho(new FileInputStream(
                new File("C:\\Users\\NickMax\\Desktop\\Lesson 1.mp3")));

        client.close();
    }
}