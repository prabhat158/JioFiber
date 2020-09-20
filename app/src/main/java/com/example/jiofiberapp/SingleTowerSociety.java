package com.example.jiofiberapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SingleTowerSociety extends AppCompatActivity {

    String[] TYPE_OF_FLAT_NUMBER = new String[]{"11", "101", "0101"};
    AutoCompleteTextView typeOfFlatNumberExposedDropdown;
    String typeOfFlatNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tower_society);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        R.layout.list_item,
                        TYPE_OF_FLAT_NUMBER);
        typeOfFlatNumberExposedDropdown = findViewById(R.id.type_of_flat_number);
        typeOfFlatNumberExposedDropdown.setAdapter(adapter);
        typeOfFlatNumberExposedDropdown.setInputType(InputType.TYPE_NULL);
        typeOfFlatNumberExposedDropdown.setKeyListener(null);

        final TextInputEditText ti1 = findViewById(R.id.TextInputEditText0);
        final TextInputEditText ti2 = findViewById(R.id.TextInputEditText01);
        final TextInputEditText ti3 = findViewById(R.id.TextInputEditText1);
        final TextInputEditText ti4 = findViewById(R.id.TextInputEditText2);

        MaterialButton materialButton = findViewById(R.id.nextbtn);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                typeOfFlatNumber = typeOfFlatNumberExposedDropdown.getText().toString();

                if (ti1.getText().toString().trim().equals("")) {
                    ti1.setError("Enter Name of tower");
                    ti1.requestFocus();
                    return;
                }

                if (ti2.getText().toString().trim().equals("")) {
                    ti2.setError("Enter Short Code");
                    ti2.requestFocus();
                    return;
                }

                if (ti3.getText().toString().trim().equals("")) {
                    ti3.setError("Enter Number of floors in the tower");
                    ti3.requestFocus();
                    return;
                }

                if (ti4.getText().toString().trim().equals("")) {
                    ti4.setError("Enter Number of Flats on each floor");
                    ti4.requestFocus();
                    return;
                }

                if (typeOfFlatNumber.trim().equals("")) {
                    typeOfFlatNumberExposedDropdown.setError("Enter Type of Flat Number");
                    typeOfFlatNumberExposedDropdown.requestFocus();
                    return;
                }

                try {
//                    writer = new CSVWriter(new FileWriter(file));
                    int code = 10;
                    if (typeOfFlatNumber.equals("11"))
                        code = 10;
                    else if (typeOfFlatNumber.equals("101"))
                        code = 100;
                    else if (typeOfFlatNumber.equals("0101"))
                        code = 1000;

//                    typeOfFlatNumber
                    List<String[]> data = new ArrayList<String[]>();

                    data.add(new String[]{"serial_number", "label", "short_code"});
                    for (int i = 1; i < Integer.parseInt(ti3.getText().toString()) + 1; i++) {
                        for (int j = 1; j < Integer.parseInt(ti4.getText().toString()) + 1; j++) {
                            int room = i * (code == 1000 ? 100 : code) + j;

                            data.add(new String[]{"" + ((i - 1) * Integer.parseInt(ti4.getText().toString()) + j),
                                    ti1.getText().toString() + "-"
//                                            +ti2.getText().toString()+"-"
                                            + (code == 1000 ? "0" + room : room),
                                    ti2.getText().toString() + room
                            });
                        }
                    }
                    StringBuilder data1 = new StringBuilder();
                    for (int i = 0; i < data.size(); i++) {
                        data1.append("\n" + data.get(i)[0] + "," + data.get(i)[1] + "," + data.get(i)[2]);
                    }
                    String baseFolder;
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        baseFolder = getExternalFilesDir(null).getAbsolutePath();
                    } else {
                        baseFolder = getFilesDir().getAbsolutePath();
                    }

                    Date currentTime = Calendar.getInstance().getTime();
                    File file = new File(baseFolder + "/" + HomeActivity.name_of_society + "[" + currentTime + "]" + ".csv");

                    FileOutputStream out = new FileOutputStream(file);
                    out.write((data1.toString()).getBytes());
                    out.close();


//                    final Uri uriData = FileProvider.getUriForFile(getApplicationContext(),
//                            BuildConfig.APPLICATION_ID + ".provider", file);
//                    grantUriPermission(getPackageName(), uriData, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    final Intent intent = new Intent(Intent.ACTION_VIEW)
//                            .setDataAndType(uriData, "*/*")
//                            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    startActivity(intent);

                    final BottomDialogFragment bottomDialogFragment =
                            BottomDialogFragment.newInstance();
                    bottomDialogFragment.show(getSupportFragmentManager(),
                            "botom");

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                bottomDialogFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface dialogInterface) {
                                                        Intent intent = new Intent(SingleTowerSociety.this, HomeActivity.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(intent);
                                                        finish(); }
                                                });
                                            }
                                        }
                            , 10);

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Error " + e, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }


}