package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Dashboard {

    @FXML
    private AnchorPane topAnchorPane;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private AnchorPane rightAnchorPane;

    public void intialize()
    {
//        AnchorPane root1=null;
//        AnchorPane root2=null;
//        AnchorPane root3=null;

        try{
//                root1 = FXMLLoader.load(getClass().getResource());
//                root2 = FXMLLoader.load(getClass().getResource());
//                root3 = FXMLLoader.load(getClass().getResource());
            topAnchorPane = FXMLLoader.load(getClass().getResource("../FXML/HeaderMain.fxml"));
            leftAnchorPane = FXMLLoader.load(getClass().getResource("../FXML/LeftView.fxml"));
            rightAnchorPane = FXMLLoader.load(getClass().getResource("../FXML/RightView.fxml"));

        }
        catch(IOException e)
        {
                e.printStackTrace();
        }

    }

}
