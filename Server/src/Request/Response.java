package Request;

import java.io.Serializable;

public class Response implements Serializable {

	private int status;
	private String errorMessage;

	public Response(int status, String errorMessage,ClientToken clientToken) {
		super(clientToken);
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public Response(int status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

}