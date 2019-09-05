package Controller;

import Constant.RequestFile;
import Main.Main;
import RequestClasses.RegisterData;
import RequestClasses.Response;
import RequestClasses.UserID;
import Utilities.FXMLInitiator;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static Main.Main.*;

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

    private static final String USERNAME_PATTERN = "^[\\p{L} .'-]+$";
    private static final String USERID_PATTERN = "^(?=.*[a-z])[a-z0-9]{4,20}$";
    private static final String PASSWORD_PATTERN ="((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,16})";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONENO_PATTERN = "\\d{10}";

    private String extension = null;
    private File selectedFile = null ;

    private void setErrorLabel(String str){
        error.setText(str);
    }

    public void initialize(){
        try {
            selectedFile = new File("src/Icons/newUserIcon.png");
            extension = "png";
//            System.out.println(file.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toURL().toString());
            imageView.setImage(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void register(ActionEvent event) throws Exception {

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
            if(userIDText.length()<4 || userIDText.length()>20){
                setErrorLabel("UserID must be between 4 to 20 characters");
                return;
            }
            if(Pattern.compile(USERID_PATTERN).matcher(userNameText).matches() == false){
                setErrorLabel("Invalid user ID entered");
                return;
            }
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
            if(passwordText.length()<6 || passwordText.length()>16) {
                setErrorLabel("Password length inappropriate");
                return;
            }if(Pattern.compile(PASSWORD_PATTERN).matcher(passwordText).matches() == false){
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
            if(Pattern.compile(PHONENO_PATTERN).matcher(phoneNoText).matches() == false){
                setErrorLabel("Invalid phone number");
                return;
            }
        }

        error.setText("");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String lastOnline = dtf.format(now);
//		    System.out.println(dtf.format(now));

        GAMER.send_message(new RegisterData(userNameText, userIDText, passwordText, emailText, phoneNoText, extension, lastOnline, 0));
        Response response = (Response) GAMER.receive_message();

        FILEGAMER.sendFile(selectedFile.getAbsolutePath(), RequestFile.PROFILEPICTURE.ordinal(), userIDText + "." + extension);
        System.out.println("Output sent");
        Response fileResponse = (Response) FILEGAMER.receiveResponse();
        System.out.println("Response received");

        System.out.println(fileResponse.getStatus());

//      To be handled

//        if(response.getStatus()==1 || fileResponse == false){
//            setErrorLabel("Network issue in sending the details to server");
//            return;
//        }

//        System.out.println(selectedFile.getAbsolutePath());

        FXMLInitiator fxmlInitiator = new FXMLInitiator("../FXML/MainScreen.fxml");
        fxmlInitiator.start(PRIMARYSTAGE);

    }

    public void loadImage(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *jpg, *jpeg, *img)", "*.png", "*.jpeg", "*.img", "*jpg");
        fileChooser.getExtensionFilters().add(extFilter);

//        System.out.println(extension);
//        System.out.println(selectedFile.getAbsolutePath());

        File selectedFileTemp = (fileChooser.showOpenDialog(null));
        selectedFile = (selectedFileTemp == null)? selectedFile : selectedFileTemp;

        try {
            if(selectedFile != null){

                Image image = new Image(selectedFile.toURI().toURL().toString());
                imageView.setImage(image);
                FileExtension fileExtension = new FileExtension(selectedFile);
                extension = fileExtension.getFileExtension();
                System.out.println(extension);
            }
        } catch (MalformedURLException e) {
//            e.printStackTrace();
        }


    }


}
