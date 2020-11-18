package com.example.jiofiberapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jiofiberapp.model.TowerVO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class NewSingleTowerActivity extends AppCompatActivity {
    public static String top_floor_last_flat_number_in_the_tallest_tower = "";
    public static String first_flat_number_on_beginning_of_residential_floor = "";
    public static String number_of_digit_in_flat_number = "";
    public static String digits_in_flat_number = "";

    String buildingID;
    String nameOfTower;
    HashSet<Integer> uniqueRoomList = new HashSet<>();


    String TAG = "tag";
    TextInputEditText TextInputEditText1;
    TextInputEditText TextInputEditText2;
    TextInputEditText TextInputEditText4;
    TextInputEditText TextInputEditText5;
    TextInputEditText TextInputEditText6;


    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_single_tower);

        Bundle bundle = getIntent().getExtras();
        buildingID = bundle.getString("buildingID");
        nameOfTower = bundle.getString("nameOfTowerOrSociety");

        TextInputEditText1 = findViewById(R.id.TextInputEditText1);
        TextInputEditText2 = findViewById(R.id.TextInputEditText2);
        TextInputEditText4 = findViewById(R.id.TextInputEditText4);
        TextInputEditText5 = findViewById(R.id.TextInputEditText5);
        TextInputEditText6 = findViewById(R.id.TextInputEditText6);

        materialButton = findViewById(R.id.nextbtn);


        TextInputEditText1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (s.length() != 0 && s.length() >= 2) {
                    int typeOfFlatNumber = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(s.toString())))) + 1;
                    TextInputEditText4.setText(typeOfFlatNumber > 0 ? String.valueOf(typeOfFlatNumber) : "");

                   /* int totalFlatsOnFloor = 0;
                    String floor = "";

                    totalFlatsOnFloor = Integer.parseInt(s.toString()) % 100;

                    if (s.length() == 3)
                        floor = s.toString().substring(0, 1);

                    if (s.length() == 4)
                        floor = s.toString().substring(0, 2);


                    TextInputEditText6.setText(String.valueOf(totalFlatsOnFloor));

                                        TextInputEditText5.setText(floor);

                    */

//                    int firsFlatsNumberOnFloor = (totalFlatsOnFloor - (totalFlatsOnFloor - 1));
//                    TextInputEditText5.setText(String.valueOf(firsFlatsNumberOnFloor));


//                    int flor = Integer.parseInt(s.toString()) / totalFlatsOnFloor;
//                    TextInputEditText5.setText(String.valueOf(flor));


                } else {
                  /*  TextInputEditText5.setText("");
                    TextInputEditText6.setText("");*/
                    TextInputEditText4.setText("");
                }
            }
        });


        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                top_floor_last_flat_number_in_the_tallest_tower = TextInputEditText1.getText().toString();
                first_flat_number_on_beginning_of_residential_floor = TextInputEditText2.getText().toString();
                digits_in_flat_number = TextInputEditText4.getText().toString();


                if (top_floor_last_flat_number_in_the_tallest_tower.equals("")) {
                    TextInputEditText1.requestFocus();
                    TextInputEditText1.setError("Enter top floor Last flat number in the tallest tower");
                    return;
                }

                int digit = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(top_floor_last_flat_number_in_the_tallest_tower)))) + 1;
                TextInputEditText4.setText(String.valueOf(digit));

                if (digit < 0) {
                    TextInputEditText1.requestFocus();
                    TextInputEditText1.setError("Please enter valid input");
                    return;
                }

                if (digit <= 1) {
                    TextInputEditText1.requestFocus();
                    TextInputEditText1.setError("Enter 2 digit number");
                    return;
                }

                if (first_flat_number_on_beginning_of_residential_floor.equals("")) {
                    TextInputEditText2.requestFocus();
                    TextInputEditText2.setError("Enter first flat number on beginning of residential floor");
                    return;
                }


                int digit2 = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(first_flat_number_on_beginning_of_residential_floor)))) + 1;

                if (digit2 < 0) {
                    TextInputEditText2.requestFocus();
                    TextInputEditText2.setError("Please enter valid input");
                    return;
                }


