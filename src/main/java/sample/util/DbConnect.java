package sample.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DbConnect {
    private static DbConnect instance = new DbConnect();
    public static DbConnect getInstance(){
        return instance;
    }
    public void connect(){
        MongoClient mongoClient = new MongoClient("Localhost",27017);
        MongoDatabase database = mongoClient.getDatabase("Delfy");
        MongoCollection collection = database.getCollection("user");
        Document doc = new Document("name","Chamath")
                .append("e-mail","chamath@gmail.com")
                .append("password","MasterKey");
        collection.insertOne(doc);
    }


}
