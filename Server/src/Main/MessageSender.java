package Main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class MessageSender {

	private HashMap<String,ObjectOutputStream> sender;

	public MessageSender() {
		this.sender = new HashMap<String, ObjectOutputStream>();
	}

	public boolean addUser(String id, ObjectOutputStream oos){
		if (sender.containsKey(id)){
			return false;
		}
		sender.put(id,oos);
		return true;
	}

	public boolean removeUser(String id){
		if (!(sender.containsKey(id))){
			return false;
		}
		sender.remove(id);
		return true;
	}

	public boolean send(String to,Object message){
		if (sender.containsKey(to)){
			try {
				sender.get(to).writeObject(message);
				sender.get(to).flush();
			} catch (IOException e) {
				return false;
//				e.printStackTrace();
			}
		}
		return false;
	}




}
