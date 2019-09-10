package Utilities;

import DataClasses.Call;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ListViewCellCall extends ListCell<Call> {

    @FXML
    Label userName;

    @FXML
    Label lastOnline;

    @FXML
    ImageView imageView;

    @FXML
    ImageView callTypeImageView;

    @FXML
    AnchorPane anchorPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Call call, boolean empty) {
        super.updateItem(call, empty);

        if(empty || call == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../FXML/ListCellCall.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            userName.setText(call.getUserName());
            lastOnline.setText(call.getLastOnline());

            try {

                File file = Main.FILESYSTEM.getProfilePicture(call.getUserID()+"."+call.getExtension());
                Image image = new Image(file.toURI().toURL().toString());
                imageView.setImage(image);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            File file = null;
            if(call.getIsCallReceivedCalledMissed() == Call.callTypeEnum.INCOMING.getValue()){
                file = new File("src/Icons/incomingCall.png");
            }else if(call.getIsCallReceivedCalledMissed() == Call.callTypeEnum.OUTGOING.getValue()){
                file = new File("src/Icons/outgoingCall.png");
            }else if(call.getIsCallReceivedCalledMissed() == Call.callTypeEnum.MISSED.getValue()) {
                file = new File("src/Icons/missedCall.png");
            }

            try {
                callTypeImageView.setImage(new Image(file.toURI().toURL().toString()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            setText(null);
            setGraphic(anchorPane);
        }

    }
}

