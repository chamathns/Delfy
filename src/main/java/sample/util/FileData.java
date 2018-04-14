package sample.util;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class FileData {

    public ObservableList<FileModule> fileModules;
    private static FileData instance = new FileData();
    private static MongoCollection userCollection = UserData.getUserCollection();

    public static FileData getInstance(){
        return instance;
    }

    public ObservableList<FileModule> getFileModules() {
        return fileModules;
    }
    public void addFileModules(FileModule fileModule){
        fileModules.add(fileModule);
    }

    public void loadFileModules(){
        fileModules = FXCollections.observableArrayList();
    }
    public static void updateRecentFiles(String email, FileModule fileModule){
        ArrayList<Document> files = new ArrayList<>();
        files.add(new Document("file",fileModule.getFileName()).append("date",fileModule.getTime())
        .append("location",fileModule.getLocation()).append("algorithm",fileModule.getAlgorithmUsed()));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set",new BasicDBObject().append("files",files));
        BasicDBObject searchQuery = new BasicDBObject().append("_id",email);
        userCollection.updateOne(searchQuery,newDocument);
    }
}
