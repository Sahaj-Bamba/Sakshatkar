package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class Login implements Serializable{

		private String userID;
		private String pass;

		public Login(String name,String pass) {
			this.userID = name;
			this.pass = pass;
		}

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


