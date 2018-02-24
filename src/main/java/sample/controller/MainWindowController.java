package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainWindowController {
    @FXML
    JFXButton buttonSelectFile,buttonFileDirectory, btnEncryptPane, btnDecryptPane, btnRecentsPane;
    @FXML
    AnchorPane paneEncrypt, paneDecrypt, paneRecents;
    @FXML
    private void handlePane(MouseEvent event){
        if (event.getSource()==btnEncryptPane){
            paneEncrypt.toFront();
        }else if (event.getSource()==btnDecryptPane){
            paneDecrypt.toFront();
        }else if (event.getSource()==btnRecentsPane){
            paneRecents.toFront();
        }
    }
}
