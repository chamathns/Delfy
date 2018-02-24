package sample.util;

import javafx.stage.FileChooser;

import java.io.File;

public class FileHandler {
    private static FileHandler instance = new FileHandler();
    private File selectedFile;

    public static FileHandler getInstance(){
        return instance;
    }

    public void selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text files","*.txt"));
        selectedFile = fileChooser.showOpenDialog(null);
    }
}
