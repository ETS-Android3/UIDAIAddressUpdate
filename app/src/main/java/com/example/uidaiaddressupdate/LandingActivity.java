package com.example.uidaiaddressupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.Toast;

import com.example.uidaiaddressupdate.auth.LoginActivity;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    "aadharsharedPreferences",
                    mainKeyAlias,
                    this,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            //SharedPreferences.Editor sharedPrefsEditor = sharedPreferences.edit();
            if(sharedPreferences.contains(Constants.KEY_AUTH_TOKEN)){
                //send to home page
                Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LandingActivity.this,MainActivity.class));
                finish();
            }else{
                //send to LoginPage
                Toast.makeText(this, "Not logged in", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LandingActivity.this, LoginActivity.class));
                finish();
            }
        }catch(Exception e){
            Toast.makeText(this, "Error while reading shared pref", Toast.LENGTH_SHORT).show();
        }
    }
}