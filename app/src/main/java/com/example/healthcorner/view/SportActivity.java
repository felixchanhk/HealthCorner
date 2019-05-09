package com.example.healthcorner.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.healthcorner.R;
import com.example.healthcorner.view.sport_video.ChestVideo01;
import com.example.healthcorner.view.sport_video.ArmsVideo01;
import com.example.healthcorner.view.sport_video.FlatBellyVideo01;
import com.example.healthcorner.view.sport_video.IntenseVideo01;
import com.example.healthcorner.view.sport_video.LegVideo01;

public class SportActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView chestID, sport_obliquesID, sport_flatBellyID, sport_legID, sport_intenseID;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        chestID =  (CardView) findViewById(R.id.sport_chestID);
        sport_obliquesID =  (CardView) findViewById(R.id.sport_armsID);
        sport_flatBellyID =  (CardView) findViewById(R.id.sport_flatBellyID);
        sport_legID =  (CardView) findViewById(R.id.sport_legID);
        sport_intenseID =  (CardView) findViewById(R.id.sport_intenseID);


        chestID.setOnClickListener(this);
        sport_obliquesID.setOnClickListener(this);
        sport_flatBellyID.setOnClickListener(this);
        sport_legID.setOnClickListener(this);
        sport_intenseID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;


        switch (v.getId()) {
            case R.id.sport_chestID:
                intent = new Intent(this, ChestVideo01.class);
                startActivity(intent);
                break;
            case R.id.sport_armsID:
                intent = new Intent(this, ArmsVideo01.class);
                startActivity(intent);
                break;
            case R.id.sport_flatBellyID:
                intent = new Intent(this, FlatBellyVideo01.class);
                startActivity(intent);
                break;
            case R.id.sport_legID:
                intent = new Intent(this, LegVideo01.class);
                startActivity(intent);
                break;
            case R.id.sport_intenseID:
                intent = new Intent(this, IntenseVideo01.class);
                startActivity(intent);
                break;
        }
    }
}
