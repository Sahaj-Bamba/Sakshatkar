package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class AcceptRequest implements Serializable {

	String name;

	public AcceptRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.ACCEPTREQUEST);
	}

}
