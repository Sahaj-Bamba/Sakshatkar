package Main;

import Request.ClientToken;
import Request.GroupList;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Owner {

	private HashMap<String,Group> groups;
	private GroupList groupList;

	public Owner(){
		groups = new HashMap<String, Group>();
		groups.put("extra",new Group("extra",""));
		groupList = new GroupList();
	}

	public boolean add_group(String name,String password){
		if (group_exist(name)){
			return false;
		}
		groups.put("extra",new Group(name,password));
		groupList.add(name);
		return true;
	}

	public void remove_group(String name){
		groups.remove(name);
		groupList.remove(name);
	}

	public GroupList get_group_list() {
		return groupList;
	}

	public void add_client(String groupName, String clientName, ObjectOutputStream objectOutputStream){
		if (group_exist(groupName)){
			groups.get(groupName).add_client(clientName,objectOutputStream);
		}
	}

	public void remove_client(String groupName, String clientName){
		if (group_exist(groupName)){
			groups.get(groupName).remove_client(clientName);
		}
	}

	public boolean group_exist(String name){
		return groups.containsKey(name);
	}

	public boolean send_message(Object message){

		boolean flag = true;
		Iterator group = groups.entrySet().iterator();
		while (group.hasNext()){
			Map.Entry g = (Map.Entry)group.next();
			flag = flag & ((Group)g.getValue()).send_message(message);
		}

		return flag;
	}

	public boolean send_message(Object message,String groupName){
		if (group_exist(groupName)){
			return groups.get(groupName).send_message(message);
		}
		return false;
	}

	public boolean send_message(Object message,String groupName, String clientName){
		if (group_exist(groupName)){
			return groups.get(groupName).send_message(message,clientName);
		}
		return false;
	}

	public boolean send_message(Object message, ClientToken clientToken){
		if (group_exist(clientToken.getGroupName())){
			return groups.get(clientToken.getGroupName()).send_message(message,clientToken.getClientName());
		}
		return false;
	}

}
