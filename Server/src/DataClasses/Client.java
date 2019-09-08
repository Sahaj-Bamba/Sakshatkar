package DataClasses;

import java.io.Serializable;

public class Client implements Serializable {

	String name;
	int isOnline;
	String lastOnline;
	String userID;
	int status;
	String phone;
	String extension;


	public Client(String name, int isOnline, String lastOnline, String userID, int status, String phone, String extension) {
		this.name = name;
		this.isOnline = isOnline;
		this.lastOnline = lastOnline;
		this.userID = userID;
		this.status = status;
		this.phone = phone;
		this.extension = extension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}

	public String getLastOnline() {
		return lastOnline;
	}

	public void setLastOnline(String lastOnline) {
		this.lastOnline = lastOnline;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return this.name+"#"+this.isOnline+"#"+this.lastOnline+"#"+this.userID+"#"+this.status+"#"+this.phone+"#"+this.extension;
	}
}
