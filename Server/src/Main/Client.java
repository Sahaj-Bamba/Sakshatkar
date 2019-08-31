package Main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	private String name;
	private Socket socket;
	private ObjectOutputStream objectOutputStream;

	public Client(String name, ObjectOutputStream objectOutputStream){
		this.name = name;
		this.objectOutputStream = objectOutputStream;
	}

	public boolean send_message(Object message) {
		try {
			objectOutputStream.writeObject(message);
			objectOutputStream.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getName(){
		return this.name;
	}
}