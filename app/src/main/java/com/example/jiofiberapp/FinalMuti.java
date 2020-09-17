package com.example.jiofiberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class FinalMuti extends AppCompatActivity {

    LinearLayout linearLayout;
    MaterialButton materialButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_muti);

        linearLayout = findViewById(R.id.linearlayout);
        materialButton = findViewById(R.id.nextbtn);

        Intent intent = getIntent();
        int num = Integer.parseInt(intent.getStringExtra("number"));

        final List<View> list=new ArrayList<View>();

        for(int i=0; i< num; i++){
            final LayoutInflater factory = getLayoutInflater();
            final View textEntryView = factory.inflate(R.layout.basicform, null);
            TextView textView = textEntryView.findViewById(R.id.towerno);
            textView.setText("Tower " + (i+1));
            list.add(textEntryView);
            linearLayout.addView(textEntryView);
        }

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(View view1: list){
                    TextInputEditText textInputEditText = view1.findViewById(R.id.TextInputEditText);
                    Log.d("check",textInputEditText.getText().toString());
                }
            }
        });

    }
}