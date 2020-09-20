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

public class FinalMultiGroup2 extends AppCompatActivity {

    public static int no_of_group = 0;
    public static List<tower_list> list_of_tower = new ArrayList<tower_list>();

    String[] TYPE_OF_FLAT_NUMBER = new String[]{"11", "101", "0101"};
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

                    list_of_tower.get(index).getTowerDataList().add(new towerData(t1.getText().toString(),
                            t2.getText().toString(), t3.getText().toString(), t4.getText().toString()
                    ));
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

                    tower_list tList= list_of_tower.get(index);
                    if (typeOfFlatNumber.equals("11"))
                        tList.setCount(10);
                    else if (typeOfFlatNumber.equals("101"))
                        tList.setCount(100);
                    else if (typeOfFlatNumber.equals("0101"))
                        tList.setCount(1000);
                    list_of_tower.set(index,tList);

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

                    tower_list tList= list_of_tower.get(index);
                    if (typeOfFlatNumber.equals("11"))
                        tList.setCount(10);
                    else if (typeOfFlatNumber.equals("101"))
                        tList.setCount(100);
                    else if (typeOfFlatNumber.equals("0101"))
                        tList.setCount(1000);
                    list_of_tower.set(index,tList);


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
                                        int room = i * (list_of_tower.get(m).getCount() == 1000 ? 100 : list_of_tower.get(m).getCount()) + j;
                                        data.add(new String[]{"" + k,
                                                twList.getName() + "-" +
//                                                        twList.getCode() + "-" +
                                                        twData.getName() + "-" +
//                                                        twData.getCode() + "-" +
                                                        (list_of_tower.get(m).getCount() == 1000 ? "0" + room : room),
                                                twList.getCode() + twData.getCode() + room
                                        });
                                        k++;
                                    }
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

    public static class tower_list {
        String name;
        String code;
        int no_of_tower;
        int count;
        List<towerData> towerDataList = new ArrayList<>();


        public tower_list(String name, String code, int no_of_tower) {
            this.name = name;
            this.code = code;
            this.no_of_tower = no_of_tower;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public towerData(String name, String code, String floor, String flat) {
            this.name = name;
            this.code = code;
            this.floor = floor;
            this.flat = flat;
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