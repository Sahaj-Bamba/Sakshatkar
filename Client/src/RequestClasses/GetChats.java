package RequestClasses;

import DataClasses.Chat;

import java.io.Serializable;
import java.util.ArrayList;

public class GetChats implements Serializable {

    String userID1;
    String userID2;
    ArrayList<Chat> chats;

    public GetChats(String userID1, String userID2, ArrayList<Chat> chats) {
        this.userID1 = userID1;
        this.userID2 = userID2;
        this.chats = chats;
    }

    public GetChats() {
    }

    public GetChats(String userID1, String userID2) {
        this.userID1 = userID1;
        this.userID2 = userID2;
    }
    public String getUserID1() {
        return userID1;
    }

    public void setUserID1(String userID1) {
        this.userID1 = userID1;
    }

    public String getUserID2() {
        return userID2;
    }

    public void setUserID2(String userID2) {
        this.userID2 = userID2;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
}
