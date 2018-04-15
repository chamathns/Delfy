package sample.util;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;

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
        Document newFile = new Document("file",fileModule.getFileName()).append("date",fileModule.getTime())
        .append("location",fileModule.getLocation()).append("algorithm",fileModule.getAlgorithmUsed());
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$push",new BasicDBObject().append("files",newFile));
        BasicDBObject searchQuery = new BasicDBObject().append("_id",email);
        userCollection.updateOne(searchQuery,newDocument);
    }
}
