package com.example.healthcorner.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthcorner.R;

public class Dashboard_Fragment extends Fragment implements View.OnClickListener {
    private CardView superfood, restaurant, sport, bmi, waterCounter, profile;
    NavigationView navigationView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        superfood = (CardView) getView().findViewById(R.id.superfoodID);
        restaurant = (CardView) getView().findViewById(R.id.restaurantID);
        sport = (CardView) getView().findViewById(R.id.sportID);
        bmi = (CardView) getView().findViewById(R.id.bmiCalculatorID);
        waterCounter = (CardView) getView().findViewById(R.id.waterCounterID);
        profile = (CardView) getView().findViewById(R.id.profileID);

        superfood.setOnClickListener(this);
        restaurant.setOnClickListener(this);
        sport.setOnClickListener(this);
        bmi.setOnClickListener(this);
        waterCounter.setOnClickListener(this);
        profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        //get fragment instance
        Fragment fragment = new Fragment();
        //get Fragment Manager
        FragmentManager fragmentManager = getFragmentManager();
        //open fragment object, do some actions in the fragment
        FragmentTransaction ft = fragmentManager.beginTransaction();


        switch (v.getId()) {
            case R.id.superfoodID :
                NavigationFocus(R.id.nav_superfood_menu);
                ft.replace(R.id.fragment_container, new Superfood_Fragment()).commit();
                break;
            case R.id.restaurantID :
                ft.replace(R.id.fragment_container, new Restaurant_Fragment()).commit();
                break;
            case R.id.sportID :
                ft.replace(R.id.fragment_container, new Sport_Fragment()).commit();
                break;
            case R.id.bmiCalculatorID :
                ft.replace(R.id.fragment_container, new Bmi_Fragment()).commit();
                break;
            case R.id.waterCounterID :
                ft.replace(R.id.fragment_container, new DrinkWater_Fragment()).commit();
                break;
            case R.id.profileID :
                ft.replace(R.id.fragment_container, new Profile_Fragment()).commit();
                break;

        }


    }


    public void NavigationFocus(int navItemIndex) {
        navigationView = getView().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }
}
