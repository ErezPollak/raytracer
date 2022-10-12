package cubesAPI;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.awt.Color.BLUE;

public final class API_CubeCreation {

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 6666;

    private static BL bl = BL.getInstance();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(4343, 10);
        System.out.println("waiting for client to connect...");
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        while (true) {

            try {
                // Receiving
                byte[] lenBytes = new byte[4];
                System.out.println("waiting for client request...");
                is.read(lenBytes, 0, 4);
                int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                        ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
                byte[] receivedBytes = new byte[len];
                is.read(receivedBytes, 0, len);
                String received = new String(receivedBytes, 0, len);

                System.out.println("Server received: " + received);
                String toSend = bl.paintCube(received);

                // Sending
                //String toSend = "Echo: " + received;
                byte[] toSendBytes = toSend.getBytes();
                int toSendLen = toSendBytes.length;
                byte[] toSendLenBytes = new byte[4];
                toSendLenBytes[0] = (byte) (toSendLen & 0xff);
                toSendLenBytes[1] = (byte) ((toSendLen >> 8) & 0xff);
                toSendLenBytes[2] = (byte) ((toSendLen >> 16) & 0xff);
                toSendLenBytes[3] = (byte) ((toSendLen >> 24) & 0xff);
                os.write(toSendLenBytes);
                os.write(toSendBytes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

        }

        socket.close();
        serverSocket.close();
    }

}


