package sample.util;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.ObservableList;

public class UserData {
//    private MongoDatabase database = DbConnect.getInstance().getDatabase();
//    private MongoCollection userCollection = database.getCollection("user");

    private ObservableList<UserProfile> userProfiles;
    private static UserData instance = new UserData();

    public static UserData getInstance() {
        return instance;
    }

    public ObservableList<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void addUserProfile (UserProfile userProfile){
        userProfiles.add(userProfile);
    }
    public void loadUserProfiles(){
        //
    }
    public void saveUserProfile(){
        //
    }
}
