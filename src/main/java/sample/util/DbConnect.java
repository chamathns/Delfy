package sample.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


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
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
