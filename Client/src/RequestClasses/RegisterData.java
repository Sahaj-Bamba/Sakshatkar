package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class RegisterData implements Serializable{

    private String userName;
    private String userID;
    private String password;
    private String emailID;
    private String phone;
    private String extension;
    private String lastOnline;
    private int status;

    public RegisterData(String userName, String userID, String password, String emailID, String phone, String extension, String lastOnline, int status) {
        this.userName = userName;
        this.userID = userID;
        this.password = password;
        this.emailID = emailID;
        this.phone = phone;
        this.extension = extension;
        this.lastOnline = lastOnline;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Request.REGISTER.toString();
    }
}
