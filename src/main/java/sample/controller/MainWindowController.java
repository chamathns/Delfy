package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.util.FileHandler;

import java.io.File;

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
            File de_file = FileHandler.getInstance().selectFile();
            if (de_file!= null){
                textFieldEncryptFile.setText(FileHandler.getInstance().getPath().toString().trim());
            }


        }else if (event.getSource()==buttonSelectEncryptedFile){
            File en_file = FileHandler.getInstance().selectEncryptedFile();
            if (en_file!= null){
                textFieldDecryptFile.setText(FileHandler.getInstance().getPath().toString().trim());
            }
        }

    }
}
