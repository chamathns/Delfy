package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.util.DbConnect;
import sample.util.KeyHandler;
import sample.util.UserData;
import sample.util.UserProfile;
import sun.security.util.Password;


import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    @FXML
    private JFXTextField textFieldName, textFieldEmail;
    @FXML
    private JFXButton buttonSignInPane, buttonRegisterPane, buttonSignIn;
    @FXML
    private AnchorPane paneSignIn, paneRegister;
    @FXML
    private JFXPasswordField passwordFieldKey, passwordFieldKey_re;

    @FXML
    private void handlePane( MouseEvent event) {

        if (event.getSource()==buttonRegisterPane){
            paneRegister.toFront();
        }else if (event.getSource()==buttonSignInPane){
            paneSignIn.toFront();
        }
    }

    public void handleSignIn (MouseEvent event) throws IOException{
        Stage stage;
        Parent root;

        stage=(Stage) buttonSignIn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/sample/userInterface/mainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void handleRegister (MouseEvent event) {
        try{
            String name = textFieldName.getText().trim() ;
            if (!UserData.validateName(name)){
                Alert alert = new Alert(Alert.AlertType.ERROR,"User name is not valid");
                alert.showAndWait();
            }

//            String name = textFieldName.getText().trim() ;
//            String email = textFieldEmail.getText().trim();
//            String passphrase = passwordFieldKey.getText().trim();
//            byte[] salt = KeyHandler.getInstance().generateSalt();
//            byte[] encryptedPassphrase = KeyHandler.getInstance().getEncryptedPassphrase(passphrase,salt);
//            UserProfile user = new UserProfile(name, email, encryptedPassphrase, salt);
//            UserData.getInstance().addUserProfile(user);
//            ObservableList<UserProfile> userProfiles = UserData.getInstance().getUserProfiles();
//
//            Iterator<UserProfile> iterator = userProfiles.iterator(;
//            while (iterator.hasNext()){
//                UserProfile profile = iterator.next();
//                System.out.println("\n\n"+profile.getName() +"\n"+profile.getEmail()+"\n"+(profile.getEncryptedPassphrase().toString())+"\n"+(profile.getSalt().toString()));
//            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void register(){
        try{
            String name = textFieldName.getText().trim() ;
            String email = textFieldEmail.getText().trim();
            String passphrase = passwordFieldKey.getText().trim();
            byte[] salt = KeyHandler.getInstance().generateSalt();
            byte[] encryptedPassphrase = KeyHandler.getInstance().getEncryptedPassphrase(passphrase,salt);
            UserProfile user = new UserProfile(name, email, encryptedPassphrase, salt);
            UserData.getInstance().addUserProfile(user);
            ObservableList<UserProfile> userProfiles = UserData.getInstance().getUserProfiles();

            Iterator<UserProfile> iterator = userProfiles.iterator();
            while (iterator.hasNext()){
                UserProfile profile = iterator.next();
                System.out.println("\n\n"+profile.getName() +"\n"+profile.getEmail()+"\n"+(profile.getEncryptedPassphrase().toString())+"\n"+(profile.getSalt().toString()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserData.getInstance().loadUserProfiles();

    }
}
