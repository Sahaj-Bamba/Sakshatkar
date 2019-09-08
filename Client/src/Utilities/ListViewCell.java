package Utilities;

import DataClasses.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;

public class ListViewCell extends ListCell<Client> {

    @FXML
    Label userName;

    @FXML
    Label lastOnline;

    @FXML
    ImageView imageView;

    @FXML
    AnchorPane anchorPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);

        if(empty || client == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../FXML/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            userName.setText(client.getName());
            lastOnline.setText(client.getLastOnline());
            Boolean doesDirectoryExist = new File("src/ProfilePictures").mkdir();
            File file = new File("src/ProfilePictures/"+client.getUserID()+"."+client.getExtension());
//            if(file.exists() == false){
//                try {
//                    DataInputStream dataInputStream = Main.Main.FILEGAMER.receiveResponse();
//                    Boolean doesItContainFile = dataInputStream.readBoolean();
//                    if(doesItContainFile == true){

//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
            setText(null);
            setGraphic(anchorPane);
        }

    }
}
