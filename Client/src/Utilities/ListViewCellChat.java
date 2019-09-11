package Utilities;

import DataClasses.Chat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class ListViewCellChat extends ListCell<Chat> {

    @Override
    protected void updateItem(Chat chat, boolean empty) {
        super.updateItem(chat, empty);

        if (empty || chat == null) {

            setText(null);
            setGraphic(null);

        } else {
            AnchorPane anchorPane = new AnchorPane();
//            System.out.println(chat.toString());
            TextField textField = new TextField();
            textField.setPrefWidth(200);
            textField.setPrefHeight((double)(chat.getContent().length()/10)*200);
            textField.setText(chat.getContent());

        System.out.println(anchorPane);
            anchorPane.getChildren().add(textField);
            setText(null);
            setGraphic(anchorPane);
        }
    }
    }

