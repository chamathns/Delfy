package sample.util;
import sample.algorithms.Algorithm;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
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
    public boolean authenticate(String attemptedPassphrase, byte[] encryptedPassphrase, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException{

        byte[] encryptredAttemptedPassphrase = getEncryptedPassphrase(attemptedPassphrase, salt);
        return Arrays.equals(encryptedPassphrase, encryptredAttemptedPassphrase);
    }
    public byte[] getEncryptedPassphrase(String password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        // PBKDF2 with SHA-1 as the hashing algorithm.
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;
        int iterations = 20000;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

        return f.generateSecret(spec).getEncoded();
    }
    public byte[] generateSalt() throws NoSuchAlgorithmException {

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }
}
