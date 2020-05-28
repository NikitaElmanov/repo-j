package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class EchoServer extends Thread {

    private DatagramSocket socket;
    private byte[] buf = new byte[1024];

    public EchoServer() throws SocketException {
        socket = new DatagramSocket(4445);
    }

    public void run() {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        try (FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\NickMax\\Desktop\\ggg.mp3"))) {
            while (true) {
                socket.receive(packet);
                fos.write(packet.getData());
                fos.flush();
                System.out.println("Server got the file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }
}
