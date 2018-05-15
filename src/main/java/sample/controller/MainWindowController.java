package sample.controller;

import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.io.FilenameUtils;
import sample.Main;
import sample.algorithms.AES;
import sample.algorithms.Algorithm;
import sample.util.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private static final String masterKey = "2EA45ED5B21E3%K^";
    private String algorithm, en_passphrase, de_passphrase, encryptedFileName, filename, extension;
    private byte[] encryptedData, decryptedData;
    @FXML
    private JFXButton buttonSelectFile,buttonSelectEncryptedFile,buttonFileDirectory, btnEncryptPane, btnDecryptPane,
            btnRecentsPane, buttonDecryptDirectory, buttonEncrypt, buttonSignOut;
    @FXML
    private Label lblFileEncrypt,lblPassphraseEncrypt,lblPassphraseEncrypt1,lblPassphraseEncrypt2,lblDirectoryEncrypt,
            lblFileDecrypt,lblPassphraseDecrypt,lblDecryptDirectory;
    @FXML
    private JFXTextField textFieldEncryptFile, textFieldDecryptFile, textEncryptDirectory, textDecryptDirectory;
    @FXML
    private AnchorPane paneEncrypt, paneDecrypt, paneBlankDecrypt, paneBlankEncrypt;
    @FXML
    private JFXComboBox algoCombo, algoComboDec;
    @FXML
    private JFXPasswordField passwordFieldEncrypt,passwordFieldEncrypt1, passwordFieldDecrypt;
    @FXML
    private StackPane mainStackPane ;
    private double xOffset = 0;
    private double yOffset = 0;

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
            Effects.sceneAnimator(paneEncrypt,1000,Interpolator.LINEAR);
            paneBlankEncrypt.toBack();
            paneBlankDecrypt.toFront();
            paneEncrypt.toFront();
        }else if (event.getSource()==btnDecryptPane){
            Effects.sceneAnimator(paneDecrypt,1000,Interpolator.LINEAR);
            paneBlankDecrypt.toBack();
            paneBlankEncrypt.toFront();
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

    @FXML
    public void handleSignOut(MouseEvent event) throws IOException {


        Stage stageLogIn;
        Parent root;

        stageLogIn=(Stage) buttonSignOut.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/sample/userInterface/logIn.fxml"));
        root.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            stageLogIn.setX(e.getScreenX() - xOffset);
            stageLogIn.setY(e.getScreenY() - yOffset);
        });
        Effects.sceneAnimator(root,2000,Interpolator.EASE_IN);

        Scene scene = new Scene(root);
        stageLogIn.setScene(scene);
        stageLogIn.setResizable(false);
        stageLogIn.show();
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
    public void saveFileData(){
        Path path = Paths.get(textFieldEncryptFile.getText().trim());
        String fileName = path.getFileName().toString();
        String encryptedDirectory = Paths.get(textEncryptDirectory.getText().trim()).toString();
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        FileModule fileModule = new FileModule(fileName,timeStamp,encryptedDirectory,getAlgorithm());
        FileData.getInstance().addFileModules(fileModule);
        FileData.updateRecentFiles(LogInController.getCurrentUserEmail(),fileModule);
    }
    
    public void encrypt(MouseEvent event) {
        Effects.sceneAnimator(lblFileEncrypt,1000,Interpolator.EASE_OUT);
        lblFileEncrypt.setVisible(false);
        Effects.sceneAnimator(lblPassphraseEncrypt,1000,Interpolator.EASE_OUT);
        lblPassphraseEncrypt.setVisible(false);
        Effects.sceneAnimator(lblPassphraseEncrypt1,1000,Interpolator.EASE_OUT);
        lblPassphraseEncrypt1.setVisible(false);
        Effects.sceneAnimator(lblPassphraseEncrypt2,1000,Interpolator.EASE_OUT);
        lblPassphraseEncrypt2.setVisible(false);
        Effects.sceneAnimator(lblDirectoryEncrypt,1000,Interpolator.EASE_OUT);
        lblDirectoryEncrypt.setVisible(false);

        if (textFieldEncryptFile.getText().equals("")){
            Effects.sceneAnimator(lblFileEncrypt,1000,Interpolator.EASE_IN);
            lblFileEncrypt.setVisible(true);

        }
        if (passwordFieldEncrypt.getText().trim().equals("")){
            Effects.sceneAnimator(lblPassphraseEncrypt,1000,Interpolator.EASE_IN);
            lblPassphraseEncrypt.setVisible(true);

        }
        if (passwordFieldEncrypt1.getText().trim().equals("")){
            Effects.sceneAnimator(lblPassphraseEncrypt2,1000, Interpolator.EASE_IN);
            lblPassphraseEncrypt2.setVisible(true);
        }
        if (!(passwordFieldEncrypt.getText().trim()).equals(passwordFieldEncrypt1.getText().trim()) && !passwordFieldEncrypt1.getText().equals("")){
            Effects.sceneAnimator(lblPassphraseEncrypt1,1000, Interpolator.EASE_IN);
            lblPassphraseEncrypt1.setVisible(true);

        }
        if (textEncryptDirectory.getText().equals("")){
            Effects.sceneAnimator(lblDirectoryEncrypt,1000,Interpolator.EASE_IN);
            lblDirectoryEncrypt.setVisible(true);
        }
        else
        {
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Encryption successful!");
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.initStyle(StageStyle.UTILITY);
            Effects.mediaAlert().play();
            alert.show();
            saveFileData();
            encryptedData = null;
            textFieldEncryptFile.clear();
            passwordFieldEncrypt.clear();
            passwordFieldEncrypt1.clear();
            textEncryptDirectory.clear();

        }

    }

    public void decrypt(MouseEvent event){
        Effects.sceneAnimator(lblFileDecrypt,1000,Interpolator.EASE_OUT);
        lblFileDecrypt.setVisible(false);
        Effects.sceneAnimator(lblPassphraseDecrypt,1000,Interpolator.EASE_OUT);
        lblPassphraseDecrypt.setVisible(false);
        Effects.sceneAnimator(lblDecryptDirectory,1000,Interpolator.EASE_OUT);
        lblDecryptDirectory.setVisible(false);

        if (textFieldDecryptFile.getText().trim().equals("")){
            Effects.sceneAnimator(lblFileDecrypt,1000,Interpolator.EASE_IN);
            lblFileDecrypt.setVisible(true);
        }
        if (passwordFieldDecrypt.getText().trim().equals("")){
            Effects.sceneAnimator(lblPassphraseDecrypt,1000,Interpolator.EASE_IN);
            lblPassphraseDecrypt.setVisible(true);
        }
        if (textDecryptDirectory.getText().trim().equals("")){
            Effects.sceneAnimator(lblDecryptDirectory,1000,Interpolator.EASE_IN);
            lblDecryptDirectory.setVisible(true);
        }
        else{
            setDecAlgorithm();
            String algo = getAlgorithm();
            byte[] encryptedData = FileHandler.readFile(textFieldDecryptFile.getText().trim());
            System.out.println("Decrypting with: " + algo);
            Algorithm.setALGO(algo);
            Algorithm.setKeyValue(KeyHandler.generateKey(passwordFieldDecrypt.getText().trim()));
            try {
                decryptedData = AES.decrypt(encryptedData);
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Algorithm or the passphrase doesn't match");
                alert.setHeaderText(null);
                alert.setTitle(null);
                alert.initStyle(StageStyle.TRANSPARENT);

                Effects.mediaError().play();
                alert.show();
            }
            try{
                FileHandler.writeFile((textDecryptDirectory.getText().trim() + "\\Decrypted (" + FilenameUtils.getBaseName(encryptedFileName) +")."+extension),getDecryptedData());
            }catch (Exception e){
//                e.printStackTrace();
            }
            if (decryptedData!= null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Decryption successful!");
                alert.setHeaderText(null);
                alert.setTitle(null);
                alert.initStyle(StageStyle.UTILITY);
                Effects.mediaAlert().play();
                alert.show();
                System.out.println("Decryption completed!");
            }

            textFieldDecryptFile.clear();
            textDecryptDirectory.clear();
            passwordFieldDecrypt.clear();
            decryptedData = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        DbConnect.getInstance().connect();
        setAlgoCombo(algoCombo);
        setAlgoCombo(algoComboDec);

    }
}
