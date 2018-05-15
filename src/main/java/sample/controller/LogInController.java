package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.bson.Document;
import org.controlsfx.control.Notifications;
import sample.util.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    public static ArrayList<Document> userProfiles;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private JFXTextField textFieldName, textFieldEmail, textEmailSignIn;
    @FXML
    private Label lblRegisterName,lblRegisterName1, lblRegisterEmail,lblRegisterEmail1,lblRegisterPassphrase,lblRegisterPassphrase1,lblRegisterPassphrase2,
            lblSignInPassphrase,lblSignInEmail,lblSignInNewAccount,lblRegisterUserExists,lblRegisterSignIn;
    @FXML
    private JFXButton buttonSignInPane, buttonRegisterPane, buttonSignIn,buttonRegister;
    @FXML
    private AnchorPane paneSignIn, paneRegister, paneBlank, paneBlank1;
    @FXML
    private JFXPasswordField passwordFieldKey , passwordFieldKey_re, passwordFieldSignIn ;

    private static String currentUserEmail;

    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }

    @FXML
    private void handlePane( MouseEvent event) {

        if (event.getSource()==buttonRegisterPane){
            Effects.sceneAnimator(paneRegister,1000,Interpolator.LINEAR);
            paneBlank1.toBack();
            paneBlank.toFront();
            paneRegister.toFront();


        }else if (event.getSource()==buttonSignInPane){
            Effects.sceneAnimator(paneSignIn,1000,Interpolator.LINEAR);
            paneBlank.toBack();
            paneBlank1.toFront();
            paneSignIn.toFront();
        }
    }
    @FXML
    public void handleNewAccount_style(MouseEvent event){
        lblSignInNewAccount.setUnderline(true);
        lblSignInNewAccount.setOpacity(1.0);
        lblSignInNewAccount.setStyle("-fx-text-fill: #0272A6");
        lblSignInNewAccount.setCursor(Cursor.HAND);
    }
    @FXML
    public void handleNewAccount(MouseEvent event){
        Effects.sceneAnimator(paneRegister,1000,Interpolator.LINEAR);
        paneBlank1.toBack();
        paneBlank.toFront();
        paneRegister.toFront();
        textEmailSignIn.clear();
        passwordFieldSignIn.clear();
        lblSignInNewAccount.setVisible(false);
        lblSignInEmail.setVisible(false);
    }
    @FXML
    public void handleRegisterSignIn(MouseEvent event){
        textEmailSignIn.setText(textFieldEmail.getText().trim());
        Effects.sceneAnimator(paneSignIn,1000,Interpolator.LINEAR);
        paneBlank.toBack();
        paneBlank1.toFront();
        paneSignIn.toFront();
        textFieldName.clear();
        textFieldEmail.clear();
        lblRegisterUserExists.setVisible(false);
        lblRegisterSignIn.setVisible(false);
        passwordFieldKey.clear();
        passwordFieldKey_re.clear();
    }
    @FXML
    public void handleRegisterSignIn_style(MouseEvent event){
        lblRegisterSignIn.setUnderline(true);
        lblRegisterSignIn.setOpacity(1.0);
        lblRegisterSignIn.setStyle("-fx-text-fill: #0272A6");
        lblRegisterSignIn.setCursor(Cursor.HAND);
    }

    public void handleSignIn (MouseEvent event) {
        Effects.sceneAnimator(lblSignInPassphrase,1000,Interpolator.EASE_OUT);
        lblSignInPassphrase.setVisible(false);
        Effects.sceneAnimator(lblSignInEmail,1000,Interpolator.EASE_OUT);
        lblSignInEmail.setVisible(false);
        Effects.sceneAnimator(lblSignInNewAccount,1000,Interpolator.EASE_OUT);
        lblSignInNewAccount.setVisible(false);
        lblSignInNewAccount.setUnderline(false);
        lblSignInNewAccount.setStyle("-fx-text-fill: black");
        lblSignInNewAccount.setOpacity(0.6);
        try{
            String email = textEmailSignIn.getText().trim();
            String logInPassphrase = passwordFieldSignIn.getText().trim();

            if (UserData.getInstance().authenticateUser(email,logInPassphrase)){
                currentUserEmail = email;
                Stage stage;
                Parent root;

                stage=(Stage) buttonSignIn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/sample/userInterface/mainWindow.fxml"));
                root.setOnMousePressed(e -> {
                    xOffset = e.getSceneX();
                    yOffset = e.getSceneY();
                });
                root.setOnMouseDragged(e -> {
                    stage.setX(e.getScreenX() - xOffset);
                    stage.setY(e.getScreenY() - yOffset);
                });
                Effects.sceneAnimator(root,2000,Interpolator.EASE_IN);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                }
                else {
                Effects.sceneAnimator(lblSignInPassphrase,1000,Interpolator.EASE_IN);
                lblSignInPassphrase.setVisible(true);
                passwordFieldSignIn.clear();
            }

        }catch (Exception e){
            e.printStackTrace();
            Effects.sceneAnimator(lblSignInEmail,1000,Interpolator.EASE_IN);
            lblSignInEmail.setVisible(true);
            Effects.sceneAnimator(lblSignInNewAccount,1000,Interpolator.EASE_IN);
            lblSignInNewAccount.setVisible(true);
            passwordFieldSignIn.clear();

        }

    }
    public void handleRegister (MouseEvent event) {

        Effects.sceneAnimator(lblRegisterName,1000,Interpolator.EASE_OUT);
        lblRegisterName.setVisible(false);
        Effects.sceneAnimator(lblRegisterName1,1000,Interpolator.EASE_OUT);
        lblRegisterName1.setVisible(false);
        Effects.sceneAnimator(lblRegisterEmail,1000,Interpolator.EASE_OUT);
        lblRegisterEmail.setVisible(false);
        Effects.sceneAnimator(lblRegisterEmail1,1000,Interpolator.EASE_OUT);
        lblRegisterEmail1.setVisible(false);
        Effects.sceneAnimator(lblRegisterPassphrase,1000,Interpolator.EASE_OUT);
        lblRegisterPassphrase.setVisible(false);
        Effects.sceneAnimator(lblRegisterPassphrase1,1000,Interpolator.EASE_OUT);
        lblRegisterPassphrase1.setVisible(false);
        Effects.sceneAnimator(lblRegisterPassphrase2,1000,Interpolator.EASE_OUT);
        lblRegisterPassphrase2.setVisible(false);
        Effects.sceneAnimator(lblRegisterUserExists,1000,Interpolator.EASE_OUT);
        lblRegisterUserExists.setVisible(false);
        Effects.sceneAnimator(lblRegisterSignIn,1000,Interpolator.EASE_OUT);
        lblRegisterSignIn.setVisible(false);
        try{
            String name = textFieldName.getText().trim() ;
            String email = textFieldEmail.getText().trim();
            String passphrase = passwordFieldKey.getText().trim();
            String passphrase_re = passwordFieldKey_re.getText().trim();

            if (name.equals("")){
                Effects.sceneAnimator(lblRegisterName1,1000,Interpolator.EASE_IN);
                lblRegisterName1.setVisible(true);
                Notifications.create()
                        .title("Title Text")
                        .hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.CENTER)
                        .text("Sample Text")
                        .showWarning();
            }
            if (UserData.getUserData(textFieldEmail.getText().trim()).getName()!=null){
                Effects.mediaError().play();
                Effects.sceneAnimator(lblRegisterUserExists,1000,Interpolator.EASE_IN);
                lblRegisterUserExists.setVisible(true);
                Effects.sceneAnimator(lblRegisterSignIn,1000,Interpolator.EASE_IN);
                lblRegisterSignIn.setVisible(true);
            }

            if (!UserData.validateName(name) && !name.equals("")){
                Effects.sceneAnimator(lblRegisterName,1000,Interpolator.EASE_IN);
                lblRegisterName.setVisible(true);

            }if (!UserData.validateEmail(email) && !email.equals("")){
                Effects.sceneAnimator(lblRegisterEmail,1000,Interpolator.EASE_IN);
                lblRegisterEmail.setVisible(true);

            }if (!UserData.validateEmail(email) && email.equals("")){
                Effects.sceneAnimator(lblRegisterEmail1,1000,Interpolator.EASE_IN);
                lblRegisterEmail1.setVisible(true);
            }if (passphrase.equals("")){
                Effects.sceneAnimator(lblRegisterPassphrase2,1000,Interpolator.EASE_IN);
                lblRegisterPassphrase2.setVisible(true);
            }
            if(!UserData.validatePassphrase(passphrase,passphrase_re) && !passphrase.equals("") &&(passphrase.equals(passphrase_re))){
                Effects.sceneAnimator(lblRegisterPassphrase,1000,Interpolator.EASE_IN);
                lblRegisterPassphrase.setVisible(true);
                passwordFieldKey.clear();
                passwordFieldKey_re.clear();
            }
            if(!UserData.validatePassphrase(passphrase,passphrase_re) && !passphrase.equals("") &&!(passphrase.equals(passphrase_re))){
                Effects.sceneAnimator(lblRegisterPassphrase1,1000,Interpolator.EASE_IN);
                lblRegisterPassphrase1.setVisible(true);
                passwordFieldKey.clear();
                passwordFieldKey_re.clear();
            }

            else if (!email.equals("") && !passphrase.equals("") && UserData.validateEmail(email)&& UserData.getUserData(textFieldEmail.getText().trim()).getName()==null
                    && UserData.validatePassphrase(passphrase,passphrase_re)){
                register();

                Effects.sceneAnimator(paneSignIn,1000,Interpolator.LINEAR);
                paneBlank.toBack();
                paneBlank1.toFront();
                paneSignIn.toFront();
                textFieldName.clear();
                textFieldEmail.clear();
                passwordFieldKey.clear();
                passwordFieldKey_re.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"User registration successful!");
                alert.setHeaderText(null);
                alert.setTitle(null);
                alert.initStyle(StageStyle.TRANSPARENT);
                alert.setX(600);
                alert.setY(550);
                Effects.mediaAlert().play();
                alert.show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void register() {
        try{
            String name = textFieldName.getText().trim() ;
            String email = textFieldEmail.getText().trim();
            String passphrase = passwordFieldKey.getText().trim();
            byte[] salt = KeyHandler.getInstance().generateSalt();
            byte[] encryptedPassphrase = KeyHandler.getInstance().getEncryptedPassphrase(passphrase,salt);
            UserProfile user = new UserProfile(name, email, encryptedPassphrase, salt);
            UserData.getInstance().addUserProfile(user);
            UserData.getInstance().saveUserProfile(user);
            ObservableList<UserProfile> userProfiles = UserData.getInstance().getUserProfiles();

            Iterator<UserProfile> iterator = userProfiles.iterator();
            while (iterator.hasNext()){
                UserProfile profile = iterator.next();
                System.out.println("\n\n"+profile.getName() +"\n"+profile.getEmail()+"\n"+(profile.getEncryptedPassphrase().toString())+"\n"+(profile.getSalt().toString()));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        DbConnect.getInstance().connect();
        UserData.getInstance().loadUserProfiles();
        FileData.getInstance().loadFileModules();

    }
}
