package sample.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DbConnect {
    private static DbConnect instance = new DbConnect();
    private MongoClient mongoClient;
    private MongoDatabase database;
    public static DbConnect getInstance(){
        return instance;
    }
    public void connect(){
         mongoClient = new MongoClient("Localhost",27017);
         database = mongoClient.getDatabase("Delfy");
        System.out.println("Database Connection Successful");
//         MongoCollection collection = database.getCollection("user");
//        Document doc = new Document("name","Chamath")
//                .append("e-mail","chamath@gmail.com")
//                .append("password","MasterKey");
//
//
//
//        collection.insertOne(doc);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
