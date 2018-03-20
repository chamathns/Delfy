package sample.util;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.bson.Document;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserData {
    private MongoDatabase database = DbConnect.getInstance().getDatabase();
    private MongoCollection userCollection = database.getCollection("users");

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
        ArrayList<Document> dbUserProfiles = (ArrayList<Document>)userCollection.find().into(new ArrayList<Document>());
        for (Document document: dbUserProfiles){
            System.out.println(document);
        }
    }
    public void saveUserProfile(UserProfile userProfile){
        Document doc = new Document("_id",userProfile.getEmail())
                .append("name",userProfile.getName())
                .append("encryptedPassphrase",userProfile.getEncryptedPassphrase())
                .append("salt",userProfile.getSalt());
        userCollection.insertOne(doc);
    }
    public static boolean validateName(String name) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
    public static boolean validateEmail(String email) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    public static boolean validatePassphrase(String passphrase, String passphrase_re){
        boolean flag;
        if (passphrase.equals(passphrase_re)){
            String regex = "^"
                            + "(?=.*\\d)"
                            + "(?=.*[a-z])"
                            + "(?=.*[A-Z])"
                            + "(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])"
                            + "."
                            + "{6,15}"
                            + "$";
            /**
             * 	- contains at least 1 digit
             * 	- contains at least 1 lowercase letter
             * 	- contains at least 1 uppercase letter
             * 	- contains at least 1 of the special
             * 		characters !@#$%^&*()_+\-=[]{};':"\|,.<>/?
             * 	- is at least 6 character long
             * 	- is at most 15 characters long
             * 	(note : for best practices you could even set
             * 	the max length to a higher value )
             */
            Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(passphrase);
            flag = matcher.find();
        }else{
            flag = false;
        }
        return flag;
    }
}
