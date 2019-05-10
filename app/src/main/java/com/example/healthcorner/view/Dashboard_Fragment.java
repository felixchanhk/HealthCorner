package com.example.healthcorner.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
        profile = (CardView) getView().findViewById(R.id.alarmID);

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
                intent = new Intent(getActivity(), SuperFoodActivity.class);
                startActivity(intent);
                break;
            case R.id.restaurantID :
                intent = new Intent(getActivity(), RestaurantActivity.class);
                startActivity(intent);
                break;
            case R.id.sportID :
                intent = new Intent(getActivity(), SportActivity.class);
                startActivity(intent);
                break;
            case R.id.bmiCalculatorID :
                intent = new Intent(getActivity(), BmiActivity.class);
                intent.putExtra("userEmail", "abc@abc.com"); //@TODO
                startActivity(intent);
                break;
            case R.id.waterCounterID :
                intent = new Intent(getActivity(), WaterActivity.class);
                startActivity(intent);
            case R.id.alarmID :
                intent = new Intent(getActivity(), AlarmActivity.class);
                startActivity(intent);

        }


    }

}
