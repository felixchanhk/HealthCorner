package com.example.healthcorner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.example.healthcorner.view.AlarmActivity;
import com.example.healthcorner.view.BmiActivity;
import com.example.healthcorner.view.Dashboard_Fragment;
import com.example.healthcorner.view.DrinkWater_Fragment;
import com.example.healthcorner.view.Profile_Fragment;
import com.example.healthcorner.view.RestaurantActivity;
import com.example.healthcorner.view.SportActivity;
import com.example.healthcorner.view.Superfood_Fragment;
import com.example.healthcorner.view.WaterActivity;


public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView userEmail;
    Intent intent;
    public String dbUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // initial page
        if( savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Dashboard_Fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashborad);
        }

        Log.e("action","a1");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        Log.e("action","a2");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);

        userEmail = findViewById(R.id.userEmail);

        Intent getDataIntent = getIntent();
        dbUserEmail = getDataIntent.getStringExtra("userEmail");
        //dbUserEmail = "abc@abc.com"; //using to test
        Log.e("dbUserEmail",dbUserEmail);
        userEmail.setText(dbUserEmail);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent = new Intent(this, AlarmActivity.class);
            startActivity(intent);
        }

        Log.e("action","a4");
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashborad) {
            // Handle the dashboard action
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, // redirect to dashboard fragment
                    new Dashboard_Fragment()).commit();
        } else if (id == R.id.nav_superfood_menu) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Superfood_Fragment()).commit();
        } else if (id == R.id.nav_restaurant) {
            intent = new Intent(this, RestaurantActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_sport) {
            intent = new Intent(this, SportActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_bmi_calculator) {
            intent = new Intent(this, BmiActivity.class);
            intent.putExtra("userEmail", dbUserEmail);
            startActivity(intent);
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
            //        new Bmi_Fragment()).commit();
        } else if (id == R.id.nav_water_counter) {
            intent = new Intent(this, WaterActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Profile_Fragment()).commit();
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        //Log.e("action","a5");
        return true;
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
