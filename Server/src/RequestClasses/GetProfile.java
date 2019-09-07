package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;

public class GetProfile implements Serializable {

	private String name;
	private Client client;

	public GetProfile(String name, Client client) {
		this.name = name;
		this.client = client;
	}

	public GetProfile(String name) {
		this.name = name;
		this.client = new Client();
	}

	public String getName() {
		return name;
	}

	public Client getClient() {
		return client;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.GETCONNECTIONSCHAT);
	}

}
