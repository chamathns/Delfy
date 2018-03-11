package sample.util;

import sample.algorithms.AES;
import sample.algorithms.Algorithm;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class KeyHandler {
    private static KeyHandler instance = new KeyHandler();
    private static final String masterKey = "2EA45ED5B21E3%K^";
    private static byte[] encryptedPassphrase;

    public static KeyHandler getInstance() {
        return instance;
    }

    public static byte[] generateKey(String userPassphrase){
        switch (Algorithm.getALGO()){
            case ("DES"):
                byte[] key = Arrays.copyOfRange(masterKey.getBytes(),0,8);
                Algorithm.setKeyValue(key);
                break;
            default:
                Algorithm.setKeyValue(masterKey.getBytes(StandardCharsets.UTF_8));
        }
        try {
           encryptedPassphrase = Algorithm.encrypt(userPassphrase.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedPassphrase;
    }
}
