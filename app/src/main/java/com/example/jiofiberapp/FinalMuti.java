package com.example.jiofiberapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jiofiberapp.model.TowerVO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class FinalMuti extends AppCompatActivity {

    LinearLayout linearLayout;
    MaterialButton materialButton;
    String nameOfSociety;
    TextView societyName;
    TextView numberOfTowerTextView;

    HashSet<Integer> uniqueRoomList = new HashSet<>();

//    String[] TYPE_OF_FLAT_NUMBER = new String[]{"Two Digit", "Three Digit", "Four Digit"};
//    AutoCompleteTextView typeOfFlatNumberExposedDropdown;
//    String typeOfFlatNumber = "";

    boolean isFixFirstFlatNumber;
    String buildingID;
    String fixFirstFlatNumber;
    int digit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_muti);

        Bundle bundle = getIntent().getExtras();
        buildingID = bundle.getString("buildingID");
        nameOfSociety = bundle.getString("nameOfTowerOrSociety");
        final int num = Integer.parseInt(bundle.getString("number"));
        fixFirstFlatNumber = bundle.getString("fixFirstFlatNumber");
        digit = bundle.getInt("digit");
        isFixFirstFlatNumber = bundle.getBoolean("isStartFlatNumberSame");


        numberOfTowerTextView = findViewById(R.id.numberOfTowerTextView);
        societyName = findViewById(R.id.societyName);
        linearLayout = findViewById(R.id.linearlayout);
        materialButton = findViewById(R.id.nextbtn);

        societyName.setText("Society Name: " + nameOfSociety);
        numberOfTowerTextView.setText(num == 1 ? "Number Of Tower: " + num : "Number Of Towers: " + num);

        //        ArrayAdapter<String> adapter =
//                new ArrayAdapter<>(this,
//                        R.layout.list_item,
//                        TYPE_OF_FLAT_NUMBER);
//        typeOfFlatNumberExposedDropdown = findViewById(R.id.type_of_flat_number);
//        typeOfFlatNumberExposedDropdown.setAdapter(adapter);
//        typeOfFlatNumberExposedDropdown.setInputType(InputType.TYPE_NULL);
//        typeOfFlatNumberExposedDropdown.setKeyListener(null);


        final List<View> list = new ArrayList<View>();
        for (int i = 0; i < num; i++) {
            final LayoutInflater factory = getLayoutInflater();
            final View textEntryView = factory.inflate(R.layout.basicform, null);
            TextView textView = textEntryView.findViewById(R.id.towerno);
            TextInputLayout textField4 = textEntryView.findViewById(R.id.textField4);
            textView.setText("Tower " + (i + 1));

            if (isFixFirstFlatNumber) {
                textField4.setVisibility(View.GONE);
            } else {
                textField4.setVisibility(View.VISIBLE);
            }

            list.add(textEntryView);
            linearLayout.addView(textEntryView);
        }

        final List<String> range0TO9ShortsCode = getRange0TO9ShortsCode();
        final List<String> range21To99ShortsCode = getRange21TO99ShortsCode();

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                typeOfFlatNumber = typeOfFlatNumberExposedDropdown.getText().toString();

//                if (typeOfFlatNumber.trim().equals("")) {
//                    typeOfFlatNumberExposedDropdown.setError("Enter Type of Flat Number");
//                    typeOfFlatNumberExposedDropdown.requestFocus();
//                    return;
//                }