//                if (digit2 <= 2) {
                if (first_flat_number_on_beginning_of_residential_floor.length() < 1 && Integer.parseInt(first_flat_number_on_beginning_of_residential_floor) == 0) {
                    TextInputEditText2.requestFocus();
                    TextInputEditText2.setError("Enter 3 digit number");
                    return;
                }


                if (Integer.parseInt(top_floor_last_flat_number_in_the_tallest_tower) < Integer.parseInt(first_flat_number_on_beginning_of_residential_floor)) {
                    TextInputEditText2.requestFocus();
                    TextInputEditText2.setError("First flat no should be less than last flat no");
                    return;
                }

                if (TextInputEditText5.getText().toString().equals("") || Integer.parseInt(TextInputEditText5.getText().toString()) == 0) {
                    TextInputEditText5.requestFocus();
                    TextInputEditText5.setError("Enter Number of floors in the tower");
                    return;
                }

                if (TextInputEditText6.getText().toString().equals("") || Integer.parseInt(TextInputEditText6.getText().toString()) == 0) {
                    TextInputEditText6.requestFocus();
                    TextInputEditText6.setError("Enter Number of Flats on each floor");
                    return;
                }


                manageLogic(digit, first_flat_number_on_beginning_of_residential_floor, nameOfTower, Integer.parseInt(TextInputEditText5.getText().toString()), Integer.parseInt(TextInputEditText6.getText().toString()));

            }
        });
    }


    private void manageLogic(int digit, String firstFlatNumber, String nameOfTower, int floor, int flatsOnEachfloor) {
//        int temp = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(firstFlatNumber)))) + 1;
        boolean skipGround;
        if ((Integer.parseInt(firstFlatNumber) == 1) && (Integer.parseInt(firstFlatNumber.substring(0, 1)) == 0) && (Integer.parseInt(firstFlatNumber.substring(0, 2)) == 0) && (firstFlatNumber.length() == 3 || firstFlatNumber.length() == 4)) {
//            skipGround = true;
            skipGround = false;
        } else if ((Integer.parseInt(firstFlatNumber) == 1) && (Integer.parseInt(firstFlatNumber.substring(0, 1)) == 0) && firstFlatNumber.length() == 2) {
//            skipGround = true;
            skipGround = false;
        } else if ((firstFlatNumber.length() == 2 || firstFlatNumber.length() == 3) && Integer.parseInt(firstFlatNumber.substring(0, 1)) == 1) {
            skipGround = (Integer.parseInt(firstFlatNumber.substring(0, 1)) >= 1);
        } else if (Integer.parseInt(firstFlatNumber.substring(0, 1)) < 9 && firstFlatNumber.length() == 1) {
            skipGround = false;
        } else
            skipGround = (Integer.parseInt(firstFlatNumber.substring(0, 1)) >= 1);


        boolean isGreaterThanOne = Integer.parseInt(firstFlatNumber.substring(0, 1)) > 1;

        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[]{"SN", "Building id", "Flats", "Labels", "Short-code"});

        int totalFlatIndex = 1;
        boolean isBuildingId = true;

        if (digit == 2) {

            int index = floor * flatsOnEachfloor;
            String room = "";
            for (int i = 1; i <= index; i++) {
                room = (i >= 10 ? "2" + i : "20" + i);

                String serial_number = (i + 1) + "";
                String label = "Flat" + "-" + (i >= 10 ? "" + i : "0" + i);
                String short_code = room;
                uniqueRoomList.add(Integer.valueOf(room));

//                                   "SN", "Building id", "Flats", "Labels", "Short-code"
                data.add(new String[]{serial_number, (isBuildingId ? buildingID : ""), "0", label, short_code});
                isBuildingId = false;
            }


        } else {

            for (int i = skipGround ? 1 : 0; i < floor + 1; i++) {
                for (int j = 1; j < flatsOnEachfloor + 1; j++) {

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

//                        room = (flatFor2DigitCount >= 10 ? "2" + flatFor2DigitCount : "20" + flatFor2DigitCount);
//                        flatFor2DigitCount++;

//                        room = (i == 1) ? (j >= 10 ? "" + j : "0" + j) : ((i - 1) * 10 + j) + "";
                        }
                    } else if (digit == 3) { //
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

                    String serial_number = skipGround ? String.valueOf(totalFlatIndex) : "" + ((i - 1) * flatsOnEachfloor + j);
                    String label = "";
                    String short_code = "";


                    label = "Flat" + "-" + Integer.parseInt(room);
//                String short_code = Integer.parseInt(room.substring(0, 2)) > 9 ? room : "#" + Integer.parseInt(room);
//                String short_code = Integer.parseInt(room.substring(0, 2)) > 9 ? room : "'" + room;
                    short_code = "2" + room;


                    uniqueRoomList.add(Integer.valueOf(room));

//                                   "SN", "Building id", "Flats", "Labels", "Short-code"
                    data.add(new String[]{serial_number, (isBuildingId ? buildingID : ""), "0", label, short_code});
                    isBuildingId = false;
                    totalFlatIndex++;
                }
            }
        }


//          data.add(new String[]{"SN 0", "Building id 1", "Flats 2", "Labels 3", "Short-code 4"});
        List<TowerVO> finalList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            finalList.add(new TowerVO(i == 0 ? data.get(i)[0] : String.valueOf(i), data.get(i)[1], data.get(i)[2], data.get(i)[3], data.get(i)[4]));
        }

        int counter = 1;
        TreeSet<Integer> temp = new TreeSet<>(uniqueRoomList);
        for (Integer key : temp) {
            TowerVO singleTowerVO = finalList.get(counter);
            singleTowerVO.setFlats(String.valueOf(key));
            finalList.set(counter, singleTowerVO);
            counter++;
        }

        Intent intent = new Intent(NewSingleTowerActivity.this, SampleActivity.class);
        intent.putParcelableArrayListExtra("LIST", (ArrayList<? extends Parcelable>) finalList);
        intent.putExtra("isSingleTower", true);
        intent.putExtra("digit", digit);
        startActivity(intent);
    }
}