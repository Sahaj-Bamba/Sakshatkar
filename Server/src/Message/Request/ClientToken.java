package Message.Request;


import java.io.Serializable;

public class ClientToken implements Serializable {

	private String clientName;
	private String groupName;

	public ClientToken(String clientName, String groupName) {
		this.clientName = clientName;
		this.groupName = groupName;
	}

	public ClientToken() {
		clientName = "";
		groupName = "";
	}

	public ClientToken(String clientName){
		this.clientName = clientName;
		this.groupName = "extra";
	}

	public ClientToken(ClientToken clientToken) {
		this.clientName = clientToken.getClientName();
		this.groupName = clientToken.getGroupName();
	}

	public ClientToken getter(){
		return this;
	}

	public void setter(ClientToken clientToken){
		this.clientName = clientToken.getClientName();
		this.groupName = clientToken.getGroupName();
	}

	public String getClientName() {
		return clientName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
