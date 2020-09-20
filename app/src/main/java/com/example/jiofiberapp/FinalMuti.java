package com.example.jiofiberapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class FinalMuti extends AppCompatActivity {

    LinearLayout linearLayout;
    MaterialButton materialButton;

    String[] TYPE_OF_FLAT_NUMBER = new String[]{"11", "101", "0101"};
    AutoCompleteTextView typeOfFlatNumberExposedDropdown;
    String typeOfFlatNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_muti);

        linearLayout = findViewById(R.id.linearlayout);
        materialButton = findViewById(R.id.nextbtn);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        R.layout.list_item,
                        TYPE_OF_FLAT_NUMBER);
        typeOfFlatNumberExposedDropdown = findViewById(R.id.type_of_flat_number);
        typeOfFlatNumberExposedDropdown.setAdapter(adapter);
        typeOfFlatNumberExposedDropdown.setInputType(InputType.TYPE_NULL);
        typeOfFlatNumberExposedDropdown.setKeyListener(null);



        Intent intent = getIntent();
        int num = Integer.parseInt(intent.getStringExtra("number"));

        final List<View> list = new ArrayList<View>();

        for (int i = 0; i < num; i++) {
            final LayoutInflater factory = getLayoutInflater();
            final View textEntryView = factory.inflate(R.layout.basicform, null);
            TextView textView = textEntryView.findViewById(R.id.towerno);
            textView.setText("Tower " + (i + 1));
            list.add(textEntryView);
            linearLayout.addView(textEntryView);
        }

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeOfFlatNumber = typeOfFlatNumberExposedDropdown.getText().toString();

                if (typeOfFlatNumber.trim().equals("")) {
                    typeOfFlatNumberExposedDropdown.setError("Enter Type of Flat Number");
                    typeOfFlatNumberExposedDropdown.requestFocus();
                    return;
                }
                int code = 10;
                if (typeOfFlatNumber.equals("11"))
                    code = 10;
                else if (typeOfFlatNumber.equals("101"))
                    code = 100;
                else if (typeOfFlatNumber.equals("0101"))
                    code = 1000;


                int k = 1;
                try {
                    List<String[]> data = new ArrayList<String[]>();
                    data.add(new String[]{"serial_number", "label", "short_code"});

                    for (View view1 : list) {
                        TextInputEditText t1 = view1.findViewById(R.id.TextInputEditText0);
                        TextInputEditText t2 = view1.findViewById(R.id.TextInputEditText01);
                        TextInputEditText t3 = view1.findViewById(R.id.TextInputEditText1);
                        TextInputEditText t4 = view1.findViewById(R.id.TextInputEditText2);

                        if (t1.getText().toString().trim().equals("")) {
                            t1.setError("Enter Number of towers");
                            t1.requestFocus();
                            return;
                        }

                        if (t2.getText().toString().trim().equals("")) {
                            t2.setError("Enter Short Code");
                            t2.requestFocus();
                            return;
                        }

                        if (t3.getText().toString().trim().equals("")) {
                            t3.setError("Enter Number of floors in the tower");
                            t3.requestFocus();
                            return;
                        }

                        if (t4.getText().toString().trim().equals("")) {
                            t4.setError("Enter Number of Flats on each floor");
                            t4.requestFocus();
                            return;
                        }

                        for (int i = 1; i < Integer.parseInt(t3.getText().toString()) + 1; i++) {
                            for (int j = 1; j < Integer.parseInt(t4.getText().toString()) + 1; j++) {
                                int room = i * (code == 1000 ? 100 : code) + j;
                                data.add(new String[]{"" + k,
                                        t1.getText().toString() + "-" +
//                                                t2.getText().toString() + "-" +
                                                (code == 1000 ? "0" + room : room),
                                        t2.getText().toString() + room
                                });
                                k++;
                            }
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
                                                        Intent intent = new Intent(FinalMuti.this, HomeActivity.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                            }
                                        }
                            , 10);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}