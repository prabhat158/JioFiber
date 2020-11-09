package com.example.jiofiberapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import easyfilepickerdialog.kingfisher.com.library.model.DialogConfig;
import easyfilepickerdialog.kingfisher.com.library.model.SupportFile;
import easyfilepickerdialog.kingfisher.com.library.view.FilePickerDialogFragment;

public class SingleTowerSociety extends AppCompatActivity {

    //    String[] TYPE_OF_FLAT_NUMBER = new String[]{"Two Digit", "Three Digit", "Four Digit"};
    //    AutoCompleteTextView typeOfFlatNumberExposedDropdown;
    int digit;
    int firstFlatNumber;
    String nameOfTower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tower_society);

        Bundle bundle = getIntent().getExtras();
        firstFlatNumber = bundle.getInt("firstFlatNumber");
        digit = bundle.getInt("typeOfFlatNumber");
        nameOfTower = bundle.getString("nameOfTowerOrSociety");

//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<>(this,
//                        R.layout.list_item,
//                        TYPE_OF_FLAT_NUMBER);
//        typeOfFlatNumberExposedDropdown = findViewById(R.id.type_of_flat_number);
//        typeOfFlatNumberExposedDropdown.setAdapter(adapter);
//        typeOfFlatNumberExposedDropdown.setInputType(InputType.TYPE_NULL);
//        typeOfFlatNumberExposedDropdown.setKeyListener(null);

//        final TextInputEditText ti1 = findViewById(R.id.TextInputEditText0);
//        final TextInputEditText ti2 = findViewById(R.id.TextInputEditText01);
        final TextInputEditText ti3 = findViewById(R.id.TextInputEditText1);
        final TextInputEditText ti4 = findViewById(R.id.TextInputEditText2);
//        final CheckBox skipGroundFloorCheckBox = findViewById(R.id.skipGroundFloorCheckBox);

        MaterialButton materialButton = findViewById(R.id.nextbtn);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                typeOfFlatNumber = typeOfFlatNumberExposedDropdown.getText().toString();


//                if (ti1.getText().toString().trim().equals("")) {
//                    ti1.setError("Enter Name of tower");
//                    ti1.requestFocus();
//                    return;
//                }

//                if (ti2.getText().toString().trim().equals("")) {
//                    ti2.setError("Enter Short Code");
//                    ti2.requestFocus();
//                    return;
//                }

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

//                if (typeOfFlatNumber.trim().equals("")) {
//                    typeOfFlatNumberExposedDropdown.setError("Enter Type of Flat Number");
//                    typeOfFlatNumberExposedDropdown.requestFocus();
//                    return;
//                }

                try {
//                  writer = new CSVWriter(new FileWriter(file));


                    List<String[]> data = new ArrayList<String[]>();

                    data.add(new String[]{"serial_number", "label", "short_code"});
                    for (int i = 1; i < Integer.parseInt(ti3.getText().toString()) + 1; i++) {
                        for (int j = 1; j < Integer.parseInt(ti4.getText().toString()) + 1; j++) {

                            String room = "";
                            if (digit == 2) {
//                                if (skipGroundFloorCheckBox.isChecked()) {
//                                    room = (i * 10 + j) + "";
//                                } else {
                                room = (i == 1) ? (j >= 10 ? "" + j : "0" + j) : ((i - 1) * 10 + j) + "";
//                                }
                            } else if (digit == 3) {
//                                if (skipGroundFloorCheckBox.isChecked()) {
//                                    room = (i * 100 + j) + "";
//                                } else {
                                room = (i == 1) ? (j >= 10 ? "0" + j : "00" + j) : ((i - 1) * 100 + j) + "";
//                                }
                            } else if (digit == 4) {
//                                if (skipGroundFloorCheckBox.isChecked()) {
//                                    room = (i * 100 + j) + "";
//                                } else {
//                                    room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) * 1000 + j) + "";
                                room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) >= 10) ? (((i - 1) * 100 + j) + "") : ((i - 1) * 1000 + j) + "";
//                                }
                            }

                            String serial_number = "" + ((i - 1) * Integer.parseInt(ti4.getText().toString()) + j);
//                            String label = ti1.getText().toString() + "-" + room;
                            String label = nameOfTower + "-" + room;
                            String short_code = room;
//                            String short_code = ti2.getText().toString() + room;

//                            data.add(new String[]{serial_number, label, short_code});
                            data.add(new String[]{serial_number, label, short_code});

                            /* data.add(
                                    new String[]{"" + ((i - 1) * Integer.parseInt(ti4.getText().toString()) + j),
                                            ti1.getText().toString() + "-" + (code == 1000 ? "0" + room : room),
                                            ti2.getText().toString() + room
                                    });*/
                        }
                    }
                    StringBuilder data1 = new StringBuilder();
                    for (int i = 0; i < data.size(); i++) {
                        data1.append("\n" + data.get(i)[0] + "," + data.get(i)[1] + "," + data.get(i)[2]);
                    }
                    final String baseFolder;
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        baseFolder = getExternalFilesDir(null).getAbsolutePath();
                    } else {
                        baseFolder = getFilesDir().getAbsolutePath();
                    }

                    Date currentTime = Calendar.getInstance().getTime();
//                    final File file = new File(baseFolder + "/" + HomeActivity.name_of_society + "[" + currentTime + "]" + ".csv");
                    final File file = new File(baseFolder + "/" + HomeActivity.name_of_society + ".csv");

                    FileOutputStream out = new FileOutputStream(file);
                    out.write((data1.toString()).getBytes());
                    out.close();

                    final BottomDialogFragment bottomDialogFragment =
                            BottomDialogFragment.newInstance();
                    bottomDialogFragment.show(getSupportFragmentManager(),
                            "botom");

                    bottomDialogFragment.setManageClickContract(new BottomDialogFragment.ManageClickContract() {
                        @Override
                        public void viewFile() {
                            DialogConfig dialogConfig = new DialogConfig.Builder()
                                    .enableMultipleSelect(false) // default is false
                                    .enableFolderSelect(true) // default is false
                                    .initialDirectory(baseFolder) // default is sdcard
                                    .supportFiles(new SupportFile(".csv", 0)) // default is showing all file types.
                                    .build();

                            new FilePickerDialogFragment.Builder()
                                    .configs(dialogConfig)
                                    .onFilesSelected(new FilePickerDialogFragment.OnFilesSelectedListener() {
                                        @Override
                                        public void onFileSelected(List<File> list) {
                                            for (File file : list) {
                                                new ManageMethod().openFile(file, getApplicationContext());
                                            }
                                        }
                                    })/*.onFolderLoadListener(new FilePickerDialogFragment.OnFolderLoadListener() {
                                        @Override
                                        public void onLoadFailed(String path) {
                                            //Could not access folder because of user permissions, sdcard is not readable...
                                        }
                                    })*/
                                    .build()
                                    .show(getSupportFragmentManager(), null);
                        }

                        @Override
                        public void shareFile() {
                            shareFileToUser(file);
                        }
                    });

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
                                                        finish();
                                                    }
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

    public void shareFileToUser(File file) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),
                BuildConfig.APPLICATION_ID + ".provider", file));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}