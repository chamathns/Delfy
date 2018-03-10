package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.util.FileHandler;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    JFXButton buttonSelectFile,buttonSelectEncryptedFile,buttonFileDirectory, btnEncryptPane, btnDecryptPane,
            btnRecentsPane, buttonDecryptDirectory, buttonEncrypt;
    @FXML
    JFXTextField textFieldEncryptFile, textFieldDecryptFile, textEncryptDirectory, textDecryptDirectory;
    @FXML
    AnchorPane paneEncrypt, paneDecrypt, paneRecents;
    @FXML
    JFXComboBox algoCombo;

    @FXML
    ComboBox comboBox;

    public void setAlgoCombo(JFXComboBox algoCombo) {
        this.algoCombo = algoCombo;
        algoCombo.getItems().setAll("AES","DES","Blowfish","IDEA");
        algoCombo.setValue(this.algoCombo.getItems().get(0));
    }

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
                textFieldEncryptFile.setText(de_file.getPath().toString().trim());
            }

        }else if (event.getSource()==buttonSelectEncryptedFile){
            File en_file = FileHandler.getInstance().selectEncryptedFile();
            if (en_file!= null){
                 textFieldDecryptFile.setText(en_file.getPath().toString().trim());
            }
        }

    }

    public void directoryPicker(MouseEvent event){
        if (event.getSource()==buttonFileDirectory){
            File en_directory = FileHandler.getInstance().selectDirectory();
            if (en_directory!= null){
                textEncryptDirectory.setText(en_directory.getPath().toString().trim());
            }
        } else  if (event.getSource()== buttonDecryptDirectory){
            File de_directory = FileHandler.getInstance().selectDirectory();
            if(de_directory!= null){
                textDecryptDirectory.setText(de_directory.getPath().toString().trim());
            }
        }
    }
    public void encrypt(MouseEvent event){
        if (event.getSource()==buttonEncrypt){
            System.out.println(algoCombo.getValue().toString());
        }
    }

//    public void algoComboBox(){
//
//    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAlgoCombo(algoCombo);

    }
}
