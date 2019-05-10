package com.example.healthcorner.view;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.healthcorner.R;
import com.example.healthcorner.utility.GpsTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener{

    double latitude;
    double longitude;
    String country;
    CardView restaurant_hk_cedele;
    CardView restaurant_hk_sevva;
    CardView restaurant_hk_mana;
    CardView restaurant_mc_hideout;
    CardView restaurant_mc_m2;
    CardView restaurant_mc_cat_cave;

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

        if(country.equalsIgnoreCase("Hong Kong") || country.equalsIgnoreCase("香港")) {
            setContentView(R.layout.activity_restaurant_hk);
            Toast.makeText(getApplicationContext(),"GPS Lat = "+latitude+
                    "\n Lon = "+longitude+
                    "\n Country: "+ getCityName(latitude,longitude) ,Toast.LENGTH_SHORT).show();

            restaurant_hk_cedele = (CardView) findViewById(R.id.restaurant_hk_cedele);
            restaurant_hk_sevva = (CardView) findViewById(R.id.restaurant_hk_sevva);
            restaurant_hk_mana = (CardView) findViewById(R.id.restaurant_hk_mana);

            restaurant_hk_cedele.setOnClickListener(this);
            restaurant_hk_sevva.setOnClickListener(this);
            restaurant_hk_mana.setOnClickListener(this);

        }else{
            setContentView(R.layout.activity_restaurant_macau);
            Toast.makeText(getApplicationContext(),"GPS Lat = "+latitude+
                    "\n Lon = "+longitude+
                    "\n Country: "+ getCityName(latitude,longitude) ,Toast.LENGTH_SHORT).show();

            restaurant_mc_hideout = (CardView) findViewById(R.id.restaurant_mc_hideout);
            restaurant_mc_m2 = (CardView) findViewById(R.id.restaurant_mc_m2);
            restaurant_mc_cat_cave = (CardView) findViewById(R.id.restaurant_mc_cat_cave);

            restaurant_mc_hideout.setOnClickListener(this);
            restaurant_mc_m2.setOnClickListener(this);
            restaurant_mc_cat_cave.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        Uri gmmIntentUri;
        Intent mapIntent;

        switch (v.getId()) {
            case R.id.restaurant_hk_cedele:
                gmmIntentUri = Uri.parse("google.navigation:q="+Uri.encode("Shop C, G/F, 58-64 Paterson Street, Fashion Walk, Causeway Bay, Causeway Bay")+"&mode=l");
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.restaurant_hk_sevva:
                gmmIntentUri = Uri.parse("google.navigation:q="+Uri.encode("Prince's Building 25th Floor 10 Chater Road Central, Hong Kong")+"&mode=l");
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.restaurant_hk_mana:
                gmmIntentUri = Uri.parse("google.navigation:q="+Uri.encode("92 Wellington Street Central, Hong Kong")+"&mode=l");
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.restaurant_mc_hideout:
                gmmIntentUri = Uri.parse("google.navigation:q="+Uri.encode("澳門馬統領圍No. 26號")+"&mode=w");
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.restaurant_mc_m2:
                gmmIntentUri = Uri.parse("google.navigation:q="+Uri.encode("澳門荷蘭 園 大 馬路 90C 號 海 濤 商場 3 樓")+"&mode=w");
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.restaurant_mc_cat_cave:
                gmmIntentUri = Uri.parse("google.navigation:q="+Uri.encode("好景大廈(第一座) 地下 a, 5 R. de Inacio Baptista, 澳門")+"&mode=w");
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
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
