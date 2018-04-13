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
    public static void updateRecentFiles(String email){
        List<Document> files = new ArrayList<>();
        files.add(new Document("file","file1").append("date","date1").append("location","path1").append("algorithm","AES"));
        files.add(new Document("file","file2").append("date","date2").append("location","path2").append("algorithm","DES"));
        files.add(new Document("file","file3").append("date","date3").append("location","path3").append("algorithm","Blowfish"));
        files.add(new Document("file","file4").append("date","date4").append("location","path4").append("algorithm","DES"));
        files.add(new Document("file","file5").append("date","date5").append("location","path5").append("algorithm","Blowfish"));
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set",new BasicDBObject().append("files",files));
        BasicDBObject searchQuery = new BasicDBObject().append("_id",email);
        userCollection.updateOne(searchQuery,newDocument);
    }
}
