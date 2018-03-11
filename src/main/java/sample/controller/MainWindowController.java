package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.io.FilenameUtils;
import sample.algorithms.AES;
import sample.algorithms.Algorithm;
import sample.algorithms.Blowfish;
import sample.util.FileHandler;
import sample.util.KeyHandler;

import java.io.File;
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
    private JFXComboBox algoCombo;
    @FXML
    private JFXPasswordField passwordFieldEncrypt, passwordFieldDecrypt;


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
            e.printStackTrace();
        }
        FileHandler.writeFile((textEncryptDirectory.getText().trim() + "\\" + filename + "." + extension + ".enc"), getEncryptedData());
        File newfile = new File(textEncryptDirectory.getText().trim() + "\\Encrypted " + filename + ".txt");
        newfile.setReadOnly();
    }

    public void decrypt(MouseEvent event){
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAlgoCombo(algoCombo);

    }
}
