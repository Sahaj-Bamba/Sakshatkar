package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class FriendRequest implements Serializable {

	String name;

	public FriendRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.FRIENDREQUEST);
	}

}
