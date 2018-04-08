package sample.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileData {

    public ObservableList<FileModule> fileModules;
    private static FileData instance = new FileData();

    public static FileData getInstance(){
        return instance;
    }

    public ObservableList<FileModule> getFileModules() {
        return fileModules;
    }

    public void loadFileModules(){
        fileModules = FXCollections.observableArrayList();
        fileModules.add(new FileModule("file1","time1","location1","AES"));
        fileModules.add(new FileModule("file2","time2","location2","DES"));
        fileModules.add(new FileModule("file3","time3","location3","Blowfish"));
        fileModules.add(new FileModule("file4","time4","location4","AES"));
        fileModules.add(new FileModule("file5","time5","location5","Blowfish"));

    }
}
