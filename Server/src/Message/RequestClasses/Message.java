package Message.RequestClasses;

import java.io.Serializable;

public class Message implements Serializable {

	private String from;
	private String to;
	private String content;
	private int type;

	public Message(String from, String to, String content, int type) {
		this.from = from;
		this.to = to;
		this.content = content;
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getContent() {
		return content;
	}

	public int getType() {
		return type;
	}

}
