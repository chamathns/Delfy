package sample.algorithms;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Blowfish extends Algorithm{
    private static String ALGO = "Blowfish";
    private static byte[] keyValue;

    public static void setKeyValue(byte[] keyValue) {
        Blowfish.keyValue = keyValue;
    }
    private static Key generateKey() {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
    public static byte[] encrypt(byte[] data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(data);
        return encValue;
    }
    public static byte[] decrypt(byte[] encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decValue = c.doFinal(encryptedData);
        return decValue;
    }
}
