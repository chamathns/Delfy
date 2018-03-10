package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.io.FilenameUtils;
import sample.algorithms.AES;
import sample.util.FileHandler;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private static final String masterKey = "2EA45ED5B21E3%K^";
    private String algorithm, en_passphrase, de_passphrase, encryptedFileName, filename;
    private byte[] encryptedData, decryptedData;
    @FXML
    JFXButton buttonSelectFile,buttonSelectEncryptedFile,buttonFileDirectory, btnEncryptPane, btnDecryptPane,
            btnRecentsPane, buttonDecryptDirectory, buttonEncrypt;
    @FXML
    JFXTextField textFieldEncryptFile, textFieldDecryptFile, textEncryptDirectory, textDecryptDirectory;
    @FXML
    AnchorPane paneEncrypt, paneDecrypt, paneRecents;
    @FXML
    JFXComboBox algoCombo;

    public void setAlgoCombo(JFXComboBox algoCombo) {
        this.algoCombo = algoCombo;
        algoCombo.getItems().setAll("AES","DES","Blowfish","IDEA");
        algoCombo.setValue(this.algoCombo.getItems().get(0));
    }

    public String getAlgorithm() {
        return algorithm;
    }
    public void setAlgorithm() {
        this.algorithm = algoCombo.getValue().toString();
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
        }else if (event.getSource()==btnRecentsPane){
            paneRecents.toFront();
        }
    }
    public void filePicker(MouseEvent event){
        if (event.getSource()==buttonSelectFile){
            File file = FileHandler.getInstance().selectFile();
            filename = FilenameUtils.getBaseName(file.getName());

            if (file!= null){
                textFieldEncryptFile.setText(file.getPath().toString().trim());
            }

        }else if (event.getSource()==buttonSelectEncryptedFile){
            File en_file = FileHandler.getInstance().selectEncryptedFile();
            encryptedFileName = FilenameUtils.getBaseName(en_file.getName());
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
        setAlgorithm();
        System.out.println(filename);
        byte[] data = FileHandler.readFile(textFieldEncryptFile.getText().trim());
//        String ex1 = FilenameUtils.getExtension(textFieldEncryptFile.getText().trim());
//        System.out.println(ex1);

        AES.setKeyValue(masterKey.getBytes(StandardCharsets.UTF_8));
        System.out.println(getAlgorithm());
        try {
            encryptedData = AES.encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            decryptedData = AES.decrypt(encryptedData);
        }catch (Exception e){
            e.printStackTrace();
        }
        String s = new String(data);
        String se = new String(getEncryptedData());
        String sd = new String(getDecryptedData());
        System.out.println("Original data: "+ s);
        System.out.println("Encrypted data: "+ se);
        System.out.println("Decrypted data: "+ sd);
        FileHandler.writeFile((textEncryptDirectory.getText().trim() + "\\file.enc"),getEncryptedData());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAlgoCombo(algoCombo);

    }
}
