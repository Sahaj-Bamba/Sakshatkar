package DataClasses.DataClasses;

public class Client {

	String name;
	int isOnline;
	String lastOnline;
	String id;
	int status;

	public Client() { }

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

	public String getLastOnline() {
		return lastOnline;
	}

	public String getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public Client(String name, int isOnline, String lastOnline, String id, int status) {
		this.name = name;
		this.isOnline = isOnline;
		this.lastOnline = lastOnline;
		this.id = id;
		this.status = status;
	}

	@Override
	public String toString() {
		return this.name+"#"+this.isOnline+"#"+this.lastOnline+"#"+this.id+"#"+this.status;
	}

}
