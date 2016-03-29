package sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SocketExample {

    public static void main(String[] args) throws Exception {

        new SocketExample().startServer();

        while (true) {
            final Socket client = new Socket("localhost", 9999);

            final ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject("SOme");
            oos.writeObject("SOme");

            client.close();

            TimeUnit.SECONDS.sleep(1);
        }
    }

    private ServerSocket server;

    public void startServer() throws Exception {

        final Thread t = new Thread() {
            @Override
            public void run() {

                try {

                    server = new ServerSocket(9999);

                    while (true) {
                        final Socket socket = server.accept();

                        final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                        System.err.println("Server received :  " + ois.readObject());
                    }

                } catch (final Exception e) {

                }

            }
        };

        t.start();

    }

}
