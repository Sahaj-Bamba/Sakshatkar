package Controller;

import Request.RegisterData;
import Utilities.SqlQueryExecuter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.File;
import java.net.MalformedURLException;

public class RegistrationForm {

    @FXML
    TextField userName;

    @FXML
    TextField userID;

    @FXML
    PasswordField password;

    @FXML
    TextField email;

    @FXML
    TextField phoneNo;

    @FXML
    ImageView imageView;

    @FXML
    Label error;

    SqlQueryExecuter sqlQueryExecuter = new SqlQueryExecuter("root","","jdbc:mysql://localhost/sakshatkar");

    public void register(MouseEvent event) {
        if(userName.getText().isEmpty()){
            error.setText("Enter user Name");
            return;
        }
        if(userID.getText().isEmpty()){
            error.setText("Enter user ID ");
            sqlQueryExecuter.
        }
    }

    public void loadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *jpg, *jpeg, *img)", "*.png", "*.jpeg", "*.img", "*jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
            imageView.setImage(new Image(selectedFile.toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
