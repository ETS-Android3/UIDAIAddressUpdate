package com.example.uidaiaddressupdate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import android.widget.Toast;

import com.example.uidaiaddressupdate.Location.Coordinates;
import com.example.uidaiaddressupdate.auth.LoginActivity;

import java.io.IOException;
import java.security.GeneralSecurityException;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

public class SharedPrefHelper {
    private static String AuthToken = null;
    private static String UidToken = null;
    private static String ShareableCode = null;
    private static String AadharNumber = null;
    private static Double longitude = null;
    private static Double latitude = null;

    /**
     * Function to read data from shared preference once and store it locally.
     * @param context
     */
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
            longitude = Double.parseDouble(sharedPreferences.getString(Constants.KEY_LONGITUDE,null));
            latitude = Double.parseDouble(sharedPreferences.getString(Constants.KEY_LATITUDE,null));

        }catch(Exception e){
            Toast.makeText(context, "Error while reading shared pref", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get Auth Token stored in the SharedPreference at the time of login
     * @param context
     * @return
     */
    public static String getAuthToken(Context context){
        if (AuthToken == null) {
            fetchAndSaveVariable(context);
        }
        return AuthToken;
    }

    /**
     * Get Uid Token of user that has been shared in the shared preferences at the time of login
     * @param context
     * @return
     */
    public static String getUidToken(Context context){
        if (UidToken == null) {
            fetchAndSaveVariable(context);
        }
        return UidToken;
    }

    /**
     * Function to get ShareableCode from shared Preference
     * @param context
     * @return
     */
    public static String getShareableCode(Context context){
        if (ShareableCode == null) {
            fetchAndSaveVariable(context);
        }
        return ShareableCode;
    }

    /**
     * Function to get aadhar number from shared preference
     * @param context
     * @return
     */
    public static String getAadharNumber(Context context) {
        if(AadharNumber == null)
            fetchAndSaveVariable(context);
        return AadharNumber;
    }

    /**
     * Function to save coordinates in shared preference
     * @param context
     * @param longitude
     * @param latitude
     */
    public static void saveCoordinates(Context context, Double longitude, Double latitude){
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String mainKeyAlias = null;
        try {
            mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    Constants.SHARED_PREFERENCES_FILE,
                    mainKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            SharedPreferences.Editor sharedPrefsEditor = sharedPreferences.edit();
            sharedPrefsEditor.putString(Constants.KEY_LONGITUDE,longitude.toString());
            sharedPrefsEditor.putString(Constants.KEY_LATITUDE,latitude.toString());
            sharedPrefsEditor.commit();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Function to get coordinates from shared preference
     * @param context
     * @return
     */
    public static Coordinates getCoordinates(Context context){
        if (latitude == null){
            fetchAndSaveVariable(context);
        }
        return new Coordinates(longitude,latitude);
    }
}
