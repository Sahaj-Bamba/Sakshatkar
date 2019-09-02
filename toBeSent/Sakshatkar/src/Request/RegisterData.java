package Request;

public class RegisterData {

    String userName;
    String userID;
    String password;
    String emailID;
    String phone;

    public RegisterData(String userName, String userID, String password, String emailID, String phone) {
        this.userName = userName;
        this.userID = userID;
        this.password = password;
        this.emailID = emailID;
        this.phone = phone;
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
}
