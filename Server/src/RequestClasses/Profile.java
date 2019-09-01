package RequestClasses;

import Constant.Request;
import DataClasses.Client;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {


	String name;
	ArrayList<Client> clients;

	public Profile(String name, ArrayList<Client> clients) {
		this.name = name;
		this.clients = clients;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.FRIENDSONLINE);
	}

}