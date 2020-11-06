package com.example.jiofiberapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class HomeActivity extends AppCompatActivity {
    String TAG = "tag";
    public static String name_of_society = "";
    public static String type_of_society = "";

    TextInputEditText textInputEditText;
    MaterialButton materialButton;
    AutoCompleteTextView editTextFilledExposedDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String[] COUNTRIES = new String[]{"Single Tower society", "Multi Tower society"};
//        "Multi Tower group society", "Others"

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        R.layout.list_item,
                        COUNTRIES);

        editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);

        editTextFilledExposedDropdown.setAdapter(adapter);
        editTextFilledExposedDropdown.setInputType(InputType.TYPE_NULL);

        editTextFilledExposedDropdown.setKeyListener(null);

        textInputEditText = findViewById(R.id.TextInputEditText);

        materialButton = findViewById(R.id.nextbtn);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_of_society = textInputEditText.getText().toString();
                type_of_society = editTextFilledExposedDropdown.getText().toString();

                if (name_of_society.equals("")) {
                    textInputEditText.setError("Enter name of society");
                    return;
                }

                if (type_of_society.equals("")) {
                    editTextFilledExposedDropdown.setError("Select type of society");
                    return;
                }

                if (editTextFilledExposedDropdown.getText().toString().equals("Single Tower society")) {
                    Intent intent = new Intent(HomeActivity.this, NewSingleTowerActivity.class);
                    intent.putExtra("nameOfTower", name_of_society);
                    startActivity(intent);
                } else if (editTextFilledExposedDropdown.getText().toString().equals("Multi Tower society")) {
                    Intent intent = new Intent(HomeActivity.this, NewMultiTowerTowerActivity.class);
                    intent.putExtra("nameOfSociety", name_of_society);
                    startActivity(intent);
                }

//                else if (editTextFilledExposedDropdown.getText().toString().equals("Multi Tower group society")) {
//                    startActivity(new Intent(HomeActivity.this, MultiTowerGroupSociety.class));
//                } else if (editTextFilledExposedDropdown.getText().toString().equals("Others")) {
//                    Intent intent = new Intent(HomeActivity.this, Other.class);
//                    intent.putExtra(AppConstants.SOCIETY_NAME, textInputEditText.getText().toString());
//                    startActivity(intent);
//                }

//                Log.d("check", textInputEditText.getText().toString());
//                Log.d("check", editTextFilledExposedDropdown.getText().toString());
            }
        });

        isReadStoragePermissionGranted();
    }

    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted1");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted1");
            return true;
        }
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted2");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted2");
            return true;
        }
    }

}