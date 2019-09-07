package DataClasses;

import java.io.Serializable;

public class Client implements Serializable {

	String name;
	String userID;
	int isOnline;
	String lastOnline;
	String userId;
	int status;
	String phone;
	String extension;

	public Client() { }

	public String getName() {
		return name;
	}

	public int isOnline() {
		return isOnline;
	}

	public String getLastOnline() {
		return lastOnline;
	}

	public String getUserId() {
		return userId;
	}

	public int getStatus() {
		return status;
	}

	public int getIsOnline() {
		return isOnline;
	}

	public Client(String name, int isOnline, String lastOnline, String userId, int status, String phone, String extension) {
		this.name = name;
		this.isOnline = isOnline;
		this.lastOnline = lastOnline;
		this.userId = userId;
		this.status = status;
		this.phone = phone;
		this.extension = extension;
	}

	@Override
	public String toString() {
		return this.name+"#"+this.isOnline+"#"+this.lastOnline+"#"+this.userId+"#"+this.status+"#"+this.phone+"#"+this.extension;
	}

}
