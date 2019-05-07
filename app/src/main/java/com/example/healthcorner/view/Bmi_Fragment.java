package com.example.healthcorner.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthcorner.R;

import java.util.Objects;

public class Bmi_Fragment extends Fragment {
    EditText edt_height;
    EditText edt_weight;
    Button btn_calculate;
    TextView result;

    String heightStr;
    String weightStr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_bmi, container, false);
        View root = inflater.inflate(R.layout.fragment_bmi, container, false);

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        edt_height = getView().findViewById(R.id.BMI_height);
        edt_weight = getView().findViewById(R.id.BMI_weight);
        btn_calculate = getView().findViewById(R.id.BMI_submit);
        result = getView().findViewById(R.id.BMI_result);


        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                heightStr = edt_height.getText().toString();
                weightStr = edt_weight.getText().toString();

                if (Objects.equals(heightStr, "") || !Objects.equals(weightStr, "")) {
                    float heightValue = Float.parseFloat(heightStr);
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

