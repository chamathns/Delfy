package sample.util;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FileModule extends RecursiveTreeObject<FileModule>{
    public StringProperty fileName;
    public StringProperty time;
    public StringProperty location;
    public StringProperty algorithmUsed;

    public FileModule(String filename, String time, String location, String algorithm){
        this.fileName = new SimpleStringProperty(filename);
        this.time = new SimpleStringProperty(time);
        this.location = new SimpleStringProperty(location);
        this.algorithmUsed = new SimpleStringProperty(algorithm);
    }


}


