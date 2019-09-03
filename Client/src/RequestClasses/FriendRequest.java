package RequestClasses;

import Constant.Request;

public class FriendRequest {

	String name;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.FRIENDREQUEST);
	}

}
