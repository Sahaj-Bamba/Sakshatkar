package RequestClasses;

import Constant.Request;

public class FriendRequest {

	String name;

	public FriendRequest(String user) {
		this.name = user;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.FRIENDREQUEST);
	}

}