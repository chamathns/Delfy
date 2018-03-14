package sample.util;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class UserData {
    private MongoDatabase database = DbConnect.getInstance().getDatabase();
    private MongoCollection userCollection = database.getCollection("user");
}
