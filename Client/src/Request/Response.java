package Request;

import java.io.Serializable;

public class Response implements Serializable {

	private int status;
	private String errorMessage;

	public Response(int status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

}
