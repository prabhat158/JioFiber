package com.example.jiofiberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MultiTowerGroupSociety extends AppCompatActivity {


    TextInputEditText textInputEditText;
    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_tower_group_society);


        textInputEditText = findViewById(R.id.TextInputEditText);
        materialButton = findViewById(R.id.nextbtn);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textInputEditText.getText().toString().equals("")){
                    if(Integer.parseInt(textInputEditText.getText().toString())>0){
                        FinalMultiGroup2.no_of_group = Integer.parseInt(textInputEditText.getText().toString());
                        Intent intent =  new Intent(MultiTowerGroupSociety.this, FinalMultiGroup1.class);
                        intent.putExtra("number", (textInputEditText.getText().toString()));
                        startActivity(intent);
                    }
                }
            }
        });
    }
}