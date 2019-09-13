package Controller;

import DataClasses.Chat;
import RequestClasses.*;
import Utilities.GetListView;
import Utilities.ListViewCell;
import Utilities.ListViewCellCall;
import Utilities.ListViewCellChat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;

import static Main.Main.*;

public class ChatWindow_new {

    @FXML
    ImageView imageView;

    @FXML
    Label userName;

    @FXML
    TextField chatInput;

    @FXML
    ListView chatListView;

    @FXML
    Label mutualFriendsLabel;

    private String otherUserDetails;
    private int mutualFriendsCount;
    private String otherUserID;

    public void sendButton(ActionEvent event) {
        chatListView.getItems().add(new Chat("asdasd","asddsnjnnkjnknjknkjnjknkjnkjnjknjknkjnkjnjkna",0,"ghhjgjhhgjgjhgjhgjhgjhghjgjhgjhgjhgjhgjhghgjghj",1,0,false));
    }

    public void voiceCall(ActionEvent event) {
    }

    public void videoCall(ActionEvent event) {
    }

    public void addFriend(ActionEvent event) {
        try {
            GAMER.send_message(new FriendRequest(otherUserID));
            GAMER.receive_message();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addAttachments(ActionEvent event) {
    }

    public void viewProfile(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../FXML/Profile.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ViewProfile viewProfile = fxmlLoader.getController();
        viewProfile.setProfile(otherUserDetails, mutualFriendsCount);
        MAINSCREENCONTROLLER.setMainAnchorPane(root);
    }

//    public void showChats(String otherUserDetails){
//
//        this.otherUserDetails = otherUserDetails;
//        String[] userDetails = otherUserDetails.split("#");
//        String targetUserID = userDetails[0];
//        String targetUserName = userDetails[1];
//        String targetUserImageExtension = userDetails[2];
//
//        try {
//            File file = FILESYSTEM.getProfilePicture(targetUserID+"."+targetUserImageExtension);
//            Image image = new Image(file.toURI().toURL().toString());
//            this.imageView.setImage(image);
//            this.userNameLabel.setText(targetUserName);
//            System.out.println("@UserName "+this.userNameLabel.getText());
//            System.out.println("@USERTEXT "+userNameLabel.getText());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public void setUser(String otherUserDetails, Parent root){

        this.otherUserDetails = otherUserDetails;
        String[] userDetails = otherUserDetails.split("#");
        String targetUserID = userDetails[0];
        this.otherUserID = targetUserID;
        String targetUserName = userDetails[1];
        String targetUserImageExtension = userDetails[2];

        try {
            File file = FILESYSTEM.getProfilePicture(targetUserID+"."+targetUserImageExtension);
            Image image = new Image(file.toURI().toURL().toString());
            imageView.setImage(image);
            userName.setText(targetUserName);

            GAMER.send_message(new GetMutualFriends(USER.getUserID(), targetUserID));
            GetMutualFriends getMutualFriends = (GetMutualFriends) GAMER.receive_message();
            this.mutualFriendsCount = getMutualFriends.getMutualFriendsCount();
            mutualFriendsLabel.setText(this.mutualFriendsCount+" mutual friends");

            GAMER.send_message(new GetChats(USER.getUserID(), targetUserID));
            GetChats response = (GetChats) GAMER.receive_message();

            ObservableList observableList = FXCollections.observableArrayList();
            observableList.addAll(response.getChats());
            chatListView.setItems(observableList);

            chatListView.setCellFactory(new Callback<ListView, ListCell>() {

                @Override
                public ListCell call(ListView listView) {
                    return new ListViewCellChat();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        MAINSCREENCONTROLLER.setMainAnchorPane(root);

    }

//    public void showChats(String ){
//
//    }

}
