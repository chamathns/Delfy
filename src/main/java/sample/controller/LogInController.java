package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    @FXML
    JFXButton buttonSignInPane, buttonRegisterPane;
    @FXML
    AnchorPane paneSignIn, paneRegister;
    @FXML
    public void handlePane( MouseEvent event){
        if (event.getSource()==buttonRegisterPane){
            paneRegister.toFront();
        }else if (event.getSource()==buttonSignInPane){
            paneSignIn.toFront();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
