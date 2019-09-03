package Message.Main;

import Message.RequestClasses.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HandleClient implements Runnable{

	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private Message message;
	private String user;

	public HandleClient(Socket socket) {
		this.socket = socket;
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */

	@Override
	public void run() {

		boolean flag;

		while (true){

			try {
				message = (Message) objectInputStream.readObject();

				/*      Do processing       */

				Object response = process();

				/*      Do processing with esult */

				objectOutputStream.writeObject(response);
				objectOutputStream.flush();

				System.out.println("Response sent");

			} catch (IOException e) {
				System.out.println("Client Disconnected");
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}


	}

	private Object process(){

		boolean flag = false;

		if (this.message.getType() == 1){
			return oneToOne();
		}else if (this.message.getType() == 2){
			return ontToGroup();
		}

	}

	private Object ontToGroup() {



	}

	private Object oneToOne() {


	}


}