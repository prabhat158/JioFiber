package com.example.jiofiberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import static java.security.AccessController.getContext;

public class HomeActivity extends AppCompatActivity {

    TextInputEditText textInputEditText;
    MaterialButton materialButton;
    AutoCompleteTextView editTextFilledExposedDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String[] COUNTRIES = new String[] {"Single tower society", "Multi Tower society",
                "Multi Tower group society", "Others"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        R.layout.list_item,
                        COUNTRIES);

        editTextFilledExposedDropdown =
                findViewById(R.id.filled_exposed_dropdown);

        editTextFilledExposedDropdown.setAdapter(adapter);
        editTextFilledExposedDropdown.setInputType(InputType.TYPE_NULL);


        textInputEditText = findViewById(R.id.TextInputEditText);

        materialButton = findViewById(R.id.nextbtn);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextFilledExposedDropdown.getText().toString().equals("Single tower society")){
                    startActivity(new Intent(HomeActivity.this, SingleTowerSociety.class));
                }else if(editTextFilledExposedDropdown.getText().toString().equals("Multi Tower society")){
                    startActivity(new Intent(HomeActivity.this, MultiTowerSociety.class));
                }else if(editTextFilledExposedDropdown.getText().toString().equals("Multi Tower group society")){
                    startActivity(new Intent(HomeActivity.this, MultiTowerGroupSociety.class));
                }

//                Log.d("check", textInputEditText.getText().toString());
//                Log.d("check", editTextFilledExposedDropdown.getText().toString());
            }
        });


    }
}