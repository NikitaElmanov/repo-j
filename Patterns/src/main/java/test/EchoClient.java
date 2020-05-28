package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;

public class EchoClient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf = new byte[1024];

    public EchoClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getLocalHost();
    }

    public void sendEcho(FileInputStream fis) throws IOException {
        DatagramPacket packet;
        while ((fis.read(buf)) != -1) {
            packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);
        }
    }

    public void close() {
        socket.close();
    }
}
