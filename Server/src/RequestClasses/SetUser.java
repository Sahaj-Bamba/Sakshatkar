package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;

public class SetUser implements Serializable {

	Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public SetUser(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.SETUSER);
	}

}