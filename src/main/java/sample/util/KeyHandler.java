package sample.util;

import sample.algorithms.AES;

import java.nio.charset.StandardCharsets;

public class KeyHandler {
    private static final String masterKey = "2EA45ED5B21E3%K^";
    private static String encryptedPassphrase;
//    private static String userPassphrase;
    public byte[] generateKey(String userPassphrase){
        AES.setKeyValue(masterKey.getBytes(StandardCharsets.UTF_8));
        try {
           encryptedPassphrase = AES.encrypt(userPassphrase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedPassphrase.getBytes();
    }
}
