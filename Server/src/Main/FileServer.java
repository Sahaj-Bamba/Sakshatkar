package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer extends Thread{
        private ServerSocket serverSocket;
        private Socket socket;

        int port = 6000;
        public void fileServerInitializer() {
            System.out.println("In the start method of FileServer");
            while (true) {
                try {
                    serverSocket = new ServerSocket(port);
                    break;
                } catch (IOException e) {
//                    e.printStackTrace();
                    port++;
                }
            }

            System.out.println("File MessageServer started on port " + port);

        }

    public void run() {

        fileServerInitializer();

        while (true) {
            try {
//                System.out.println("Accepting sockets from FileServer");
                socket = serverSocket.accept();
//                System.out.println("Client socket accepted from FileServer");
                Thread t = new Thread(new HandleClientFile(socket));
//                System.out.println("Handle client for file server created");
                t.start();
                System.out.println("Thread Started for FileServer");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
