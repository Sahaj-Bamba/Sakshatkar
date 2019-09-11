package Utilities;

import Main.Main;
import RequestClasses.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

import static Main.Main.MAINSCREENCONTROLLER;

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
                System.out.println(obj.getClass().toString());
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
                } else if(obj instanceof GetChats) {
                    return new ListViewCellChat();
                }

                return new ListViewCell();
            }
        });

        setListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                String otherUserDetails = setListView.getSelectionModel().getSelectedItem().toString();

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../FXML/ChatWindow_new.fxml"));
                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.CHATWINDOWCONTROLLER = fxmlLoader.getController();
                Main.CHATWINDOWCONTROLLER.setUser(otherUserDetails, root);
            }
        });

        return setListView;
    }

}