//                int digit = 0;
//                if (typeOfFlatNumber.equals("Two Digit")) {
//                    digit = 2;
//                } else if (typeOfFlatNumber.equals("Three Digit")) {
//                    digit = 3;
//                } else if (typeOfFlatNumber.equals("Four Digit")) {
//                    digit = 4;
//                }


                int k = 1;
                boolean isBuildingId = true;
                List<String[]> data = new ArrayList<String[]>();
                data.add(new String[]{"SN", "Building id", "Towers", "Flats", "Labels", "Short-code"});

                for (int m = 0; m < list.size(); m++) {

                    View view1 = list.get(m);


                    TextInputEditText t1 = view1.findViewById(R.id.TextInputEditText0);
//                        TextInputEditText t2 = view1.findViewById(R.id.TextInputEditText01);
                    TextInputEditText t3 = view1.findViewById(R.id.TextInputEditText1);
                    TextInputEditText t4 = view1.findViewById(R.id.TextInputEditText2);
                    TextInputEditText firstFlatInTowerEditText = view1.findViewById(R.id.TextInputEditText4);
//                        CheckBox skipGroundFloorCheckBox = findViewById(R.id.skipGroundFloorCheckBox);


                    if (t1.getText().toString().trim().equals("")) {
                        t1.setError("Enter name of towers");
                        t1.requestFocus();
                        return;
                    }

//                        if (t2.getText().toString().trim().equals("")) {
//                            t2.setError("Enter Short Code");
//                            t2.requestFocus();
//                            return;
//                        }

                    if (t3.getText().toString().trim().equals("")) {
                        t3.setError("Enter number of floors in the tower");
                        t3.requestFocus();
                        return;
                    }

                    if (t4.getText().toString().trim().equals("")) {
                        t4.setError("Enter number of Flats on each floor");
                        t4.requestFocus();
                        return;
                    }

                    String towerName = t1.getText().toString();

//                    int startingFloor = 1;
                    boolean skipGround;
                    boolean addTowerName = true;
                    String firstFlatNumber = "";


                    if (isFixFirstFlatNumber) {
//                        startingFloor = Integer.parseInt(fixFirstFlatNumber.substring(0, 1));
//                        skipGround = Integer.parseInt(fixFirstFlatNumber.substring(0, 1)) >= 1;

                        firstFlatNumber = fixFirstFlatNumber;

                        if ((Integer.parseInt(fixFirstFlatNumber) == 1) && (Integer.parseInt(fixFirstFlatNumber.substring(0, 1)) == 0) && (Integer.parseInt(fixFirstFlatNumber.substring(0, 2)) == 0) && (fixFirstFlatNumber.length() == 3 || fixFirstFlatNumber.length() == 4)) {
                            skipGround = false;
                        } else if ((Integer.parseInt(fixFirstFlatNumber) == 1) && (Integer.parseInt(fixFirstFlatNumber.substring(0, 1)) == 0) && fixFirstFlatNumber.length() == 2) {
                            skipGround = false;
                        } else if ((fixFirstFlatNumber.length() == 2 || fixFirstFlatNumber.length() == 3) && Integer.parseInt(fixFirstFlatNumber.substring(0, 1)) == 1) {
                            skipGround = (Integer.parseInt(fixFirstFlatNumber.substring(0, 1)) >= 1);
                        } else if (Integer.parseInt(fixFirstFlatNumber.substring(0, 1)) < 9 && fixFirstFlatNumber.length() == 1) {
                            skipGround = false;
                        } else
                            skipGround = (Integer.parseInt(fixFirstFlatNumber.substring(0, 1)) >= 1);

                    } else {
                        if (firstFlatInTowerEditText.getText().toString().equals("")) {
                            firstFlatInTowerEditText.requestFocus();
                            firstFlatInTowerEditText.setError("Enter first flat number on beginning of residential floor");
                            return;
                        }

                        int digit2 = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(firstFlatInTowerEditText.getText().toString())))) + 1;

                        if (digit2 < 0) {
                            firstFlatInTowerEditText.requestFocus();
                            firstFlatInTowerEditText.setError("Please enter valid input");
                            return;
                        }

                        if (digit2 <= 2) {
                            firstFlatInTowerEditText.requestFocus();
                            firstFlatInTowerEditText.setError("Enter 3 digit number");
                            return;
                        }

