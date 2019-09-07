package Utilities;

import DataClasses.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.ArrayList;

public class GetListView {

    ObservableList<Client> observableList;

    public GetListView(ArrayList<Client> arrayList) {
        this.observableList = FXCollections.observableArrayList();
        this.observableList.addAll(arrayList);
    }

    public ObservableList<Client> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList observableList) {
        this.observableList = observableList;
    }

    public ListView<Client> generateListView() {
        ListView<Client> setListView = new ListView<Client>();
//        listView.setPrefWidth(600);
        setListView.setItems(observableList);
        setListView.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
            @Override
            public ListCell<Client> call(ListView<Client> listView) {
                return new ListViewCell();
            }
        });
        return setListView;
    }

}