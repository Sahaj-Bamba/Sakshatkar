package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class Notification implements Serializable {

    String userID;
    ArrayList<Client> clients;

    public Notification(){
    }

    public Notification(String userID) {
        this.userID = userID;
        this.clients = new ArrayList<Client>();
    }

    public Notification(String userID,ArrayList<Client> clients) {
        this.userID = userID;
        this.clients = clients;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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
        return String.valueOf(Request.NOTIFICATION);
    }


}