//                        startingFloor = Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1));
//                        skipGround = Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1)) >= 1;

                        firstFlatNumber = firstFlatInTowerEditText.getText().toString();

                        if ((Integer.parseInt(firstFlatInTowerEditText.getText().toString()) == 1) && (Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1)) == 0) && (Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 2)) == 0) && (firstFlatInTowerEditText.getText().toString().length() == 3 || firstFlatInTowerEditText.getText().toString().length() == 4)) {
                            skipGround = false;
                        } else if ((Integer.parseInt(firstFlatInTowerEditText.getText().toString()) == 1) && (Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1)) == 0) && firstFlatInTowerEditText.getText().toString().length() == 2) {
                            skipGround = false;
                        } else if ((firstFlatInTowerEditText.getText().toString().length() == 2 || firstFlatInTowerEditText.getText().toString().length() == 3) && Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1)) == 1) {
                            skipGround = (Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1)) >= 1);
                        } else if (Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1)) < 9 && firstFlatInTowerEditText.getText().toString().length() == 1) {
                            skipGround = false;
                        } else
                            skipGround = (Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1)) >= 1);

                    }


                    if (digit == 2) {

                        int index = Integer.parseInt(t3.getText().toString()) * Integer.parseInt(t4.getText().toString());
                        String room = "";
                        for (int i = 1; i <= index; i++) {
                            room = (i >= 10 ? "2" + i : "20" + i);

                            String serial_number = (i + 1) + "";
                            String label = t1.getText().toString() + "-" + (i >= 10 ? "" + i : "0" + i);

                            String sh = (i >= 10 ? "" + i : "0" + i);
                            String short_code = getShortCodeFor2Digit(num, sh, m, range0TO9ShortsCode, range21To99ShortsCode);
                            uniqueRoomList.add(Integer.valueOf(room));

                            data.add(new String[]{serial_number, (isBuildingId ? buildingID : ""), (addTowerName ? towerName : ""), "0", label, short_code});
                            addTowerName = false;
                            isBuildingId = false;
                        }

                    } else {
                        for (int i = skipGround ? 1 : 0; i < Integer.parseInt(t3.getText().toString()) + 1; i++) {
                            for (int j = 1; j < Integer.parseInt(t4.getText().toString()) + 1; j++) {

                                String room = "";
                                if (digit == 2) {
                                    if (skipGround) {
                                        //                        room = (i * 10 + j) + "";
                                        if (firstFlatNumber.length() == 1) {

                                            if (i == 1)
                                                room = j >= 10 ? "0" + j : "0" + j;
                                            else
                                                room = (i * 10 + j) + "";

                                        } else if (firstFlatNumber.length() == 2) {
                                            room = (i * 10 + j) + "";

                                        } else
                                            room = (i == 1) ? (j >= 10 ? "" + j : "0" + j) : ((i - 1) * 10 + j) + "";

                                    } else {
                                        room = (i == 1) ? (j >= 10 ? "" + j : "0" + j) : ((i - 1) * 10 + j) + "";
                                    }
                                } else if (digit == 3) {
                                    if (skipGround) {
//                        room = (i * 100 + j) + "";

                                        if (firstFlatNumber.length() == 1) {

                                            if (i == 1)
                                                room = j >= 10 ? "0" + j : "00" + j;
                                            else if (i >= 2 && i <= 9)
                                                room = "0" + (i * 100 + j) + "";
                                            else
                                                room = (i * 100 + j) + "";

                                        } else if (firstFlatNumber.length() == 2) {

                                            if (i == 1)
                                                room = "0" + (i * 10 + j) + "";
                                            else if (i >= 2 && i <= 9)
                                                room = "0" + (i * 100 + j) + "";
                                            else
                                                room = (i * 100 + j) + "";

                                        } else
                                            room = (i == 1) ? (j >= 10 ? "0" + j : "00" + j) : ((i - 1) * 100 + j) + "";

                                    } else {

                                        if (i == 0)
                                            room = (j >= 10 ? "0" + j : "00" + j);
                                        else if (i >= 1 && i <= 9)
                                            room = (i * 100 + j) + "";
                                        else
                                            room = (i * 100 + j) + "";

//                        room = (i == 1) ? (j >= 10 ? "0" + j : "00" + j) : ((i - 1) * 100 + j) + "";
                                    }
                                } else if (digit == 4) {
                                    if (skipGround) {

                                        int temp = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(firstFlatNumber)))) + 1;
                                        // Check first room number is 1 or 3  or 2 digit

                                        if ((firstFlatNumber.length() == 2 || firstFlatNumber.length() == 3 || firstFlatNumber.length() == 4) && temp == 1) {
                                            if (i == 0)
                                                room = "000" + (i * 10 + j) + "";
                                            else if (i >= 1 && i <= 9)
                                                room = "0" + (i * 100 + j) + "";
                                            else
                                                room = (i * 100 + j) + "";

                                        } else if (firstFlatNumber.length() == 1) {

                                            if (i == 1)
                                                room = j >= 10 ? "00" + j : "000" + j;
                                            else if (i >= 2 && i <= 9)
                                                room = "0" + (i * 100 + j) + "";
                                            else
                                                room = (i * 100 + j) + "";

                                        } else if (firstFlatNumber.length() == 2) {

                                            if (i == 1)
                                                room = "00" + (i * 10 + j) + "";
                                            else if (i >= 2 && i <= 9)
                                                room = "0" + (i * 100 + j) + "";
                                            else
                                                room = (i * 100 + j) + "";

                                        } else { // 3 digit

                                            room = (i * 100 + j) + "";

                                            // This is for if number length 3 digit then append 0 at begging
                                            int roomLength = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(room)))) + 1;
                                            if (roomLength == 3) {
                                                room = "0" + room;
                                            }
                                        }

                                    } else {

                                        if (i == 0)
                                            room = (j >= 10 ? "00" + j : "000" + j);
                                        else if (i >= 1 && i <= 9)
                                            room = "0" + (i * 100 + j) + "";
                                        else
                                            room = (i * 100 + j) + "";

//                        if (i < 9)
//                            room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) * 1000 + j) + "";
//                        else
//                            room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) >= 10) ? (((i - 1) * 100 + j) + "") : ((i - 1) * 1000 + j) + "";
                                    }
                                }
