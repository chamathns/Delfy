package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

public class LogInControllerTest extends ApplicationTest{

    JFXTextField textFieldName, textFieldEmail, textEmailSignIn;
    JFXButton buttonSignInPane, buttonRegisterPane, buttonSignIn,buttonRegister;
    AnchorPane paneSignIn, paneRegister, paneBlank, paneBlank1;
    JFXPasswordField passwordFieldKey , passwordFieldKey_re, passwordFieldSignIn ;
    Label lblRegisterName,lblRegisterName1, lblRegisterEmail,lblRegisterEmail1,lblRegisterPassphrase,lblRegisterPassphrase1,lblRegisterPassphrase2,
            lblSignInPassphrase,lblSignInEmail,lblSignInNewAccount,lblRegisterUserExists,lblRegisterSignIn;



    /* This operation comes from ApplicationTest and loads the GUI to test. */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/userInterface/logIn.fxml"));
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


        lblRegisterName = find("#lblRegisterName");
        lblRegisterName1 = find("#lblRegisterName1");
        lblRegisterEmail = find("#lblRegisterEmail");
        lblRegisterEmail1 = find("#lblRegisterEmail1");
        lblRegisterPassphrase = find("#lblRegisterPassphrase");
        lblRegisterPassphrase1 = find("#lblRegisterPassphrase1");
        lblRegisterPassphrase2 = find("#lblRegisterPassphrase2");
        lblSignInPassphrase = find("#lblSignInPassphrase");
        lblSignInEmail = find("#lblSignInEmail");
        lblSignInNewAccount = find("#lblSignInNewAccount");
        lblRegisterUserExists = find("#lblRegisterUserExists");
        lblRegisterSignIn = find("#lblRegisterSignIn");

        passwordFieldKey = find("#passwordFieldKey");
        passwordFieldKey_re = find("#passwordFieldKey_re");
        passwordFieldSignIn = find("#passwordFieldSignIn");

        textFieldName = find("#textFieldName");
        textFieldEmail = find("#textFieldEmail");
        textEmailSignIn = find("#textEmailSignIn");

        buttonSignInPane = find("#buttonSignInPane");
        buttonRegisterPane = find("#buttonRegisterPane");
        buttonSignIn = find("#buttonSignIn");
        buttonRegister = find("#buttonRegister");

        paneSignIn = find("#paneSignIn");
        paneRegister = find("#paneRegister");
        paneBlank = find("#paneBlank");
        paneBlank1 = find("#paneBlank1");

    }

//    @Test
//    public void getCurrentUserEmail() {
//
//
//    }
    @Test
    public void handle_signIn_Pane(){
        AtomicBoolean flag1 = new AtomicBoolean(true);
        paneSignIn.sceneProperty().addListener((obs,oldScene,newScene)->{
            if (newScene == null){
                flag1.set(false);
            }
        });
        clickOn(buttonSignInPane);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals(true, flag1.get());
    }
    @Test
    public void handle_Register_Pane(){
        AtomicBoolean flag1 = new AtomicBoolean(false);
        paneRegister.sceneProperty().addListener((obs,oldScene,newScene)->{
            if (newScene == null){
                flag1.set(true);
            }
        });
        clickOn(buttonRegisterPane);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals(false, flag1.get());

    }

    @Test
    public void user_logIn(){

    }

//    @Test
//    public void handleNewAccount() {
//    }
//
//    @Test
//    public void handleRegisterSignIn() {
//    }
//
//    @Test
//    public void handleSignIn() {
//    }
//
//    @Test
//    public void handleRegister() {
//    }
//
//    @Test
//    public void register() {
//    }

    /* IMO, it is quite recommended to clear the ongoing events, in case of. */
    @After
    public void tearDown() throws TimeoutException {
        /* Close the window. It will be re-opened at the next test. */
        FxToolkit.hideStage();
        release(new KeyCode[] {});
        release(new MouseButton[] {});
    }
}