package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.security.util.Password;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    @FXML
    private static JFXTextField textFieldName, textFieldEmail;
    @FXML
    private static JFXButton buttonSignInPane, buttonRegisterPane, buttonSignIn;
    @FXML
    private static AnchorPane paneSignIn, paneRegister;
    @FXML
    private static JFXPasswordField passwordFieldKey, passwordFieldKey_re;
    @FXML
    private static PasswordField passwordField;

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
    public static void handleRegister (MouseEvent event){
        String name = textFieldName.getText().trim();
        String email = textFieldEmail.getText().trim();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
