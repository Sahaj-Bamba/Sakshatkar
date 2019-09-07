package DataClasses;

import java.io.Serializable;

public class Client implements Serializable {

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
