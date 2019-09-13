package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class Response implements Serializable {

	private int status;
	private String message;

	public Response(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String toString(){
		return String.valueOf(Request.RESPONSE);
	}

}