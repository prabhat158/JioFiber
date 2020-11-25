package com.jiocentrex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MultiTowerSociety extends AppCompatActivity {

    String nameOfSociety;
    TextView societyName;
    TextInputEditText textInputEditText;
    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_tower_society);
        textInputEditText = findViewById(R.id.TextInputEditText);
        materialButton = findViewById(R.id.nextbtn);
        societyName = findViewById(R.id.societyName);

        Bundle bundle = getIntent().getExtras();
        nameOfSociety = bundle.getString("nameOfSociety");

        societyName.setText(nameOfSociety);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!textInputEditText.getText().toString().equals("")) {
                    if (Integer.parseInt(textInputEditText.getText().toString()) > 0) {
                        Intent intent = new Intent(MultiTowerSociety.this, FinalMuti.class);
                        intent.putExtra("number", (textInputEditText.getText().toString()));
                        intent.putExtra("nameOfSociety", nameOfSociety);
                        startActivity(intent);
                    } else
                        textInputEditText.setError("Enter Number of towers in the society");
                } else
                    textInputEditText.setError("Enter Number of towers in the society");
            }
        });
    }
}