package sample;

import com.mongodb.MongoClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import org.apache.commons.io.FilenameUtils;
import sample.util.DbConnect;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("userInterface/logIn.fxml"));
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
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
        primaryStage.show();
    }

    public static void main(String[] args) {
        DbConnect.getInstance().connect();
        launch(args);
    }
}