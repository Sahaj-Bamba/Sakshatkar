package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchFriends implements Serializable {

    String userID;
    ArrayList<Client> clients;

    public SearchFriends(String userID, ArrayList<Client> clients) {
        this.userID = userID;
        this.clients = clients;
    }

    public SearchFriends(){
    }

    public SearchFriends(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String name) {
        this.userID = userID;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return String.valueOf(Request.SEARCHFRIENDS);
    }

}
