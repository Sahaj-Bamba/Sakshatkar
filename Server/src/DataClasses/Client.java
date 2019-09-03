package DataClasses;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Client {

	String name;
	int isOnline;
	Timestamp lastOnline;
	String id;
	int status;

	public String getName() {
		return name;
	}

	public int isOnline() {
		return isOnline;
	}

//	public Image getPic() {
//		Image pic = null;
//		File outputfile = new File("zzzz.png");
//		try {
//			ImageIO.write(this.pic, "png", outputfile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		pic = new Image("zzzz.jpg");
//		return pic;
//	}

	public Timestamp getLastOnline() {
		return lastOnline;
	}

	public String getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public Client(String name, int isOnline, Timestamp lastOnline, String id, int status) {
		this.name = name;
		this.isOnline = isOnline;
		this.lastOnline = lastOnline;
		this.id = id;
		this.status = status;
	}

}
