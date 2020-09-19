package com.example.jiofiberapp;

import android.content.Intent;
import android.net.Uri;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import fr.tvbarthel.intentshare.IntentShare;

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

                try {
//                    writer = new CSVWriter(new FileWriter(file));

                    List<String[]> data = new ArrayList<String[]>();

                    data.add(new String[]{"serial_number", "label", "short_code"});
                    for (int i = 1; i < Integer.parseInt(ti3.getText().toString()) + 1; i++) {
                        for (int j = 1; j < Integer.parseInt(ti4.getText().toString()) + 1; j++) {
                            int room = i * 100 + j;
                            data.add(new String[]{"" + ((i - 1) * Integer.parseInt(ti4.getText().toString()) + j),
                                    ti1.getText().toString() + "-"
//                                            +ti2.getText().toString()+"-"
                                            + room,
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
//                            .setDataAndType(uriData,  "text/csv")
//                            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    startActivity(intent);

//                    String filePath = RealPathUtil.getRealPath(SingleTowerSociety.this, Uri.parse(file.getAbsolutePath()));
//                    IntentShare.with(SingleTowerSociety.this)
//                            .chooserTitle("Select a sharing target : ")
//                            .text("Default text you would like to share.")
//                            .image(Uri.parse(baseFolder + "/" + HomeActivity.name_of_society + "[" + currentTime + "]" + ".csv"))
//                            .mailSubject("Mail subject.")
//                            .mailBody("Extended text you would like to share in mail body.")
//                            .deliver();

                    startActivity(new Intent(SingleTowerSociety.this, HomeActivity.class));
                    Toast.makeText(getApplicationContext(), "Your inputs have been recorded", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Error " + e, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }


}