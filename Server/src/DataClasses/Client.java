package DataClasses;

import javafx.scene.image.Image;

import java.sql.Timestamp;

public class Client {

	String name;
	boolean status;
	Image pic;
	Timestamp timestamp;

	public Client(String name, boolean status, Image pic, Timestamp timestamp) {
		this.name = name;
		this.status = status;
		this.pic = pic;
		this.timestamp = timestamp;
	}

}
