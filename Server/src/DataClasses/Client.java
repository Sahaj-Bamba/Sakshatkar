package DataClasses;

import java.io.Serializable;

public class Client implements Serializable {

	private String name;
	private int isOnline;
	private String lastOnline;
	private String userID;
	private int status;
	private String phone;
	private String extension;
	private String emailAddress;

	public Client(String name, int isOnline, String lastOnline, String userID, int status, String phone, String extension, String emailAddress) {
		this.name = name;
		this.isOnline = isOnline;
		this.lastOnline = lastOnline;
		this.userID = userID;
		this.status = status;
		this.phone = phone;
		this.extension = extension;
		this.emailAddress = emailAddress;
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

	public void setUserID(String userId) {
		this.userID = userId;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return this.userID+"#"+this.name+"#"+this.extension+"#"+this.isOnline+"#"+this.status+"#"+this.phone+"#"+this.lastOnline+"#"+this.emailAddress;
	}

}
