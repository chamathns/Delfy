package sample.algorithms;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;


public class AES extends Algorithm{
//    private static AES instance = new AES();
//    public static AES getInstance(){
//        return instance;
//    }
//
//    private static String ALGO = "AES";
//    private static byte[] keyValue;
//
//    public static void setKeyValue(byte[] keyValue) {
//        AES.keyValue = keyValue;
//    }
//
//    private static Key generateKey() {
//        Key key = new SecretKeySpec(keyValue, ALGO);
//        return key;
//    }
//    public static byte[] encrypt(byte[] data) throws Exception {
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encValue = c.doFinal(data);
//        return encValue;
//    }
//    public static byte[] decrypt(byte[] encryptedData) throws Exception {
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.DECRYPT_MODE, key);
//        byte[] decValue = c.doFinal(encryptedData);
//        return decValue;
//    }
//
//
//    //keep this code
////    public static byte[] encrypt(byte[] data) throws Exception {
////        Key key = generateKey();
////        Cipher c = Cipher.getInstance(ALGO);
////        c.init(Cipher.ENCRYPT_MODE, key);
////        byte[] encValue = c.doFinal(data);
////        return encValue;
////    }
////    public static byte[] decrypt(byte[] encryptedData) throws Exception {
////        Key key = generateKey();
////        Cipher c = Cipher.getInstance(ALGO);
////        c.init(Cipher.DECRYPT_MODE, key);
////        byte[] decValue = c.doFinal(encryptedData);
////        return decValue;
////    }
////    private static Key generateKey() {
////        Key key = new SecretKeySpec(keyValue, ALGO);
////        return key;
////    }
//

}