package sample.util;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.controlsfx.control.Notifications;
import sample.controller.MainWindowController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class FileHandler {
    private static FileHandler instance = new FileHandler();
    private File selectedFile;
    private String extension;
    public static FileHandler getInstance(){
        return instance;
    }

    public String getExtension() {
        return extension;
    }

    public File selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\"));
        selectedFile = fileChooser.showOpenDialog(null);
        extension = FilenameUtils.getExtension(selectedFile.getPath());
        return selectedFile;
    }
    public File selectDirectory(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("C:\\"));
        File selectedDirectory = directoryChooser.showDialog(null);
        return  selectedDirectory;
    }

    public File selectEncryptedFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Delfy encrypted files","*.enc"));
        selectedFile = fileChooser.showOpenDialog(null);
        String filename = FilenameUtils.getBaseName(selectedFile.getName());
        extension = FilenameUtils.getExtension(filename);
        return selectedFile;
    }
    public File getPath(){
        return selectedFile;
    }
    public static byte[] readFile(String filePath){
        byte[] content = {};
        try{
            content = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e){

            Notifications.create()
                    .title("Title Text")
                    .hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER)
                    .text("Sample Text")
                    .showWarning();
        }
        return content;
    }
    public static void writeFile(String filePath, byte[] byteArray){
        try {
            FileUtils.writeByteArrayToFile(new File(filePath),byteArray);
        } catch (IOException e) {
//            Notifications.create().title("Title Text").text("Sample Text").showWarning();
//            e.printStackTrace();
        }
    }

}
