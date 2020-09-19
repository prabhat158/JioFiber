package com.example.jiofiberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SingleTowerSociety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tower_society);

        final TextInputEditText ti1 = findViewById(R.id.TextInputEditText0);
        final TextInputEditText ti2 = findViewById(R.id.TextInputEditText01);
        final TextInputEditText ti3 = findViewById(R.id.TextInputEditText1);
        final TextInputEditText ti4 = findViewById(R.id.TextInputEditText2);

        MaterialButton materialButton = findViewById(R.id.nextbtn);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ti1.getText().toString().equals("") || ti2.getText().toString().equals("") || ti3.getText().toString().equals("") || ti4.getText().toString().equals("") )
                    return;

                try {
//                    writer = new CSVWriter(new FileWriter(file));

                    List<String[]> data = new ArrayList<String[]>();

                    data.add(new String[]{"serial_number", "label", "short_code"});
                    for(int i = 1; i< Integer.parseInt(ti3.getText().toString())+1; i++){
                        for(int j =1; j<Integer.parseInt(ti4.getText().toString())+1;j++){
                            int room = i*100 + j;
                            data.add(new String[]{""+((i-1)*Integer.parseInt(ti4.getText().toString())+j),
                                    ti1.getText().toString()+"-"
//                                            +ti2.getText().toString()+"-"
                                            + room,
                                    ti2.getText().toString() + room
                            });
                        }
                    }
                    StringBuilder data1 = new StringBuilder();
                    for(int i = 0; i<data.size(); i++){
                        data1.append("\n"+data.get(i)[0]+","+data.get(i)[1]+","+ data.get(i)[2]);
                    }
                    String baseFolder;
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        baseFolder = getExternalFilesDir(null).getAbsolutePath();
                    }else {
                        baseFolder = getFilesDir().getAbsolutePath();
                    }

                    Date currentTime = Calendar.getInstance().getTime();
                    File file = new File(baseFolder +"/"+ HomeActivity.name_of_society + "["+currentTime+"]" +".csv");

                    FileOutputStream out = new FileOutputStream(file);
                    out.write((data1.toString()).getBytes());
                    out.close();

                    startActivity(new Intent(SingleTowerSociety.this, HomeActivity.class));
                    Toast.makeText(getApplicationContext(),"Your inputs have been recorded", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Error "+ e, Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }
            }
        });



    }
}