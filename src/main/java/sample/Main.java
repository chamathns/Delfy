package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.util.DbConnect;
import sample.util.UserData;
import sample.util.UserProfile;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("userInterface/mainWindow.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("userInterface/logIn.fxml"));

//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Delfy | Security for your files");

        try{
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("delfyIcon.png")));

        }catch (Exception e){
            e.printStackTrace();
        }
        primaryStage.show();
    }

    public static void main(String[] args) {
        DbConnect.getInstance().connect();
        launch(args);
    }
}