//                                int room = i * (code == 1000 ? 100 : code) + j;

                                String serial_number = "" + k;
//                                String label = t1.getText().toString() + "-" + Integer.parseInt(room);
//                            String label = nameOfSociety + "-" + t1.getText().toString() + "-" + Integer.parseInt(room);
                                String label = t1.getText().toString() + "-" + Integer.parseInt(room);

//                                String short_code = t2.getText().toString() + room;

                                String short_code = getShortCode(num, room, m, range0TO9ShortsCode, range21To99ShortsCode);


                                String Flat_Numbers = room;

                                uniqueRoomList.add(Integer.valueOf(room));

                                data.add(new String[]{serial_number, (isBuildingId ? buildingID : ""), (addTowerName ? towerName : ""), "0", label, short_code});
                                addTowerName = false;
                                isBuildingId = false;
                                k++;
                            }
                        }
                    }
                }


//                  data.add(new String[]{"SN 0", "Building id 1", "Towers 2", "Flats 3", "Labels 4", "Short-code 5"});
                List<TowerVO> finalList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    finalList.add(new TowerVO(i == 0 ? data.get(i)[0] : String.valueOf(i), data.get(i)[1], data.get(i)[2], data.get(i)[3], data.get(i)[4], data.get(i)[5]));
                }

                int counter = 1;
                TreeSet<Integer> temp = new TreeSet<>(uniqueRoomList);
                for (Integer key : temp) {
                    TowerVO towerVO = finalList.get(counter);
                    towerVO.setFlats(String.valueOf(key));
                    finalList.set(counter, towerVO);
                    counter++;
                }


                List<String> towerNameList = new ArrayList<>();
                for (int i = 1; i < finalList.size(); i++) {
                    TowerVO towerVO = finalList.get(i);
                    if (towerVO.getTowers().length() > 0) {
                        towerNameList.add(towerVO.getTowers());
                        towerVO.setTowers("");
                        finalList.set(i, towerVO);
                    }
                }

                for (int i = 0; i < towerNameList.size(); i++) {
                    TowerVO towerVO = finalList.get(i+1);
                    towerVO.setTowers(towerNameList.get(i));
                    finalList.set(i + 1, towerVO);
                }

                Intent intent = new Intent(FinalMuti.this, SampleActivity.class);
                intent.putParcelableArrayListExtra("LIST", (ArrayList<? extends Parcelable>) finalList);
                intent.putExtra("isSingleTower", false);
                intent.putExtra("digit", digit);
                startActivity(intent);
            }
        });
    }

    private String getShortCodeFor2Digit(int num, String room, int index, List<String> Range0TO9ShortsCode, List<String> Range21TO99ShortsCode) {
        if (num > 9) {
            return Range21TO99ShortsCode.get(index) + room;
        } else {
            if (Range0TO9ShortsCode.get(index).equals("0")) {
                return room;
            } else {
                return Range0TO9ShortsCode.get(index) + room;
            }
        }
    }

    private String getShortCode(int num, String room, int index, List<String> Range0TO9ShortsCode, List<String> Range21TO99ShortsCode) {
        if (num > 9) {
            return Range21TO99ShortsCode.get(index) + room;
        } else {
            if (Range0TO9ShortsCode.get(index).equals("0")) {
                return "0-" + room;
            } else {
                return Range0TO9ShortsCode.get(index) + room;
            }
        }
    }

    private List<String> getRange0TO9ShortsCode() {
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("0");
        return list;
    }


    private List<String> getRange21TO99ShortsCode() {
        List<String> list = new ArrayList<>();
        for (int i = 21; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}