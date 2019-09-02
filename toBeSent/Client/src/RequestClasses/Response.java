package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class Response implements Serializable {

	private int status;
	private String errorMessage;

	public Response(int status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public int getStatus() {
		return status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String toString(){
		return String.valueOf(Request.RESPONSE);
	}

}