package RequestClasses;

import Constant.Request;

import java.io.Serializable;

public class GetMutualFriends implements Serializable {

    private String userID1;
    private String userID2;
    private int mutualFriendsCount;

    public GetMutualFriends(String userID1, String userID2) {
        this.userID1 = userID1;
        this.userID2 = userID2;
    }

    public GetMutualFriends(String userID1, String userID2, int mutualFriendsCount) {
        this.userID1 = userID1;
        this.userID2 = userID2;
        this.mutualFriendsCount = mutualFriendsCount;
    }

    public String getUserID1() {
        return userID1;
    }

    public void setUserID1(String userID1) {
        this.userID1 = userID1;
    }

    public String getUserID2() {
        return userID2;
    }

    public void setUserID2(String userID2) {
        this.userID2 = userID2;
    }

    public int getMutualFriendsCount() {
        return mutualFriendsCount;
    }

    public void setMutualFriendsCount(int mutualFriendsCount) {
        this.mutualFriendsCount = mutualFriendsCount;
    }

    @Override
    public String toString() {
        return String.valueOf(Request.GETMUTUALFRIENDS);
    }
}
