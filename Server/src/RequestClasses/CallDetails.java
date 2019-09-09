package RequestClasses;

import Constant.Request;
import DataClasses.Call;

import java.io.Serializable;
import java.util.ArrayList;

public class CallDetails implements Serializable {

    private String userID;
    private ArrayList<Call> userDetails;

    public CallDetails(){
    }

    public CallDetails(String userID) {
        this.userID = userID;
    }

    public CallDetails(String userID, ArrayList<Call> userDetails) {
        this.userID = userID;
        this.userDetails = userDetails;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<Call> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(ArrayList<Call> userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return String.valueOf(Request.CALLDETAILS);
    }
}
