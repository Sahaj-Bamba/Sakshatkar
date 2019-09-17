package RequestClasses;

import Constant.Request;
import DataClasses.Chat;

import java.io.Serializable;

public class Message implements Serializable {

	Chat chat;

	public Message(Chat chat) {
		this.chat = chat;
	}

	@Override
	public String toString() {
		return (String.valueOf(Request.MESSAGE));
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Chat getChat() {
		return chat;
	}
}
