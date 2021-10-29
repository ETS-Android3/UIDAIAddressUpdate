package com.example.uidaiaddressupdate;

import android.util.Base64;
import android.util.Log;

import java.security.KeyStore;

import javax.crypto.Cipher;

public class DecryptionUtils {
    public static String decryptMessage(String encryptedText) throws Exception {
        // Get private key
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        KeyStore.Entry pke = (KeyStore.Entry) ks.getEntry("aadharkeys", null);
        KeyStore.PrivateKeyEntry prk = ((KeyStore.PrivateKeyEntry) pke);

        // Decryption
        Cipher cipher1 = Cipher.getInstance("RSA/ECB/OAEPwithSHA-1andMGF1Padding");
        cipher1.init(Cipher.DECRYPT_MODE, prk.getPrivateKey());
        byte[] decryptedBytes = cipher1.doFinal(Base64.decode(encryptedText, Base64.DEFAULT));
        return new String(decryptedBytes);
    }
}
