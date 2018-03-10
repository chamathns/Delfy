package sample.util;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    private static FileHandler instance = new FileHandler();
    private File selectedFile;

    public static FileHandler getInstance(){
        return instance;
    }

    public File selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\"));
        selectedFile = fileChooser.showOpenDialog(null);
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
                new FileChooser.ExtensionFilter("text files","*.txt"));
        selectedFile = fileChooser.showOpenDialog(null);
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
            e.printStackTrace();
        }
        return content;
    }
    public static void writeFile(String filePath, byte[] byteArray){
        try {
            FileUtils.writeByteArrayToFile(new File(filePath),byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
