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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class FinalMuti extends AppCompatActivity {

    LinearLayout linearLayout;
    MaterialButton materialButton;

    String[] TYPE_OF_FLAT_NUMBER = new String[]{"Two Digit", "Three Digit", "Four Digit"};
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

                int digit = 0;
                if (typeOfFlatNumber.equals("Two Digit")) {
                    digit = 2;
                } else if (typeOfFlatNumber.equals("Three Digit")) {
                    digit = 3;
                } else if (typeOfFlatNumber.equals("Four Digit")) {
                    digit = 4;
                }


                int k = 1;
                try {
                    List<String[]> data = new ArrayList<String[]>();
                    data.add(new String[]{"serial_number", "label", "short_code"});

                    for (View view1 : list) {
                        TextInputEditText t1 = view1.findViewById(R.id.TextInputEditText0);
                        TextInputEditText t2 = view1.findViewById(R.id.TextInputEditText01);
                        TextInputEditText t3 = view1.findViewById(R.id.TextInputEditText1);
                        TextInputEditText t4 = view1.findViewById(R.id.TextInputEditText2);
                        CheckBox skipGroundFloorCheckBox = findViewById(R.id.skipGroundFloorCheckBox);

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

                                String room = "";
                                if (digit == 2) {
                                    if (skipGroundFloorCheckBox.isChecked()) {
                                        room = (i * 10 + j) + "";
                                    } else {
                                        room = (i == 1) ? (j >= 10 ? "" + j : "0" + j) : ((i - 1) * 10 + j) + "";
                                    }
                                } else if (digit == 3) {
                                    if (skipGroundFloorCheckBox.isChecked()) {
                                        room = (i * 100 + j) + "";
                                    } else {
                                        room = (i == 1) ? (j >= 10 ? "0" + j : "00" + j) : ((i - 1) * 100 + j) + "";
                                    }
                                } else if (digit == 4) {
                                    if (skipGroundFloorCheckBox.isChecked()) {
                                        room = (i * 1000 + j) + "";
                                    } else {
                                        room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) * 1000 + j) + "";
                                    }
                                }
//                                int room = i * (code == 1000 ? 100 : code) + j;

                                String serial_number = "" + k;
                                String label = t1.getText().toString() + "-" + room;
                                String short_code = t2.getText().toString() + room;

                                data.add(new String[]{serial_number, label, short_code});


                              /*  data.add(new String[]{"" + k,
                                        t1.getText().toString() + "-" +
//                                                t2.getText().toString() + "-" +
                                                (code == 1000 ? "0" + room : room),
                                        t2.getText().toString() + room
                                });*/
                                k++;
                            }
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
                    final File file = new File(baseFolder + "/" + HomeActivity.name_of_society + ".csv");
//                    final File file = new File(baseFolder + "/" + HomeActivity.name_of_society + "[" + currentTime + "]" + ".csv");

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
                            final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("image/jpg");
                            shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),
                                    BuildConfig.APPLICATION_ID + ".provider", file));
                            startActivity(Intent.createChooser(shareIntent, "Share image using"));
                        }
                    });

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

    private void shareFileToUser(File file) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),
                BuildConfig.APPLICATION_ID + ".provider", file));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}