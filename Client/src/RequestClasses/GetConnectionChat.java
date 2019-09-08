package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class GetConnectionChat implements Serializable {

	private String name;
	private ArrayList<Client> clients;

	public GetConnectionChat(String name,ArrayList<Client> clients) {
		this.name = name;
		this.clients = clients;
	}

	public GetConnectionChat(String name) {
		this.name = name;
		this.clients = new ArrayList<Client>();
	}

	public GetConnectionChat(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.GETCONNECTIONSCHAT);
	}

}
