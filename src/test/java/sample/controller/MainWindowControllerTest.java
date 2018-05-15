package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

public class MainWindowControllerTest extends ApplicationTest {

    JFXButton buttonSelectFile,buttonSelectEncryptedFile,buttonFileDirectory, btnEncryptPane, btnDecryptPane,
             buttonDecryptDirectory, buttonSignOut;
    Label lblFileEncrypt,lblPassphraseEncrypt,lblPassphraseEncrypt1,lblPassphraseEncrypt2,lblDirectoryEncrypt,
            lblFileDecrypt,lblPassphraseDecrypt,lblDecryptDirectory;
    JFXTextField textFieldEncryptFile, textFieldDecryptFile, textEncryptDirectory, textDecryptDirectory;
    AnchorPane paneEncrypt, paneDecrypt, paneBlankDecrypt, paneBlankEncrypt;
    JFXComboBox algoCombo, algoComboDec;
    JFXPasswordField passwordFieldEncrypt,passwordFieldEncrypt1, passwordFieldDecrypt;
    StackPane mainStackPane ;

    /* This operation comes from ApplicationTest and loads the GUI to test. */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/userInterface/mainWindow.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }
    /* Just a shortcut to retrieve widgets in the GUI. */
    public <T extends Node> T find(final String query) {
        /** TestFX provides many operations to retrieve elements from the loaded GUI. */
        return lookup(query).query();
    }

    @Before
    public void setup(){
        buttonSelectFile = find("#buttonSelectFile");
        buttonSelectEncryptedFile = find("#buttonSelectEncryptedFile");
        buttonFileDirectory = find("#buttonFileDirectory");
        btnEncryptPane = find("#btnEncryptPane");
        btnDecryptPane = find("#btnDecryptPane");
        buttonDecryptDirectory = find("#buttonDecryptDirectory");
        buttonSignOut = find("#buttonSignOut");

        lblFileEncrypt = find("#lblFileEncrypt");
        lblPassphraseEncrypt = find("#lblPassphraseEncrypt");
        lblPassphraseEncrypt1 = find("#lblPassphraseEncrypt1");
        lblPassphraseEncrypt2 = find("#lblPassphraseEncrypt2");
        lblDirectoryEncrypt = find("#lblDirectoryEncrypt");
        lblFileDecrypt = find("#lblFileDecrypt");
        lblPassphraseDecrypt = find("#lblPassphraseDecrypt");
        lblDecryptDirectory = find("#lblDecryptDirectory");

        textFieldEncryptFile = find("#textFieldEncryptFile");
        textFieldDecryptFile = find("#textFieldDecryptFile");
        textEncryptDirectory = find("#textEncryptDirectory");
        textDecryptDirectory = find("#textDecryptDirectory");

        paneEncrypt = find("#paneEncrypt");
        paneDecrypt = find("#paneDecrypt");
        paneBlankDecrypt = find("#paneBlankDecrypt");
        paneBlankEncrypt = find("#paneBlankEncrypt");

        algoCombo = find("#algoCombo");
        algoComboDec = find("#algoComboDec");

        passwordFieldEncrypt = find("#passwordFieldEncrypt");
        passwordFieldEncrypt1 = find("#passwordFieldEncrypt1");
        passwordFieldDecrypt = find("#passwordFieldDecrypt");

        mainStackPane = find("#mainStackPane");
    }


    @Test
    public void handlePane_Encrypt(){
        AtomicBoolean flag1 = new AtomicBoolean(true);
        paneEncrypt.sceneProperty().addListener((obs,oldScene,newScene)->{
            if (newScene == null){
                flag1.set(false);
            }
        });
        clickOn(btnEncryptPane);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals(true, flag1.get());
    }
    @Test
    public void handlePane_Decrypt(){
        AtomicBoolean flag1 = new AtomicBoolean(true);
        paneDecrypt.sceneProperty().addListener((obs,oldScene,newScene)->{
            if (newScene == null){
                flag1.set(false);
            }
        });
        clickOn(btnDecryptPane);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals(true, flag1.get());
    }
    @Test
    public void setAlgoCombo() {
        algoCombo.getItems().setAll("AES", "Blowfish", "DES", "RC2");
        algoCombo.setValue(algoCombo.getItems().get(0));
        String algorithm = algoCombo.getValue().toString();
        assertEquals("AES",algorithm);
    }

//    @Test
//    public void getAlgorithm() {
//    }
//
//    @Test
//    public void setAlgorithm() {
//    }
//
//    @Test
//    public void setDecAlgorithm() {
//    }
//
//    @Test
//    public void getEncryptedData() {
//    }
//
//    @Test
//    public void getDecryptedData() {
//    }
//
//    @Test
//    public void showRecentFilesDialog() {
//    }
//
//    @Test
//    public void handleSignOut() {
//
//    }
//
//    @Test
//    public void filePicker() {
//    }
//
//    @Test
//    public void directoryPicker() {
//    }
//
//    @Test
//    public void saveFileData() {
//    }
//
//    @Test
//    public void encrypt() {
//    }
//
//    @Test
//    public void decrypt() {
//    }
}