package sample.algorithms;

import com.sun.istack.internal.NotNull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class implements methods to encrypt and decrypt files with Bruce
 * Schneier's blowfish algorithm.
 */
public final class Blowfish {

    private static final String BLOWFISH = "Blowfish";

    private Blowfish() {}

    /**
     * Encrypts a file with a given key. The maximum key length is 16 byte.
     * @param plainFileName File name of the file you want to encrypt.
     * @param encryptedFileName File name of the encrypted file.
     * @param key Key (maximum 16 byte)
     * @throws Exception
     */
    public static void encrypt(String plainFileName, String encryptedFileName, String key) throws Exception {
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), BLOWFISH);
        Cipher cipher = Cipher.getInstance(BLOWFISH);
        cipher.init(Cipher.ENCRYPT_MODE, skey);

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                plainFileName));
        CipherOutputStream out = new CipherOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(encryptedFileName)), cipher);
        int i;
        do {
            i = in.read();
            if (i != -1) {
                out.write(i);
            }
        } while (i != -1);
        in.close();
        out.close();
    }

    /**
     * Decrypts an encrypted file.
     * @param encryptedFileName File name of the encrypted file
     * @param plainFileName File name of the decrypted file
     * @param key Key
     * @throws Exception
     */
    public static void decrypt(String encryptedFileName, String plainFileName, String key) throws Exception {
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), BLOWFISH);
        Cipher cipher = Cipher.getInstance(BLOWFISH);
        cipher.init(Cipher.DECRYPT_MODE, skey);
        CipherInputStream in = new CipherInputStream(new BufferedInputStream(
                new FileInputStream(encryptedFileName)), cipher);
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(plainFileName));
        int i;
        do {
            i = in.read();
            if (i != -1) {
                out.write(i);
            }
        } while (i > 0);
        in.close();
        out.close();
    }
}