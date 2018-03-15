package sample.util;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
