package RequestClasses;

public class GetProfilePicture {

    private String userID;

    public GetProfilePicture(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
