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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.PATTERN;

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

    private void setErrorLabel(String str){
        error.setText(str);
    }

    private static final String PASSWORD_PATTERN ="((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONENO_PATTERN = "\\d{10}";

    SqlQueryExecuter sqlQueryExecuter = new SqlQueryExecuter("root","","jdbc:mysql://localhost/sakshatkar");

    public void register(ActionEvent event) throws SQLException {

        String userNameText = userName.getText();
        if(userNameText.isEmpty()){
            setErrorLabel("User Name cannot be empty");
            return;
        }

        String userIDText = userID.getText();
        boolean isValid = true;
        if(userIDText.isEmpty()) {
            setErrorLabel("User ID cannot be empty");
            return;
        }
        else{
            error.setText("Enter user ID ");
            ResultSet rs = sqlQueryExecuter.select("SELECT userID FROM user");
            while(rs.next()){
                String userID = rs.getString("userID");
                if(userID.equals(userIDText)){
                    isValid = false;
                    break;
                }
            }
        }
        if(isValid == false) {
            setErrorLabel("User ID already exists. ");
            return;
        }

        String passwordText = password.getText();
        if(passwordText.isEmpty()){
            setErrorLabel("Password cannot be empty");
            return;
        }
        else{
            if(Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE).matcher(passwordText).matches() == false){
                setErrorLabel("Invalid password entered");
                return;
            }
        }

        String emailText = email.getText();
        if(emailText.isEmpty()){
            setErrorLabel("Email ID cannot be empty");
            return;
        }
        else{
            if(Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE).matcher(emailText).matches() == false){
                setErrorLabel("Invalid email entered");
                return;
            }
        }

        String phoneNoText = phoneNo.getText();
        if(phoneNoText.isEmpty()){
            setErrorLabel("Phone number cannot be empty");
            return;
        }
        else{
            if(Pattern.compile(PHONENO_PATTERN, Pattern.CASE_INSENSITIVE).matcher(phoneNoText).matches() == false){
                setErrorLabel("Invalid phone number");
                return;
            }
        }

        error.setText("");
        RegisterData registerData = new RegisterData(userNameText, userIDText, passwordText, emailText, phoneNoText);

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
