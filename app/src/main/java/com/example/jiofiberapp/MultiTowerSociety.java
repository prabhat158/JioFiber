package com.example.jiofiberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MultiTowerSociety extends AppCompatActivity {

    TextInputEditText textInputEditText;
    MaterialButton materialButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_tower_society);
        textInputEditText = findViewById(R.id.TextInputEditText);
        materialButton = findViewById(R.id.nextbtn);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textInputEditText.getText().toString().equals("")){
                    if(Integer.parseInt(textInputEditText.getText().toString())>0){
                        Intent intent =  new Intent(MultiTowerSociety.this, FinalMuti.class);
                        intent.putExtra("number", (textInputEditText.getText().toString()));
                        startActivity(intent);
                    }
                }
            }
        });


    }
}