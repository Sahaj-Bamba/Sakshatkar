package Utilities;

import DataClasses.Client;
import RequestClasses.CallUser;
import RequestClasses.GetConnectionChat;
import RequestClasses.Notification;
import RequestClasses.SearchFriends;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

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
                } else if (obj instanceof CallUser) {
                    return new ListViewCellCall();
                } else if (obj instanceof SearchFriends) {
                    return new ListViewCell();
                } else if(obj instanceof Notification) {
                    return new ListViewCell();
                }
                return new ListViewCell();
            }
        });
        return setListView;
    }

}