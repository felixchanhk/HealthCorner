package com.example.healthcorner.view;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcorner.R;
import com.example.healthcorner.utility.GpsTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RestaurantActivity extends AppCompatActivity {

    double latitude;
    double longitude;
    TextView tv;
    Button btnLoc;
    String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GpsTracker gt = new GpsTracker(getApplicationContext());
        Location l = gt.getLocation();
        if( l == null){
            Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            latitude = l.getLatitude();
            longitude = l.getLongitude();
            //country = getCityName(latitude,longitude);
            //Log.e("country",country);
        }

        //if(country.equalsIgnoreCase("Hong Kong")) {
            setContentView(R.layout.activity_restaurant_hk);
        //}else{
            //setContentView(R.layout.activity_restaurant_macau);
        //}

        btnLoc = (Button) findViewById(R.id.btnGetLoc);
        tv = (TextView) findViewById(R.id.geo);

        ActivityCompat.requestPermissions(RestaurantActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GpsTracker gt = new GpsTracker(getApplicationContext());
                Location l = gt.getLocation();
                if( l == null){
                    Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
                }else {
                    latitude = l.getLatitude();
                    longitude = l.getLongitude();
                    String name123 = getCityName(latitude,longitude);
                    Toast.makeText(getApplicationContext(),"GPS Lat = "+latitude+"\n lon = "+longitude+"\n country"+name123,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getCityName(Double latitude, Double longitude){
        String country = "";
        Geocoder geocoder = new Geocoder(RestaurantActivity.this, Locale.getDefault());
        try{
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);
            country = addresses.get(0).getCountryName();
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
        } catch(IOException e){
            e.printStackTrace();
        }
        return country;
    }
}