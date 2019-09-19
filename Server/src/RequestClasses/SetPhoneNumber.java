package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class SetPhoneNumber implements Serializable {

    private String userID;
    private String phoneNo;

    public SetPhoneNumber(String userID, String phoneNo) {
        this.userID = userID;
        this.phoneNo = phoneNo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return String.valueOf(Request.SETPHONENUMBER);

    }
}
