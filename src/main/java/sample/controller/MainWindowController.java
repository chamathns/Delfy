package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.util.FileHandler;

public class MainWindowController {
    @FXML
    JFXButton buttonSelectFile,buttonSelectEncryptedFile,buttonFileDirectory, btnEncryptPane, btnDecryptPane, btnRecentsPane;
    @FXML
    JFXTextField textFieldEncryptFile, textFieldDecryptFile;
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
    public void filePicker(MouseEvent event){
        if (event.getSource()==buttonSelectFile){
            FileHandler.getInstance().selectFile();
            System.out.println(FileHandler.getInstance().getPath());
            textFieldEncryptFile.setText(FileHandler.getInstance().getPath().toString().trim());

        }else if (event.getSource()==buttonSelectEncryptedFile){
            FileHandler.getInstance().selectEncryptedFile();
            System.out.println(FileHandler.getInstance().getPath());
            textFieldDecryptFile.setText(FileHandler.getInstance().getPath().toString().trim());
        }

    }
}
