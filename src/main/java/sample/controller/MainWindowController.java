package sample.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import sample.algorithms.AES;
import sample.algorithms.Algorithm;
import sample.util.FileHandler;
import sample.util.KeyHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private static final String masterKey = "2EA45ED5B21E3%K^";
    private String algorithm, en_passphrase, de_passphrase, encryptedFileName, filename, extension;
    private byte[] encryptedData, decryptedData;
    @FXML
    private JFXButton buttonSelectFile,buttonSelectEncryptedFile,buttonFileDirectory, btnEncryptPane, btnDecryptPane,
            btnRecentsPane, buttonDecryptDirectory, buttonEncrypt;
    @FXML
    private JFXTextField textFieldEncryptFile, textFieldDecryptFile, textEncryptDirectory, textDecryptDirectory;
    @FXML
    private AnchorPane paneEncrypt, paneDecrypt, paneRecents;
    @FXML
    private JFXComboBox algoCombo, algoComboDec;
    @FXML
    private JFXPasswordField passwordFieldEncrypt, passwordFieldDecrypt;
    @FXML
    private StackPane mainStackPane ;


    public void setAlgoCombo(JFXComboBox algoCombo) {
        algoCombo.getItems().setAll("AES", "Blowfish", "DES", "RC2");
        algoCombo.setValue(algoCombo.getItems().get(0));
    }

    public String getAlgorithm() {
        return algorithm;
    }
    public void setAlgorithm() {
        this.algorithm = algoCombo.getValue().toString();
    }
    public void setDecAlgorithm(){
        this.algorithm = algoComboDec.getValue().toString();
    }


    public byte[] getEncryptedData() {
        return encryptedData;
    }

    public byte[] getDecryptedData() {
        return decryptedData;
    }

    @FXML
    private void handlePane(MouseEvent event){
        if (event.getSource()==btnEncryptPane){
            paneEncrypt.toFront();
        }else if (event.getSource()==btnDecryptPane){
            paneDecrypt.toFront();
        }
    }
    @FXML
    public void showRecentFilesDialog(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/userInterface/recentFilesWindow.fxml"));
        JFXDialogLayout content = new JFXDialogLayout();
        StackPane pane = fxmlLoader.load();

        JFXDialog dialog1 = new JFXDialog(mainStackPane,pane,JFXDialog.DialogTransition.CENTER);
        dialog1.show();


    }

    public void filePicker(MouseEvent event){
        try{
            if (event.getSource()==buttonSelectFile){
                File file = FileHandler.getInstance().selectFile();
                filename = FilenameUtils.getBaseName(file.getName());
                extension = FileHandler.getInstance().getExtension();
                if (file!= null){
                    textFieldEncryptFile.setText(file.getPath().toString().trim());
                }

            }else if (event.getSource()==buttonSelectEncryptedFile){
                FileHandler instance = FileHandler.getInstance();
                File en_file = instance.selectEncryptedFile();
                encryptedFileName = FilenameUtils.getBaseName(en_file.getName());
                extension = instance.getExtension();
                if (en_file!= null){
                    textFieldDecryptFile.setText(en_file.getPath().toString().trim());
                }
            }
        }catch (Exception e){
//            errorHandler("File not selected");
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
    public void encrypt(MouseEvent event) {
        setAlgorithm();
        String algo = getAlgorithm();
        byte[] data = FileHandler.readFile(textFieldEncryptFile.getText().trim());
        System.out.println("Encrypting with: " + algo);
        Algorithm.setALGO(algo);
        Algorithm.setKeyValue(KeyHandler.generateKey(passwordFieldEncrypt.getText().trim()));
        try {
            encryptedData = Algorithm.encrypt(data);
        } catch (Exception e) {
            System.out.println(e.getClass().toString());
            if (e.getClass() == IOException.class){

            }
        }
        FileHandler.writeFile((textEncryptDirectory.getText().trim() + "\\" + filename + "." + extension + ".enc"), getEncryptedData());
        File newfile = new File(textEncryptDirectory.getText().trim() + "\\Encrypted " + filename + ".txt");
        newfile.setReadOnly();
        System.out.println("Encryption completed!");
        textFieldEncryptFile.clear();
        passwordFieldEncrypt.clear();
        textEncryptDirectory.clear();
    }

    public void decrypt(MouseEvent event){
        setDecAlgorithm();
        String algo = getAlgorithm();
        byte[] encryptedData = FileHandler.readFile(textFieldDecryptFile.getText().trim());
        System.out.println("Decrypting with: " + algo);
        Algorithm.setALGO(algo);
        Algorithm.setKeyValue(KeyHandler.generateKey(passwordFieldDecrypt.getText().trim()));
        try {
            decryptedData = AES.decrypt(encryptedData);
        }catch (Exception e){
            e.printStackTrace();
        }
        FileHandler.writeFile((textDecryptDirectory.getText().trim() + "\\Decrypted (" + FilenameUtils.getBaseName(encryptedFileName) +")."+extension),getDecryptedData());
        System.out.println("Decryption completed!");
        textFieldDecryptFile.clear();
        textDecryptDirectory.clear();
        passwordFieldDecrypt.clear();
    }
//    public void errorHandler(String promptText){
//        JFXDialogLayout content = new JFXDialogLayout();
//        content.setHeading(new Text("Error"));
//        content.setBody(new Text(promptText));
//        JFXDialog dialog = new JFXDialog(mainStackPane,content,JFXDialog.DialogTransition.CENTER);
//        dialog.show();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAlgoCombo(algoCombo);
        setAlgoCombo(algoComboDec);
    }
}
