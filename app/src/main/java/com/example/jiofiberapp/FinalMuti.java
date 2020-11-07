package com.example.jiofiberapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.jiofiberapp.model.MultiTowerVO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
    String fixFirstFlatNumber;
    int digit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_muti);

        Bundle bundle = getIntent().getExtras();
        nameOfSociety = bundle.getString("nameOfSociety");
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
                boolean societyNameAdd = true;
                try {
                    List<String[]> data = new ArrayList<String[]>();
                    data.add(new String[]{"Society_Name", "Serial_Number", "Label", "Short_Code", "Unique_Flat_Number", "Tower_Name", "Flat_Numbers"});

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

                        int startingFloor = 1;
                        boolean skipGround = false;
                        boolean addTowerName = true;

                        if (isFixFirstFlatNumber) {
                            startingFloor = Integer.parseInt(fixFirstFlatNumber.substring(0, 1));
                            skipGround = Integer.parseInt(fixFirstFlatNumber.substring(0, 1)) >= 1;
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

                            startingFloor = Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1));
                            skipGround = Integer.parseInt(firstFlatInTowerEditText.getText().toString().substring(0, 1)) >= 1;

                        }


//                        for (int i = 1; i < Integer.parseInt(t3.getText().toString()) + 1; i++) {
                        for (int i = startingFloor; i < Integer.parseInt(t3.getText().toString()) + 1; i++) {
                            for (int j = 1; j < Integer.parseInt(t4.getText().toString()) + 1; j++) {

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
//                                        room = (i * 1000 + j) + "";
                                        room = (i * 100 + j) + "";

                                        // This is for if number length 3 digit then append 0 at begging
                                        int roomLength = (int) Math.floor(Math.log10(Math.abs(Integer.parseInt(room)))) + 1;
                                        if (roomLength == 3) {
                                            room = "0" + room;
                                        }

                                    } else {
//                                        room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) * 1000 + j) + "";
                                        room = (i == 1) ? (j >= 10 ? "00" + j : "000" + j) : ((i - 1) >= 10) ? (((i - 1) * 100 + j) + "") : ((i - 1) * 1000 + j) + "";
                                    }
                                }
//                                int room = i * (code == 1000 ? 100 : code) + j;

                                String serial_number = "" + k;
                                String label = t1.getText().toString() + "-" + Integer.parseInt(room);

//                                String short_code = t2.getText().toString() + room;

                                String short_code = getShortCode(num, room, m, range0TO9ShortsCode, range21To99ShortsCode);


                                String Flat_Numbers = room;

                                uniqueRoomList.add(Integer.valueOf(room));

                                data.add(new String[]{(societyNameAdd ? nameOfSociety : ""), serial_number, label, short_code, "0", (addTowerName ? towerName : ""), Flat_Numbers});
                                addTowerName = false;
                                societyNameAdd = false;
                                k++;
                            }
                        }
                    }


//                  new String[]{"Society_Name 0", "Serial_Number 1", "Label 2", "Short_Code 3", "Unique_Flat_Number 4", "Tower_Name 5", "Flat_Numbers 6"}
                    List<MultiTowerVO> finalList = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        finalList.add(new MultiTowerVO(data.get(i)[1], data.get(i)[0], data.get(i)[5], data.get(i)[6], data.get(i)[2], data.get(i)[3], data.get(i)[4]));
                    }

                    int counter = 1;
                    TreeSet<Integer> temp = new TreeSet<>(uniqueRoomList);
                    for (Integer key : temp) {
                        MultiTowerVO multiTowerVO = finalList.get(counter);
                        multiTowerVO.setUniqueFlatNumber(String.valueOf(key));
                        finalList.set(counter, multiTowerVO);
                        counter++;
                    }


                    List<String> towerNameList = new ArrayList<>();
                    for (int i = 1; i < finalList.size(); i++) {
                        MultiTowerVO multiTowerVO = finalList.get(i);
                        if (multiTowerVO.getTowerName().length() > 0) {
                            towerNameList.add(multiTowerVO.getTowerName());
                            multiTowerVO.setTowerName("");
                            finalList.set(i, multiTowerVO);
                        }
                    }

                    for (int i = 0; i < towerNameList.size(); i++) {
                        MultiTowerVO multiTowerVO = finalList.get(i+1);
                        multiTowerVO.setTowerName(towerNameList.get(i));
                        finalList.set(i + 1, multiTowerVO);
                    }


                    StringBuilder data1 = new StringBuilder();
                    for (int i = 0; i < finalList.size(); i++) {
                        MultiTowerVO multiTowerVO = finalList.get(i);
                        data1.append("\n" + multiTowerVO.getSerialNumber() + "," + multiTowerVO.getSocietyName() + "," + multiTowerVO.getTowerName() + "," + multiTowerVO.getLabel() + "," + multiTowerVO.getShortCode() + "," + (i == 0 ? multiTowerVO.getUniqueFlatNumber() : Integer.parseInt(multiTowerVO.getUniqueFlatNumber()) == 0 ? "" : multiTowerVO.getUniqueFlatNumber()));
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

    private String getShortCode(int num, String room, int index, List<String> Range0TO9ShortsCode, List<String> Range21TO99ShortsCode) {
        if (num > 9) {
            return Range21TO99ShortsCode.get(index) + room;
        } else {
            if (Range0TO9ShortsCode.get(index).equals("0")) {
                return "0-" + room;
            } else {
                return Range0TO9ShortsCode.get(index) +  room;
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

    private void shareFileToUser(File file) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),
                BuildConfig.APPLICATION_ID + ".provider", file));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}