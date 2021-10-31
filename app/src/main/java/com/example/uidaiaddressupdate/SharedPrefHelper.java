package com.example.uidaiaddressupdate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.Toast;

import com.example.uidaiaddressupdate.auth.LoginActivity;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

public class SharedPrefHelper {
    private static String AuthToken = null;
    private static String UidToken = null;
    private static String ShareableCode = null;
    private static String AadharNumber = null;

    private static void fetchAndSaveVariable(Context context){
        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    Constants.SHARED_PREFERENCES_FILE,
                    mainKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            AuthToken = sharedPreferences.getString(Constants.KEY_AUTH_TOKEN,null);
            UidToken = sharedPreferences.getString(Constants.KEY_UID_TOKEN,null);
            ShareableCode = sharedPreferences.getString(Constants.KEY_SHAREABLE_CODE,null);
            AadharNumber = sharedPreferences.getString(Constants.KEY_AADHAR_NUMBER,null);

        }catch(Exception e){
            Toast.makeText(context, "Error while reading shared pref", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getAuthToken(Context context){
        if(AuthToken != null){
            return AuthToken;
        }else{
            fetchAndSaveVariable(context);
            return AuthToken;
        }
    }

    public static String getUidToken(Context context){
        if(UidToken != null){
            return UidToken;
        }else{
            fetchAndSaveVariable(context);
            return UidToken;
        }
    }

    public static String getShareableCode(Context context){
        if(ShareableCode != null){
            return ShareableCode;
        }else{
            fetchAndSaveVariable(context);
            return ShareableCode;
        }
    }

    public static String getAadharNumber(Context context) {
        if(AadharNumber == null)
            fetchAndSaveVariable(context);
        return AadharNumber;
    }
}
