package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestFileServer extends Thread{
        private ServerSocket serverSocket;
        private Socket socket;

        int port = 6000;
        public void fileServerInitializer() {
            System.out.println("In the start method of RequestFileServer");
            while (true) {
                try {
                    serverSocket = new ServerSocket(port);
                    break;
                } catch (IOException e) {
//                    e.printStackTrace();
                    port++;
                }
            }

            System.out.println("File Server started on port " + port);

        }

    public void run() {

        fileServerInitializer();

        while (true) {
            try {
                System.out.println("Accepting sockets from RequestFileServer");
                socket = serverSocket.accept();
                System.out.println("Client socket accepted from RequestFileServer");
                Thread t = new Thread(new HandleClientFile(socket));
                System.out.println("Handle client for file server created");
                t.start();
                System.out.println("Thread Started for RequestFileServer");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
