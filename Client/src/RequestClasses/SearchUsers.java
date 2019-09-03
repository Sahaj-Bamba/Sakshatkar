package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.util.ArrayList;

public class SearchUsers {

	String text;

	ArrayList<Client> clients;

	public SearchUsers(String text, ArrayList<Client> clients) {
		this.text = text;
		this.clients = clients;
	}

	public SearchUsers(String name) {
		this.text = name;
		this.clients = new ArrayList<Client>();
	}

	public String getName() {
		return text;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.SEARCHUSERS);
	}

}
