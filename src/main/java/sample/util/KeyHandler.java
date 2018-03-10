package sample.util;

import sample.algorithms.AES;

import java.nio.charset.StandardCharsets;

public class KeyHandler {
    private static KeyHandler instance = new KeyHandler();
    private static final String masterKey = "2EA45ED5B21E3%K^";
    private static byte[] encryptedPassphrase;

    public static KeyHandler getInstance() {
        return instance;
    }

    public static byte[] generateKey(String userPassphrase){
        AES.setKeyValue(masterKey.getBytes(StandardCharsets.UTF_8));
        try {
           encryptedPassphrase = AES.encrypt(userPassphrase.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedPassphrase;
    }
}
