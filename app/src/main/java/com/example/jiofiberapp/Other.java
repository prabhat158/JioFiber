package com.example.jiofiberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
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

public class Other extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);


        final TextInputEditText ti1 = findViewById(R.id.TextInputEditText0);
        final TextInputEditText ti3 = findViewById(R.id.TextInputEditText1);

        MaterialButton materialButton = findViewById(R.id.nextbtn);


        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ti1.getText().toString().equals("") || ti3.getText().toString().equals(""))
                    return;

                try {
//                    writer = new CSVWriter(new FileWriter(file));

                    List<String[]> data = new ArrayList<String[]>();

                    data.add(new String[]{"serial_number", "label", "short_code"});
                    data.add(new String[]{"1", ti1.getText().toString()+"-"+ti3.getText().toString(), ti3.getText().toString()});

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

                    startActivity(new Intent(Other.this, HomeActivity.class));
                    Toast.makeText(getApplicationContext(),"Your inputs have been recorded", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Error "+ e, Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }
            }
        });


    }
}