package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;

public class Profile implements Serializable {

	String name;
	Client client;

	public Profile(String name, Client clients) {
		this.name = name;
		this.client = clients;
	}

	public String getName() {
		return name;
	}

	public Client getClient() {
		return client;
	}

	public Profile(String name) {
		this.name = name;
		this.client = null;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.PROFILE);
	}

}