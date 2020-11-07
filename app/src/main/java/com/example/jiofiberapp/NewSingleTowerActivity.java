package com.example.jiofiberapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.jiofiberapp.model.SingleTowerVO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import easyfilepickerdialog.kingfisher.com.library.model.DialogConfig;
import easyfilepickerdialog.kingfisher.com.library.model.SupportFile;
import easyfilepickerdialog.kingfisher.com.library.view.FilePickerDialogFragment;

public class NewSingleTowerActivity extends AppCompatActivity {
    public static String top_floor_last_flat_number_in_the_tallest_tower = "";
    public static String first_flat_number_on_beginning_of_residential_floor = "";
    //    public static String number_of_digit_in_flat_number = "";
    public static String digits_in_flat_number = "";

    String nameOfTower;
    HashSet<Integer> uniqueRoomList = new HashSet<>();


    String TAG = "tag";
    TextInputEditText TextInputEditText1;
    TextInputEditText TextInputEditText2;
    //    TextInputEditText TextInputEditText3;
    TextInputEditText TextInputEditText4;
    TextInputEditText TextInputEditText5;
    TextInputEditText TextInputEditText6;


    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_single_tower);

        Bundle bundle = getIntent().getExtras();
        nameOfTower = bundle.getString("nameOfTower");

        TextInputEditText1 = findViewById(R.id.TextInputEditText1);
        TextInputEditText2 = findViewById(R.id.TextInputEditText2);
//        TextInputEditText3 = findViewById(R.id.TextInputEditText3);
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
                if (s.length() != 0 && s.length() > 2) {
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
//                number_of_digit_in_flat_number = TextInputEditText3.getText().toString();
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


                if (digit2 <= 2) {
                    TextInputEditText2.requestFocus();
                    TextInputEditText2.setError("Enter 3 digit number");
                    return;
                }

//                if (number_of_digit_in_flat_number.equals("")) {
//                    TextInputEditText3.requestFocus();
//                    TextInputEditText3.setError("Enter Number of digits in flat numbers");
//                    return;
//                }

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


//                Intent intent = new Intent(NewOptionActivity.this, SingleTowerSociety.class);
//                intent.putExtra("nameOfTower", nameOfTower);
//                intent.putExtra("typeOfFlatNumber", typeOfFlatNumber);
//                intent.putExtra("firstFlatNumber", Integer.parseInt(first_flat_number_on_beginning_of_residential_floor));
//                startActivity(intent);
            }
        });
    }


    private void manageLogic(int digit, String firstFlatNumber, String nameOfTower, int floor, int flatsOnEachfloor) {

        boolean skipGround = Integer.parseInt(firstFlatNumber.substring(0, 1)) >= 1;
        boolean isGreaterThanOne = Integer.parseInt(firstFlatNumber.substring(0, 1)) > 1;

        try {

            List<String[]> data = new ArrayList<String[]>();
            boolean societyNameAdd = true;

            data.add(new String[]{"Society_Name", "Serial_Number", "Label", "Short_Code", "Unique_Flat_Number", "Flat_Numbers"});
//            data.add(new String[]{"serial_number", "label", "short_code", "Blank1", "Blank2", "Flat_Numbers"});

            int totalFlatIndex = 1;
            for (int i = Integer.parseInt(firstFlatNumber.substring(0, 1)); i < floor + 1; i++) {
                for (int j = 1; j < flatsOnEachfloor + 1; j++) {

                    String room = "";
                    if (digit == 2) {
                        if (skipGround) {
                            room = (i * 10 + j) + "";
                        } else {
                            room = (i == 1) ? (j >= 10 ? "" + j : "0" + j) : ((i - 1) * 10 + j) + "";
                        }
                    } else if (digit == 3) {
                        if (skipGround) {
                            room = (i * 100 + j) + "";
                        } else {
                            room = (i == 1) ? (j >= 10 ? "0" + j : "00" + j) : ((i - 1) * 100 + j) + "";
                        }
                    } else if (digit == 4) {
                        if (skipGround) {
                            room = (i * 100 + j) + "";

                            // This is for if number length 3 digit then append 0 at begging
                            int roomLength = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(room)))) + 1;
                            if (roomLength == 3) {
                                room = "0" + room;
                            }
                        } else {
//                            room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) * 1000 + j) + "";
                            room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) >= 10) ? (((i - 1) * 100 + j) + "") : ((i - 1) * 1000 + j) + "";
                        }
                    }

                    String serial_number = skipGround ? String.valueOf(totalFlatIndex) : "" + ((i - 1) * flatsOnEachfloor + j);
//                            String label = ti1.getText().toString() + "-" + room;
                    String label = nameOfTower + "-" + Integer.parseInt(room);
                    String short_code = Integer.parseInt(room.substring(0, 2)) > 9 ? room : "0-" + Integer.parseInt(room);
                    String Flat_Numbers = room;
                    String Tower = nameOfTower;
//                            String short_code = ti2.getText().toString() + room;

                    uniqueRoomList.add(Integer.valueOf(room));

//                    data.add(new String[]{serial_number, label, short_code, "", "", Flat_Numbers});
                    data.add(new String[]{(societyNameAdd ? nameOfTower : ""), serial_number, label, short_code, "0", Flat_Numbers});
                    societyNameAdd = false;
                    totalFlatIndex++;
                }
            }


//          new String[]{"Society_Name 0", "Serial_Number 1", "Label 2", "Short_Code 3", "Unique_Flat_Number 4", "Flat_Numbers 5"}
            List<SingleTowerVO> finalList = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                finalList.add(new SingleTowerVO(data.get(i)[1], data.get(i)[0], data.get(i)[5], data.get(i)[2], data.get(i)[3], data.get(i)[4]));
            }


            int counter = 1;
            TreeSet<Integer> temp = new TreeSet<>(uniqueRoomList);
            for (Integer key : temp) {
                SingleTowerVO singleTowerVO = finalList.get(counter);
                singleTowerVO.setUniqueFlatNumber(String.valueOf(key));
                finalList.set(counter, singleTowerVO);
                counter++;
            }


            StringBuilder data1 = new StringBuilder();
            for (int i = 0; i < finalList.size(); i++) {
                SingleTowerVO multiTowerVO = finalList.get(i);
                data1.append("\n" + multiTowerVO.getSerialNumber() + "," + multiTowerVO.getSocietyName() + "," + multiTowerVO.getLabel() + "," + multiTowerVO.getShortCode() + "," + (i == 0 ? multiTowerVO.getUniqueFlatNumber() : Integer.parseInt(multiTowerVO.getUniqueFlatNumber()) == 0 ? "" : multiTowerVO.getUniqueFlatNumber()));
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
                                                Intent intent = new Intent(NewSingleTowerActivity.this, HomeActivity.class);
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

    public void shareFileToUser(File file) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),
                BuildConfig.APPLICATION_ID + ".provider", file));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}