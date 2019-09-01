package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;

public class Profile implements Serializable {

	String name;
	Client clients;

	public Profile(String name, Client clients) {
		this.name = name;
		this.clients = clients;
	}

	public String getName() {
		return name;
	}

	public Profile(String name) {
		this.name = name;
		this.clients = null;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.PROFILE);
	}

}