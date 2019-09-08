package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class Login implements Serializable{

	public Login(String userID, String pass) {
		this.userID = userID;
		this.pass = pass;
	}

	private String userID;
	private String pass;


	public String getPass() {
		return pass;
	}

	public String getUserID() {
			return userID;
		}

	@Override
	public String toString() {
		return String.valueOf(Request.LOGIN);
	}

}


