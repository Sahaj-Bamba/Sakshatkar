package Controller;

import Request.GroupList;
import Request.WhoIAm;
import sample.Main;

import java.io.IOException;

import static sample.Main.GAMER;

public class Controller3_2 {

	GroupList groupList;

	public Controller3_2(){
		System.out.println("kill him");

		try {
			GAMER.send_message(new GroupList(GAMER.getClientToken()));
			System.out.println("Name sent to server");
			groupList = (GroupList) GAMER.receive_message();
			System.out.println("Got group data");
			for (String X : groupList.getGroups()){
				System.out.println(X);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void submit(){
		System.out.println("3_2 Hi");

	}
}
