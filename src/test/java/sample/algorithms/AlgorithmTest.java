package sample.algorithms;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static org.junit.Assert.*;

public class AlgorithmTest {
    private static String ALGO;
    private static final byte[] keyValue =
            new byte[] { 'W', 'h', 'C', 'B', 'e', 's', 's',

                    'S', 'e', 'c', 's','e', 's', 'K', 'e', 'w' };


    public static void setALGO(String ALGO){
        AlgorithmTest.ALGO=ALGO;
    }

    public static String getALGO() {
        return ALGO;
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

    @Test
    public void encrypt_test() throws Exception {
        AlgorithmTest.setALGO("AES");
        byte[] plain_data = "This is a test string".getBytes();
        byte[] en_data = AlgorithmTest.encrypt(plain_data);
        byte[] de_data = AlgorithmTest.decrypt(en_data);
        String de_val = new String (de_data);

        assertEquals("This is a test string",de_val);
    }
}