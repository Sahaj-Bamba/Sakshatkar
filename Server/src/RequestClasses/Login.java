package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class Login implements Serializable{

		private String name;
		private String pass;

		public Login(String name,String pass) {
			this.name = name;
			this.pass = pass;
		}

	public String getPass() {
		return pass;
	}

	public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return String.valueOf(Request.LOGIN);
		}

	}


