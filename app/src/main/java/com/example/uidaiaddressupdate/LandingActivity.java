package com.example.uidaiaddressupdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import android.widget.Toast;

import com.example.uidaiaddressupdate.auth.LoginActivity;

public class LandingActivity extends AppCompatActivity {

    private int LOCATION_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        if (ContextCompat.checkSelfPermission(LandingActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(LandingActivity.this, "You have already granted this permission!", Toast.LENGTH_SHORT).show();
            waitCheckAndMove();
        } else {
            requestLocationPermission();
        }

    }

    /**
     * This function will make the activity wait for 2 seconds and then call function for check and move
     */
    private void waitCheckAndMove(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MoveToAuth();
            }
        }, 2000);

    }

    /**
     * This function will check weather user is already loggedIn or not thereafter move to required page
     */
    private void MoveToAuth(){
        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    Constants.SHARED_PREFERENCES_FILE,
                    mainKeyAlias,
                    this,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            //SharedPreferences.Editor sharedPrefsEditor = sharedPreferences.edit();
            if(sharedPreferences.contains(Constants.KEY_AUTH_TOKEN)){
                //send to home page
                Log.d("FD",SharedPrefHelper.getAadharNumber(getApplicationContext()));
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
            e.printStackTrace();
//            Toast.makeText(this, "Error while reading shared pref", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Request For location permission
     */
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("Location permission is needed for Security")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(LandingActivity.this,
                                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
            waitCheckAndMove();
        }
    }
}