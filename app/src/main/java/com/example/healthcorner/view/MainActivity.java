package com.example.healthcorner.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.healthcorner.R;
import com.example.healthcorner.database.model.User;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getDataIntent = getIntent();
        String email = getDataIntent.getStringExtra("userEmail");

        welcome = findViewById(R.id.HelloWorld);
        welcome.setText(email);

    }
}
