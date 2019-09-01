package DataClasses;

import javafx.scene.image.Image;

import java.sql.Timestamp;

public class Client {

	String name;
	int isOnline;
	Image pic;
	Timestamp lastOnline;
	String id;
	int status;

	public String getName() {
		return name;
	}

	public int isOnline() {
		return isOnline;
	}

	public Image getPic() {
		return pic;
	}

	public Timestamp getLastOnline() {
		return lastOnline;
	}

	public String getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public Client(String name, int isOnline, Image pic, Timestamp lastOnline, String id, int status) {
		this.name = name;
		this.isOnline = isOnline;
		this.pic = pic;
		this.lastOnline = lastOnline;
		this.id = id;
		this.status = status;
	}



}
