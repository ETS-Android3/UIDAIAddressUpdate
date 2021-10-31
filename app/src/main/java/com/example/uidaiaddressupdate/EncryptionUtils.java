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
    /**
     * Function for encrypting the message
     * @param publicKeyString
     * @param Message
     * @return
     * @throws Exception
     */
    public static String encryptMessage(String publicKeyString, String Message) throws Exception{
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

    /**
     * Function for decrypting the message
     * @param encrytedMessage
     * @return
     * @throws Exception
     */
    public static String decryptMessage(String encrytedMessage) throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        KeyStore.Entry pke = (KeyStore.Entry)ks.getEntry("aadharkeys",null);
        KeyStore.PrivateKeyEntry prk = ((KeyStore.PrivateKeyEntry)pke);

        Cipher cipher1 = Cipher.getInstance("RSA/ECB/OAEPwithSHA-1andMGF1Padding");
        cipher1.init(Cipher.DECRYPT_MODE, prk.getPrivateKey());
        byte[] decryptedBytes = cipher1.doFinal(Base64.decode(encrytedMessage,Base64.DEFAULT));
        String decryptedText = new String(decryptedBytes);

        return decryptedText;
    }
}
