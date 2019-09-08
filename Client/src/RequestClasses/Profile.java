package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;

public class Profile implements Serializable {

	String userId;
	Client client;

	public Profile(String userId, Client clients) {
		this.userId = userId;
		this.client = clients;
	}

	public String getUserId() {
		return userId;
	}

	public Client getClient() {
		return client;
	}

	public Profile(String userId) {
		this.userId = userId;
		this.client = null;
	}

	public Profile(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.PROFILE);
	}

}