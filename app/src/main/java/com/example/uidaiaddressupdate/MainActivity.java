package com.example.uidaiaddressupdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.uidaiaddressupdate.Location.LocationInterface;
import com.example.uidaiaddressupdate.Location.MyLocationListener;
import com.example.uidaiaddressupdate.lender.LenderActivity;
import com.example.uidaiaddressupdate.requestaddress.RequestHome;
import com.example.uidaiaddressupdate.sharecode.ShowShareCode;

public class MainActivity extends AppCompatActivity implements LocationInterface {
    private AppCompatButton requestAddress, viewRequests, shareCode;
    private static Integer LOCATION_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("F",SharedPrefHelper.getAadharNumber(getApplicationContext()));
        Log.d("F",SharedPrefHelper.getAuthToken(getApplicationContext()));
        Log.d("F",SharedPrefHelper.getUidToken(getApplicationContext()));
        Log.d("F",SharedPrefHelper.getShareableCode(getApplicationContext()));
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new MyLocationListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        requestAddress = (AppCompatButton) findViewById(R.id.main_request_address_button);
        viewRequests = (AppCompatButton) findViewById(R.id.view_requests_button);
        shareCode = (AppCompatButton) findViewById(R.id.view_share_code_btn);
        requestAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RequestHome.class));
            }
        });


        viewRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LenderActivity.class));
            }
        });

        shareCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowShareCode.class));
            }
        });
    }

    /**
     * Callback function called from LocationListener
     * @param longitude
     * @param lattitude
     */
    @Override
    public void updateLocation(Double longitude, Double lattitude) {
        Log.d("Mohan","Coordinates are : (" + longitude + "," + lattitude  + ")");
        SharedPrefHelper.saveCoordinates(this,longitude,lattitude);
    }

    /**
     * request for location permission if location permission is not granted
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
                            ActivityCompat.requestPermissions(MainActivity.this,
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
        }
    }
}