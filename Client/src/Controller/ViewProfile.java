package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.File;
import java.net.MalformedURLException;

public class ViewProfile {

    @FXML
    Label mutualFriends;

    @FXML
    Label userName;

    @FXML
    Label userID;

    @FXML
    TextField emailAddress;

    @FXML
    TextField phoneNumber;

    @FXML
    Label lastOnline;

    @FXML
    ImageView profilePicture;

    @FXML
    ImageView isOnline;

    @FXML
    Button editEmailID;

    @FXML
    Button editPhoneNumber;

    private int updateEmailMouseClicks;
    private int updatePhoneNumberMouseClicks;

    public void initialize(){
        updateEmailMouseClicks = 0;
        updateEmailMouseClicks = 0;
        emailAddress.setEditable(false);
        phoneNumber.setEditable(false);
        BackgroundImage buttonBackgroundImage;
        try {
            buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/editIcon.png").toURI().toURL().toString()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            editEmailID.setBackground(new Background(buttonBackgroundImage));
            editPhoneNumber.setBackground(new Background(buttonBackgroundImage));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmailID(ActionEvent event) {

        BackgroundImage buttonBackgroundImage;
        try {
            if (updateEmailMouseClicks % 2 == 0) {
                emailAddress.setEditable(true);
                buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/okayIcon.png").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            } else {
                emailAddress.setEditable(false);
                buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/editIcon.png").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            }
            editEmailID.setBackground(new Background(buttonBackgroundImage));
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
                phoneNumber.setEditable(true);
                buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/okayIcon.png").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            } else {
                phoneNumber.setEditable(false);
                buttonBackgroundImage = new BackgroundImage(new Image(new File("src/Icons/editIcon.png").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
            }
            editPhoneNumber.setBackground(new Background(buttonBackgroundImage));
            updatePhoneNumberMouseClicks^=1;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void updateProfilePressed(ActionEvent event) {
    }

    public void setProfile(String profileDetails){
        String[] profileDetail = profileDetails.split("#");
        //If profile does not belong to the current user, disable the buttons
    }

}
