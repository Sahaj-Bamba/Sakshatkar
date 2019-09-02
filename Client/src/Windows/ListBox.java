package Windows;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ListBox
{
    private Stage window;
    private ListView<String> listView;
    private Scene scene;
    private Button button;
    private String title;
    private String heading;
    private ArrayList<String> input;
    private ArrayList<String> output;

    public ListBox(String title, String heading, ArrayList<String> input) {
        this.title = title;
        this.heading = heading;
        this.input = input;
        this.output = new ArrayList<String>();
    }

    public ArrayList<String> start(SelectionMode selectionMode)
    {
        window = new Stage();
        window.setTitle(title);

        //ListView

        button = new Button("Submit");

        listView = new ListView<String>();
        listView.getItems().addAll(input);
        listView.getSelectionModel().setSelectionMode(selectionMode);


        button.setOnAction(e -> buttonClicked());
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(listView,button);


        scene = new Scene(layout, 600, 550);
        window.setScene(scene);
        window.showAndWait();

        return output;
    }

    private void buttonClicked() {

    /*private void getChoice(ChoiceBox<String> choiceBox) {

        String food = choiceBox.getValue();

        System.out.println(food);
    }*/

        ObservableList<String> out;
        out = listView.getSelectionModel().getSelectedItems();

        for (String m : out) {
            output.add(m);
        }

        window.close();
    }


}
