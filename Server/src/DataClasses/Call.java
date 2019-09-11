package DataClasses;

import java.io.Serializable;

public class Call implements Serializable {

    //This class contains the details of all the calls of a person
    private String userID;
    private String userName;
    private String phone;
    private String lastOnline;
    private String isOnline;
    private int isCallReceivedCalledMissed;
    private String extension;

    public enum callTypeEnum{

        INCOMING(0),
        OUTGOING(1),
        MISSED(2);

        private int value;

        callTypeEnum(int value) {
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public Call(String userID, String userName, String phone, String lastOnline, String isOnline, int isCallReceivedCalledMissed, String extension) {
        this.userID = userID;
        this.userName = userName;
        this.phone = phone;
        this.lastOnline = lastOnline;
        this.isOnline = isOnline;
        this.isCallReceivedCalledMissed = isCallReceivedCalledMissed;
        this.extension = extension;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastSeen) {
        this.lastOnline = lastSeen;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public int getIsCallReceivedCalledMissed() {
        return isCallReceivedCalledMissed;
    }

    public void setIsCallReceivedCalledMissed(int isCallReceivedCalledMissed) {
        this.isCallReceivedCalledMissed = isCallReceivedCalledMissed;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return this.userID+"#"+this.userName+"#"+this.extension+"#"+this.phone+"#"+this.lastOnline+"#"+this.isOnline+"#"+this.isCallReceivedCalledMissed;
    }
}
