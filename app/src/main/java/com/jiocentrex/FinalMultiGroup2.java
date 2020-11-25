package com.jiocentrex;

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

public class FinalMultiGroup2 extends AppCompatActivity {

    public static int no_of_group = 0;
    public static List<tower_list> list_of_tower = new ArrayList<tower_list>();

    String[] TYPE_OF_FLAT_NUMBER = new String[]{"Two Digit", "Three Digit", "Four Digit"};
    AutoCompleteTextView typeOfFlatNumberExposedDropdown;
    String typeOfFlatNumber = "";

    LinearLayout linearLayout;
    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_multi_group2);

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
        final int index = intent.getIntExtra("index", 0);

        TextView tv = findViewById(R.id.grpno);
        tv.setText("Group " + (index + 1));
        if ((index + 1) == list_of_tower.size()) {
            materialButton.setText("Submit");
        }

        final List<View> list = new ArrayList<View>();

        for (int i = 0; i < list_of_tower.get(index).getNo_of_tower(); i++) {
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

                for (View view1 : list) {
                    TextInputEditText t1 = view1.findViewById(R.id.TextInputEditText0);
                    TextInputEditText t2 = view1.findViewById(R.id.TextInputEditText01);
                    TextInputEditText t3 = view1.findViewById(R.id.TextInputEditText1);
                    TextInputEditText t4 = view1.findViewById(R.id.TextInputEditText2);
                    CheckBox skipGroundFloorCheckBox = findViewById(R.id.skipGroundFloorCheckBox);

                    if (t1.getText().toString().trim().equals("")) {
                        t1.setError("Enter Name of tower");
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

                    towerData tData = new towerData(t1.getText().toString(), t2.getText().toString(), t3.getText().toString(), t4.getText().toString());
                    tData.setSkipGroundFloor(skipGroundFloorCheckBox.isChecked());

                    list_of_tower.get(index).getTowerDataList().add(tData);
                }
//                for(View view1: list){
//                    TextInputEditText textInputEditText = view1.findViewById(R.id.TextInputEditText);
//                    Log.d("check",textInputEditText.getText().toString());
//                }
                if ((index + 1) != list_of_tower.size()) {
                    typeOfFlatNumber = typeOfFlatNumberExposedDropdown.getText().toString();
                    if (typeOfFlatNumber.trim().equals("")) {
                        typeOfFlatNumberExposedDropdown.setError("Enter Type of Flat Number");
                        typeOfFlatNumberExposedDropdown.requestFocus();
                        return;
                    }

                    tower_list tList = list_of_tower.get(index);


                    if (typeOfFlatNumber.equals("Two Digit")) {
                        tList.setDigit(2);
                    } else if (typeOfFlatNumber.equals("Three Digit")) {
                        tList.setDigit(3);
                    } else if (typeOfFlatNumber.equals("Four Digit")) {
                        tList.setDigit(4);
                    }

                    list_of_tower.set(index, tList);

                    Intent intent1 = new Intent(FinalMultiGroup2.this, FinalMultiGroup2.class);
                    intent1.putExtra("index", index + 1);
                    startActivity(intent1);
                } else {

                    typeOfFlatNumber = typeOfFlatNumberExposedDropdown.getText().toString();
                    if (typeOfFlatNumber.trim().equals("")) {
                        typeOfFlatNumberExposedDropdown.setError("Enter Type of Flat Number");
                        typeOfFlatNumberExposedDropdown.requestFocus();
                        return;
                    }

                    tower_list tList = list_of_tower.get(index);
                    if (typeOfFlatNumber.equals("Two Digit")) {
                        tList.setDigit(2);
                    } else if (typeOfFlatNumber.equals("Three Digit")) {
                        tList.setDigit(3);
                    } else if (typeOfFlatNumber.equals("Four Digit")) {
                        tList.setDigit(4);
                    }
                    list_of_tower.set(index, tList);

                    int k = 1;
                    try {
                        List<String[]> data = new ArrayList<String[]>();
                        data.add(new String[]{"serial_number", "label", "short_code"});

                        /* for (tower_list twList : list_of_tower)*/
                        for (int m = 0; m < list_of_tower.size(); m++) {
                            tower_list twList = list_of_tower.get(m);
                            for (towerData twData : twList.getTowerDataList()) {

                                for (int i = 1; i < Integer.parseInt(twData.getFloor()) + 1; i++) {
                                    for (int j = 1; j < Integer.parseInt(twData.getFlat()) + 1; j++) {

                                        String room = "";
                                        if (list_of_tower.get(m).getDigit() == 2) {

                                            if (list_of_tower.get(m).isSkipGround()) {
                                                room = (i * 10 + j) + "";
                                            } else {
                                                room = (i == 1) ? (j >= 10 ? "" + j : "0" + j) : ((i - 1) * 10 + j) + "";
                                            }
//                                            room = (i == 1) ? "0" + j : ((i - 1) * 10 + j) + "";
                                        } else if (list_of_tower.get(m).getDigit() == 3) {

                                            if (list_of_tower.get(m).isSkipGround()) {
                                                room = (i * 100 + j) + "";
                                            } else {
                                                room = (i == 1) ? (j >= 10 ? "0" + j : "00" + j) : ((i - 1) * 100 + j) + "";
                                            }
//                                            room = (i == 1) ? "00" + j : ((i - 1) * 100 + j) + "";
                                        } else if (list_of_tower.get(m).getDigit() == 4) {

                                            if (list_of_tower.get(m).isSkipGround()) {
                                                room = (i * 1000 + j) + "";
                                            } else {
//                                                room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) * 1000 + j) + "";
                                                room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) >= 10) ? (((i - 1) * 100 + j) + "") : ((i - 1) * 1000 + j) + "";
                                            }
//                                            room = (i == 1) ? "000" + j : ((i - 1) * 1000 + j) + "";
                                        }

//                                        int room = i * (list_of_tower.get(m).getCount() == 1000 ? 100 : list_of_tower.get(m).getCount()) + j;

                                        String serial_number = "" + k;
                                        String label = twList.getName() + "-" + twData.getName() + "-" + room;
                                        String short_code =  twList.getCode() + twData.getCode() + room;

                                        data.add(new String[]{serial_number, label, short_code});

                                       /* data.add(new String[]{"" + k,
                                                twList.getName() + "-" +*//*twList.getCode() + "-" +*//*twData.getName() + "-" +*//*twData.getCode() + "-" +*//*
                                                        (list_of_tower.get(m).getCount() == 1000 ? "0" + room : room),
                                                twList.getCode() + twData.getCode() + room
                                        });*/
                                        k++;
                                    }
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
//                        final File file = new File(baseFolder + "/" + HomeActivity.name_of_society + "[" + currentTime + "]" + ".csv");
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
                                                            Intent intent = new Intent(FinalMultiGroup2.this, HomeActivity.class);
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

    public static class tower_list {
        String name;
        String code;
        int no_of_tower;
        int digit;
        boolean skipGround;
        List<towerData> towerDataList = new ArrayList<>();


        public tower_list(String name, String code, int no_of_tower) {
            this.name = name;
            this.code = code;
            this.no_of_tower = no_of_tower;
        }

        public boolean isSkipGround() {
            return skipGround;
        }

        public void setSkipGround(boolean skipGround) {
            this.skipGround = skipGround;
        }

        public int getDigit() {
            return digit;
        }

        public void setDigit(int digit) {
            this.digit = digit;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<towerData> getTowerDataList() {
            return towerDataList;
        }

        public void setTowerDataList(List<towerData> towerDataList) {
            this.towerDataList = towerDataList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNo_of_tower() {
            return no_of_tower;
        }

        public void setNo_of_tower(int no_of_tower) {
            this.no_of_tower = no_of_tower;
        }
    }

    public static class towerData {
        String name;
        String code;
        String floor;
        String flat;
        boolean skipGroundFloor;

        public towerData(String name, String code, String floor, String flat) {
            this.name = name;
            this.code = code;
            this.floor = floor;
            this.flat = flat;
        }

        public boolean isSkipGroundFloor() {
            return skipGroundFloor;
        }

        public void setSkipGroundFloor(boolean skipGroundFloor) {
            this.skipGroundFloor = skipGroundFloor;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getFlat() {
            return flat;
        }

        public void setFlat(String flat) {
            this.flat = flat;
        }
    }

}