package com.example.healthcorner.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcorner.R;
import com.example.healthcorner.database.SqliteHelper;
import com.example.healthcorner.database.model.User;

public class WaterActivity extends AppCompatActivity {

    private EditText editTextDrinking;
    TextView tvReminder;
    public static final String DRINKING_KEY = "DRINKING_KEY";
    public static final String USER_EMAIL_KEY = "USER_EMAIL_KEY";
    private SharedPreferences sharedPreferences;
    public String TodayDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        editTextDrinking = (EditText) findViewById(R.id.nameText);
        tvReminder = (TextView) findViewById(R.id.reminder_water);
        sharedPreferences = getSharedPreferences("MySharedPreMain", Context.MODE_PRIVATE);

        if (sharedPreferences.contains(DRINKING_KEY)) {
            TodayDrink = sharedPreferences.getString(DRINKING_KEY, "");

            String stringValue = countDrinking(TodayDrink);
            tvReminder.setText(stringValue);
            editTextDrinking.setText(TodayDrink);

        }

    }

    public void save(View v){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DRINKING_KEY, editTextDrinking.getText().toString());
        editor.commit();
        Toast.makeText(v.getContext(),"data saved",Toast.LENGTH_SHORT).show();
    }

    public String countDrinking(String TodayDrink){
        SqliteHelper sqliteHelper = new SqliteHelper(this);
        User getUser = sqliteHelper.getUser(sharedPreferences.getString(USER_EMAIL_KEY, ""));
        Log.e("weight",getUser.getWeight());

        int reminderTotal = Integer.parseInt(TodayDrink);
        int userKG = Integer.parseInt(getUser.getWeight());
        // kg x 30c.c.
        int todayReminder = userKG * 30 - reminderTotal;
        String stringValue = Integer.toString(todayReminder);
        return stringValue;
    }
}