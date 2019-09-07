package Utilities;

import DataClasses.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ListViewCell extends ListCell<Client> {

    @FXML
    Label userName;

    @FXML
    Label lastOnline;

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

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
