package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class UserID implements Serializable{

    String userID;

    public UserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return Request.USERID.toString();
    }
}
