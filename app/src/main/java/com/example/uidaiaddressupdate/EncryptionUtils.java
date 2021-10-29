package com.example.uidaiaddressupdate;

import android.util.Base64;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncryptionUtils {
    public static String encryptMessage(String publicKeyString, String Message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        //Generation of Key from String
        byte[] keyBytes = Base64.decode(publicKeyString, Base64.DEFAULT);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pk2 = keyFactory.generatePublic(spec);

        //Encryption
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPwithSHA-1andMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pk2);
        byte[] encryptedBytes = cipher.doFinal(Message.getBytes());
        Log.d("KeyTest", String.valueOf(encryptedBytes.length));

        String encryptedText = Base64.encodeToString(encryptedBytes, Base64.DEFAULT);

        return encryptedText;
    }
}
