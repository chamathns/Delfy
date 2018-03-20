package sample.util;

public class UserProfile {
    private String name;
    private String email;
    private byte[] encryptedPassphrase;
    private byte[] salt;

    public UserProfile(String name, String email, byte[] encryptedPassphrase, byte[] salt){
        this.name = name;
        this.email = email;
        this.encryptedPassphrase = encryptedPassphrase;
        this.salt = salt;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getEncryptedPassphrase() {
        return encryptedPassphrase;
    }

    public void setEncryptedPassphrase(byte[] encryptedPassphrase) {
        this.encryptedPassphrase = encryptedPassphrase;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
