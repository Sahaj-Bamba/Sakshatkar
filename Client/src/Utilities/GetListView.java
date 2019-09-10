package Utilities;

import Controller.MainScreen;
import Main.Main;
import RequestClasses.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class GetListView {

    ObservableList observableList;

    public GetListView(ArrayList arrayList) {
        this.observableList = FXCollections.observableArrayList();
        this.observableList.addAll(arrayList);
    }

    public ObservableList getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList observableList) {
        this.observableList = observableList;
    }

    public ListView generateListView(Object obj) {
        ListView setListView = new ListView();
//        listView.setPrefWidth(600);
        setListView.setItems(observableList);
        setListView.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                if (obj instanceof GetConnectionChat) {
                    return new ListViewCell();
                } else if (obj instanceof CallDetails) {
                    return new ListViewCellCall();
                } else if (obj instanceof SearchFriends) {
                    return new ListViewCell();
                } else if(obj instanceof Notification) {
                    return new ListViewCell();
                } else if(obj instanceof SearchUser) {
                    return new ListViewCell();
                }
                return new ListViewCell();
            }
        });
        setListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String otherUserDetails = setListView.getSelectionModel().getSelectedItem().toString();
                System.out.println(otherUserDetails);
                String[] userDetails = otherUserDetails.split("#");
                String otherUserID = userDetails[3];
                try {
                    Main.GAMER.send_message(new GetChats(Main.USER.getUserID(), otherUserID));
                    System.out.println("Message sent");
//                    GetChats response = (GetChats) Main.GAMER.receive_message();
                    MainScreen.showChats();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return setListView;
    }

}