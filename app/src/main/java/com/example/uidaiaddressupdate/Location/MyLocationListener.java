package com.example.uidaiaddressupdate.Location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class MyLocationListener implements LocationListener {

    LocationInterface locationInterface;

    public MyLocationListener(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        locationInterface.updateLocation(longitude,latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
