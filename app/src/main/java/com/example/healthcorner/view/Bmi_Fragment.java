package com.example.healthcorner.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthcorner.R;

public class Bmi_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_bmi, container, false);
        View root = inflater.inflate(R.layout.fragment_bmi, container, false);
        TextView view = root.findViewById(R.id.testId);
        view.setText("abc");
        return root;
    }
}

