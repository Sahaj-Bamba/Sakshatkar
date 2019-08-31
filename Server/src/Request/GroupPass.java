package Request;

import Constant.Request;

import java.io.Serializable;

public class GroupPass implements Serializable {

	private String clientName;
	private String password;
	private String groupName;

	public GroupPass(String password, String groupName, String clientName){
		this.password = password;
		this.groupName = groupName;
		this.clientName = clientName;
	}

	public String get_password() {
		return password;
	}

	public String get_group_name() {
		return groupName;
	}

	public String get_client_name() {
		return clientName;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.GROUPPASS);
	}

}
