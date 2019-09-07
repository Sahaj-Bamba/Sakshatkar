package RequestClasses;

import Constant.Request;
import DataClasses.Client;

import java.io.Serializable;

public class HeaderMain implements Serializable {

    String name;
    Client clients;

    public HeaderMain(String name, Client clients) {
        this.name = name;
        this.clients = clients;
    }

    public String getName() {
        return name;
    }

    public HeaderMain(String name) {
        this.name = name;
        this.clients = null;
    }

    @Override
    public String toString() {
        return String.valueOf(Request.PROFILE);
    }

}