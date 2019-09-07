package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MessageServer extends Thread{

	private ServerSocket serverSocket;
	private Socket socket;

	public void serverInitializer(){

		boolean flag;
		int port = 5700;
		do {
			try {
				serverSocket = new ServerSocket(port);
				flag = true;
			} catch (IOException e) {
				flag = false;
				port++;
			}
		}while(flag);

		System.out.println("RequestServer started on port "+port);

	}

	public void run() {

		serverInitializer();

		while (true) {
			try {
//				System.out.println("Accepting sockets");
				socket = serverSocket.accept();
//				System.out.println("Client socket accepted");
				Thread t = new Thread(new HandleClientMessage(socket));
//				System.out.println("Handle Client created");
				t.start();
				System.out.println("Message Server Handelling Thread Started");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}