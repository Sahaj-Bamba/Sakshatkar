package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class Online implements Serializable {

	String userID;

	public Online(String userID) {
		this.userID = userID;
	}

	public String getUserId() {
		return userID;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.ONLINE);
	}

}
