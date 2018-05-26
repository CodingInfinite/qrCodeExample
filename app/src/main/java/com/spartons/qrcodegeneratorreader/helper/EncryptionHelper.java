package com.spartons.qrcodegeneratorreader.helper;


import android.util.Base64;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Ahsen Saeed on 1/10/2018.
 */

public class EncryptionHelper {

    private static EncryptionHelper encryptionHelper = null;
    private String encryptionKey;

    private EncryptionHelper() {

    }

    public static EncryptionHelper getInstance() {
        if (encryptionHelper == null) {
            encryptionHelper = new EncryptionHelper();
        }
        return encryptionHelper;
    }

    public SecretKey getSecreteKey(String secretKey) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digestOfPassword = md.digest(secretKey.getBytes("UTF-8"));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        return new SecretKeySpec(keyBytes, "AES");
    }

    public String encryptMsg() {
        return Base64.encodeToString(encryptionKey.getBytes(), Base64.DEFAULT);
    }

    public EncryptionHelper encryptionString(String encryptionKey) {
        this.encryptionKey = encryptionKey;
        return encryptionHelper;
    }

    public String getDecryptionString(String encryptedText) {
        return new String(Base64.decode(encryptedText.getBytes(), Base64.DEFAULT));
    }
}
