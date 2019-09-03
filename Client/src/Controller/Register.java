package Controller;

import Main.Main;
import RequestClasses.RegisterData;
import RequestClasses.Response;
import RequestClasses.UserID;
import Utilities.FileExtension;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static Main.Main.GAMER;

public class Register {

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

    private static final String USERNAME_PATTERN = "^[\\p{L} .'-]+$";
    private static final String PASSWORD_PATTERN ="((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,16})";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONENO_PATTERN = "\\d{10}";

    private String extension = null;
    private Image image = null;

    public void register(ActionEvent event) throws IOException, ClassNotFoundException {

        String userNameText = userName.getText();
        if(userNameText.isEmpty()){
            setErrorLabel("User Name cannot be empty");
            return;
        }
        else{
            System.out.println(userNameText);
            if(Pattern.compile(USERNAME_PATTERN).matcher(userNameText).matches() == false){
                setErrorLabel("Invalid user name entered");
                return;
            }
        }

        String userIDText = userID.getText();
        if(userIDText.isEmpty()) {
            setErrorLabel("User ID cannot be empty");
            return;
        }
        else{
            System.out.println("Hello");
            GAMER.send_message(new UserID(userIDText));
            Response res = (Response) GAMER.receive_message();
            if(res.getStatus() == 1){
                setErrorLabel(res.getErrorMessage());
                return;
            }
        }

        String passwordText = password.getText();
        if(passwordText.isEmpty()){
            setErrorLabel("Password cannot be empty");
            return;
        }
        else{
//            System.out.println(passwordText);
//            if(passwordText.length()<6 || passwordText.length()>16) {
//                setErrorLabel("Password length inappropriate");
//                return;
//            }if(Pattern.compile(PASSWORD_PATTERN).matcher(passwordText).matches() == false){
//                setErrorLabel("Invalid password entered");
//                return;
//            }
        }

        String emailText = email.getText();
        if(emailText.isEmpty()){
            setErrorLabel("Email ID cannot be empty");
            return;
        }
        else{
//            if(Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE).matcher(emailText).matches() == false){
//                setErrorLabel("Invalid email entered");
//                return;
//            }
        }

        String phoneNoText = phoneNo.getText();
        if(phoneNoText.isEmpty()){
            setErrorLabel("Phone number cannot be empty");
            return;
        }
        else{
//            if(Pattern.compile(PHONENO_PATTERN).matcher(phoneNoText).matches() == false){
//                setErrorLabel("Invalid phone number");
//                return;
//            }
        }

        error.setText("");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String lastOnline = dtf.format(now);
//		    System.out.println(dtf.format(now));

        GAMER.send_message(new RegisterData(userNameText, userIDText, passwordText, emailText, phoneNoText, extension, lastOnline, image, 0));
        Response response = (Response) GAMER.receive_message();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/DashBoardMainScreen.fxml"));
        Main.MAIN = new Scene(root);
        Main.PRIMARYSTAGE.setScene(Main.MAIN);
        Main.PRIMARYSTAGE.show();
    }

    public void loadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *jpg, *jpeg, *img)", "*.png", "*.jpeg", "*.img", "*jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
            image = new Image(selectedFile.toURI().toURL().toString());
            imageView.setImage(image);
            FileExtension fileExtension = new FileExtension(selectedFile);
            extension = fileExtension.getFileExtension();
//            System.out.println(extension);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
