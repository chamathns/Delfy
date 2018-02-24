package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    @FXML
    JFXButton buttonSignInPane, buttonRegisterPane, buttonSignIn;
    @FXML
    AnchorPane paneSignIn, paneRegister;
    @FXML
    public void handlePane( MouseEvent event) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
