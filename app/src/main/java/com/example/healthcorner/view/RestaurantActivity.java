package com.example.healthcorner.view;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.healthcorner.R;
import com.example.healthcorner.utility.GpsTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RestaurantActivity extends AppCompatActivity {

    double latitude;
    double longitude;
    String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_macau);

        ActivityCompat.requestPermissions(RestaurantActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        GpsTracker gt = new GpsTracker(getApplicationContext());
        Location l = gt.getLocation();

        if( l == null){
            Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            latitude = l.getLatitude();
            longitude = l.getLongitude();
            country = getCityName(latitude,longitude);
            Log.e("country: ",country);
        }

        if(country.equalsIgnoreCase("Hong Kong")) {
            setContentView(R.layout.activity_restaurant_hk);
            Toast.makeText(getApplicationContext(),"GPS Lat = "+latitude+
                    "\n Lon = "+longitude+
                    "\n Country: "+ getCityName(latitude,longitude) ,Toast.LENGTH_SHORT).show();

        }else{
            setContentView(R.layout.activity_restaurant_macau);
            Toast.makeText(getApplicationContext(),"GPS Lat = "+latitude+
                    "\n Lon = "+longitude+
                    "\n Country: "+ getCityName(latitude,longitude) ,Toast.LENGTH_SHORT).show();
        }

    }

    private String getCityName(Double latitude, Double longitude){
        String country = "";
        String city = "";
        String state = "";
        Geocoder geocoder = new Geocoder(RestaurantActivity.this, Locale.getDefault());
        try{
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);
            country = addresses.get(0).getCountryName();
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
        } catch(IOException e){
            e.printStackTrace();
        }
        return country;
    }
}