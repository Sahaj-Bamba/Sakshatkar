package Controller;

import Main.Main;
import RequestClasses.IsOnline;
import RequestClasses.Response;
import Utilities.FileExtension;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ViewProfile {

    @FXML
    Label mutualFriendsLabel;

    @FXML
    Label userNameLabel;

    @FXML
    Label userIDLabel;

    @FXML
    TextField emailAddressText;

    @FXML
    TextField phoneNumberText;

    @FXML
    Label lastOnlineLabel;

    @FXML
    ImageView profilePictureImageView;

    @FXML
    ImageView isOnlineImageView;

    @FXML
    Button editEmailIDButton;

    @FXML
    Button editPhoneNumberButton;

    @FXML
    Button updateProfilePictureButton;

    private int updateEmailMouseClicks;
    private int updatePhoneNumberMouseClicks;
    private Image profilePicture;
    private File selectedFile;
    private String targetUserImageExtension;

    public void initialize(){
        updateEmailMouseClicks = 0;
        updateEmailMouseClicks = 0;
        emailAddressText.setEditable(false);
        phoneNumberText.setEditable(false);
        BackgroundImage buttonBackgroundImage;
        try {
            buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/editIcon.png").toURI().toURL().toString()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            editEmailIDButton.setBackground(new Background(buttonBackgroundImage));
            editPhoneNumberButton.setBackground(new Background(buttonBackgroundImage));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmailID(ActionEvent event) {

        BackgroundImage buttonBackgroundImage;
        try {
            if (updateEmailMouseClicks % 2 == 0) {
                emailAddressText.setEditable(true);
                buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/okayIcon.png").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            } else {
                emailAddressText.setEditable(false);
                buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/editIcon.png").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            }
            editEmailIDButton.setBackground(new Background(buttonBackgroundImage));
            updateEmailMouseClicks^=1;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(updateEmailMouseClicks);

    }

    public void updatePhoneNumber(ActionEvent event) {

        BackgroundImage buttonBackgroundImage;
        try {
            if (updatePhoneNumberMouseClicks % 2 == 0) {
                phoneNumberText.setEditable(true);
                buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/okayIcon.png").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            } else {
                phoneNumberText.setEditable(false);
                buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/editIcon.png").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            }
            editPhoneNumberButton.setBackground(new Background(buttonBackgroundImage));
            updatePhoneNumberMouseClicks^=1;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void setProfile(String profileDetails, int mutualFriendsCount){

        String[] profileDetail = profileDetails.split("#");

        System.out.println("Details");
        for(String X: profileDetail)
            System.out.println(X);
        String targetUserID = profileDetail[0];
        String targetUserName = profileDetail[1];
        targetUserImageExtension = profileDetail[2];
        int status = Integer.parseInt(profileDetail[4]);
        String phoneNumber = profileDetail[5];
        String lastOnline = profileDetail[6];
        String emailAddress = profileDetail[7];

        mutualFriendsLabel.setText(String.valueOf(mutualFriendsCount)+" mutual friends");
        System.out.println(Main.USER.getUserID());
        System.out.println(targetUserID);
        if(targetUserID.compareTo(Main.USER.getUserID()) != 0){
            editEmailIDButton.setVisible(false);
            editPhoneNumberButton.setVisible(false);
            updateProfilePictureButton.setVisible(false);
        }
        try {
            selectedFile = Main.FILESYSTEM.getProfilePicture(targetUserID+"."+targetUserImageExtension);
            profilePicture = new Image(selectedFile.toURI().toURL().toString());
            profilePictureImageView.setImage(profilePicture);

            Main.GAMER.send_message(new IsOnline(targetUserID));
            Response response = (Response) Main.GAMER.receive_message();

            if(response.getStatus() == 1){
                isOnlineImageView.setImage(new Image(new File("src/Icons/online.png").toURI().toURL().toString()));
            }
            else{
                isOnlineImageView.setImage(new Image(new File("src/Icons/offline.png").toURI().toURL().toString()));
            }
            lastOnlineLabel.setText(response.getMessage());

            //Need to apply threading
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        userNameLabel.setText(targetUserName);
        userIDLabel.setText(targetUserID);
        emailAddressText.setText(emailAddress);
        phoneNumberText.setText(phoneNumber);
    }


    public void updateProfilePressed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *jpg, *jpeg, *img)", "*.png", "*.jpeg", "*.img", "*jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFileTemp = (fileChooser.showOpenDialog(null));
        selectedFile = (selectedFileTemp == null)? selectedFile : selectedFileTemp;

        try {
            if(selectedFile != null){

                Image image = new Image(selectedFile.toURI().toURL().toString());
                profilePictureImageView.setImage(image);
                FileExtension fileExtension = new FileExtension(selectedFile);
                targetUserImageExtension = fileExtension.getFileExtension();
//                System.out.println(extension);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /* Write image into database now */

    }

}
