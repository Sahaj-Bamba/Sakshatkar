package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class Notification implements Serializable {

    String name;
    ArrayList<Client> clients;

    public Notification(String name,ArrayList<Client> clients) {
        this.name = name;
        this.clients = clients;
    }

    public Notification(String name) {
        this.name = name;
        this.clients = new ArrayList<Client>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(Request.NOTIFICATION);
    }


}
