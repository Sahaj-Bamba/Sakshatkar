package DataClasses;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;

public class Client {

	String name;
	int isOnline;
	BufferedImage pic;
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
		Image pic = null;
//		pic = new Image(SwingFXUtils.toFXImage(this.pic,null));
//		pic = new Image()
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
		this.pic = SwingFXUtils.fromFXImage(pic, null);
		this.lastOnline = lastOnline;
		this.id = id;
		this.status = status;
	}

}
