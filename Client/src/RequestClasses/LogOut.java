package RequestClasses;

import java.io.Serializable;

public class LogOut implements Serializable {

    private String userID;

    public LogOut(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
