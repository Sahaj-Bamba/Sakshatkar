package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;

public class Profile implements Serializable {

	String userID;
	Client client;

	public Profile(String userID, Client clients) {
		this.userID = userID;
		this.client = clients;
	}

	public Profile(String userID) {
		this.userID = userID;
		this.client = null;
	}

	public Profile(Client client) {
		this.client = client;
	}

	public String getUserID() {
		return userID;
	}

	public Client getClient() {
		return client;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.PROFILE);
	}

}