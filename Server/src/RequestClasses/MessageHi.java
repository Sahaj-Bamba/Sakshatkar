package RequestClasses;

import java.io.Serializable;

public class MessageHi implements Serializable {

	String id;

	public MessageHi(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
