package com.example.healthcorner.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthcorner.R;
import com.example.healthcorner.database.SqliteHelper;
import com.example.healthcorner.database.model.User;

import java.util.Objects;

public class BmiActivity extends AppCompatActivity {
    EditText edt_height;
    EditText edt_weight;
    Button btn_calculate;
    TextView result;

    String heightStr;
    String weightStr;
    SqliteHelper db = new SqliteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        edt_height = findViewById(R.id.BMI_height);
        edt_weight = findViewById(R.id.BMI_weight);

        Intent getDataIntent = getIntent();
        String dbUserEmail;
        dbUserEmail = getDataIntent.getStringExtra("userEmail");
        //Log.e("BMI USER",dbUserEmail);
        User getUser = db.getUser(dbUserEmail);
        edt_height.setText(getUser.getHeight());
        edt_weight.setText(getUser.getWeight());

        btn_calculate = findViewById(R.id.BMI_submit);
        result = findViewById(R.id.BMI_result);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                heightStr = edt_height.getText().toString();
                weightStr = edt_weight.getText().toString();

                if (Objects.equals(heightStr, "") || !Objects.equals(weightStr, "")) {
                    float heightValue = Float.parseFloat(heightStr)/100;
                    float weightValue = Float.parseFloat(weightStr);
                    float bmi = weightValue / (heightValue * heightValue);
                    displayBMI(bmi);
                } else {
                    result.setText(getString(R.string.Enter_height));
                }
            }
        });
    }

    private void displayBMI(float bmi) {

        String bmiLabel;

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.very_underweight);
        } else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal_weight);
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.over_weight);
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.first_class_obese);
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.second_class_obese);
        } else {
            bmiLabel = getString(R.string.third_class_obese);
        }

        bmiLabel = bmi + "\n" + bmiLabel;
        result.setText(bmiLabel);

    }
}
