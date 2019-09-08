package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class Online implements Serializable {

	String userId;

	public Online(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.ONLINE);
	}

}
