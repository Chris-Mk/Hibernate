package app.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public final class PasswordEncryption {
    private static final String SALT = "ILSUFBKSA34LEUHJBK2432VJB6575ASIEGFKSJDYGFJdjhkfbgksygfkhe";
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATION_COUNT = 65568;
    private static final int KEY_LENGTH = 128;


    public static String encrypt(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT.getBytes(), ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] encoded = secretKeyFactory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }
}
