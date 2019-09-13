package RequestClasses;

import java.io.Serializable;

public class IsOnline implements Serializable {

    private String userID;

    public IsOnline(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
