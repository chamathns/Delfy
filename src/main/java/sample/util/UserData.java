package sample.util;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
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
        userProfiles = FXCollections.observableArrayList();
        UserProfile user = new UserProfile("user","user@sample.com","passphrase".getBytes(),"salt".getBytes());
        userProfiles.add(user);

        //
    }
    public void saveUserProfile(){
        //
    }
}
