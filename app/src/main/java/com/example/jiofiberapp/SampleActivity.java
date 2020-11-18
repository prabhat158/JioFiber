package com.example.jiofiberapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.jiofiberapp.model.RangeVO;
import com.example.jiofiberapp.model.SaveVO;
import com.example.jiofiberapp.model.TowerVO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import easyfilepickerdialog.kingfisher.com.library.model.DialogConfig;
import easyfilepickerdialog.kingfisher.com.library.model.SupportFile;
import easyfilepickerdialog.kingfisher.com.library.view.FilePickerDialogFragment;

public class SampleActivity extends AppCompatActivity {

    SaveVO saveVO;
    RangeVO rangeVO;
    boolean isSingleTower;
    int digit;
    private TextInputEditText gateTextInputEditText;
    private TextInputEditText societyOfficeTextInputEditText;
    private TextInputEditText towerLiftLobbyTextInputEditText;
    private TextInputEditText clubHouseAndMoreCommonPointTextInputEditText;
    //    private TextInputEditText societyManagerAndCommitteeMembersTextInputEditText;
//    private TextInputEditText parkingTextInputEditText;
//    private TextInputEditText societyHousekeepingAndStaffTextInputEditText;
//    private TextInputEditText shopsTextInputEditText;
//    private TextInputEditText servicesTextInputEditText;
    private MaterialButton submitButton;
    private ArrayList<TowerVO> mainList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point1);

        Bundle bundle = getIntent().getExtras();

        isSingleTower = bundle.getBoolean("isSingleTower");
        digit = bundle.getInt("digit");
        mainList = getIntent().getParcelableArrayListExtra("LIST");


        rangeVO = new RangeVO();
        saveVO = new SaveVO();
        uiManage();
        actionManage();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToNext();
            }
        });
    }

    private void actionManage() {

        gateTextInputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
//                    int maxValue = rangeVO.getGateRange().size();

//                    if (currentValue > maxValue) {
//                        gateTextInputEditText.setError("Maximum limit is " + maxValue);
//                        return;
//                    }
                    saveVO.setGate(currentValue);
                } else
                    saveVO.setGate(0);
            }
        });

        societyOfficeTextInputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
//                    int maxValue = rangeVO.getSocietyOfficesRange().size();

//                    if (currentValue > maxValue) {
//                        societyOfficeTextInputEditText.setError("Maximum limit is " + maxValue);
//                        return;
//                    }
                    saveVO.setSocietyOffices(currentValue);
                } else
                    saveVO.setSocietyOffices(0);
            }
        });

        towerLiftLobbyTextInputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = rangeVO.getTowerLobbiesRange().size();

                    if (currentValue > maxValue) {
                        towerLiftLobbyTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }
                    saveVO.setSocietyOffices(currentValue);
                } else
                    saveVO.setSocietyOffices(0);
            }
        });

        clubHouseAndMoreCommonPointTextInputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = rangeVO.getClubHouseCommonHallAndMoreCommonPointsRange().size();

                    if (currentValue > maxValue) {
                        clubHouseAndMoreCommonPointTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }
                    saveVO.setClubHouseAndMoreCommonPoint(currentValue);
                } else
                    saveVO.setClubHouseAndMoreCommonPoint(0);
            }
        });

//        societyManagerAndCommitteeMembersTextInputEditText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() != 0) {
//                    int currentValue = Integer.parseInt(s.toString());
//                    int maxValue = rangeVO.getSocietyManagerAndCommitteeMembersRange().size();
//
//                    if (currentValue > maxValue) {
//                        societyManagerAndCommitteeMembersTextInputEditText.setError("Maximum limit is " + maxValue);
//                        return;
//                    }
//                    saveVO.setSocietyManagerAndCommitteeMembers(currentValue);
//                } else
//                    saveVO.setSocietyManagerAndCommitteeMembers(0);
//            }
//        });

//        parkingTextInputEditText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() != 0) {
//                    int currentValue = Integer.parseInt(s.toString());
//                    saveVO.setParking(currentValue);
//                } else
//                    saveVO.setParking(0);
//            }
//        });

//        societyHousekeepingAndStaffTextInputEditText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() != 0) {
//                    int currentValue = Integer.parseInt(s.toString());
//                    saveVO.setSocietyHousekeepingAndStaff(currentValue);
//                } else
//                    saveVO.setSocietyHousekeepingAndStaff(0);
//            }
//        });


//        shopsTextInputEditText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() != 0) {
//                    int currentValue = Integer.parseInt(s.toString());
//                    saveVO.setShops(currentValue);
//                } else
//                    saveVO.setShops(0);
//            }
//        });

//        servicesTextInputEditText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() != 0) {
//                    int currentValue = Integer.parseInt(s.toString());
//                    saveVO.setServices(currentValue);
//                } else
//                    saveVO.setServices(0);
//            }
//        });

    }

    private void uiManage() {

        gateTextInputEditText = findViewById(R.id.gateTextInputEditText);
        societyOfficeTextInputEditText = findViewById(R.id.societyOfficeTextInputEditText);
        towerLiftLobbyTextInputEditText = findViewById(R.id.towerLiftLobbyTextInputEditText);
        clubHouseAndMoreCommonPointTextInputEditText = findViewById(R.id.clubHouseAndMoreCommonPointTextInputEditText);
//        societyManagerAndCommitteeMembersTextInputEditText = findViewById(R.id.societyManagerAndCommitteeMembersTextInputEditText);
//        parkingTextInputEditText = findViewById(R.id.parkingTextInputEditText);
//        societyHousekeepingAndStaffTextInputEditText = findViewById(R.id.societyHousekeepingAndStaffTextInputEditText);
//        shopsTextInputEditText = findViewById(R.id.shopsTextInputEditText);
//        servicesTextInputEditText = findViewById(R.id.servicesTextInputEditText);

        submitButton = findViewById(R.id.submitButton);
    }


    private void moveToNext() {

        saveVO.setGate(Integer.parseInt(Objects.requireNonNull(gateTextInputEditText.getText()).toString().trim().equals("") ? "0" : gateTextInputEditText.getText().toString()));
        saveVO.setSocietyOffices(Integer.parseInt(Objects.requireNonNull(societyOfficeTextInputEditText.getText()).toString().trim().equals("") ? "0" : societyOfficeTextInputEditText.getText().toString()));
        saveVO.setTowerLiftLobby(Integer.parseInt(Objects.requireNonNull(towerLiftLobbyTextInputEditText.getText()).toString().trim().equals("") ? "0" : towerLiftLobbyTextInputEditText.getText().toString()));
        saveVO.setClubHouseAndMoreCommonPoint(Integer.parseInt(Objects.requireNonNull(clubHouseAndMoreCommonPointTextInputEditText.getText()).toString().trim().equals("") ? "0" : clubHouseAndMoreCommonPointTextInputEditText.getText().toString()));
//        saveVO.setSocietyManagerAndCommitteeMembers(Integer.parseInt(Objects.requireNonNull(societyManagerAndCommitteeMembersTextInputEditText.getText()).toString().trim().equals("") ? "0" : societyManagerAndCommitteeMembersTextInputEditText.getText().toString()));
//        saveVO.setParking(Integer.parseInt(Objects.requireNonNull(parkingTextInputEditText.getText()).toString().trim().equals("") ? "0" : parkingTextInputEditText.getText().toString()));
//        saveVO.setSocietyHousekeepingAndStaff(Integer.parseInt(Objects.requireNonNull(societyHousekeepingAndStaffTextInputEditText.getText()).toString().trim().equals("") ? "0" : societyHousekeepingAndStaffTextInputEditText.getText().toString()));
//        saveVO.setShops(Integer.parseInt(Objects.requireNonNull(shopsTextInputEditText.getText()).toString().trim().equals("") ? "0" : shopsTextInputEditText.getText().toString()));
//        saveVO.setServices(Integer.parseInt(Objects.requireNonNull(servicesTextInputEditText.getText()).toString().trim().equals("") ? "0" : servicesTextInputEditText.getText().toString()));

        if (isSingleTower) {
            try {
                forSingleTower();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                forMultiTower();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void forSingleTower() throws IOException {

        mainList.add(new TowerVO("", "", "0", "", ""));
        mainList.add(new TowerVO("", "", "0", "", ""));
        mainList.add(new TowerVO("", "", "0", "", ""));
        mainList.add(new TowerVO("Common Point SN", "", "0", "Labels", "Short-code"));

        int count = 1;


        // Gate
        List<Integer> list1 = rangeVO.getGateAndTowerRange();
        int gateTowerIndex = 0;
        for (int i = 0; i < list1.size(); i++) {

            if (i == saveVO.getGate())
                break;

            int code = list1.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", (i == 0 ? "Main Gate" : "Gate " + (i + 1)), (code == 0 ? "" : code + "")));
            gateTowerIndex++;
        }

        // Tower/lift/lobby
        for (int i = 0; i < list1.size(); i++) {

            if (i == saveVO.getTowerLiftLobby())
                break;

            int code = list1.get(gateTowerIndex);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Lobby " + (i + 1), (code == 0 ? "" : code + "")));
            gateTowerIndex++;
        }


        // Society Office
        int officeAndCommonPoint = 0;
        List<Integer> list2 = rangeVO.getOfficeAndCommonPointRange();
        for (int i = 0; i < list2.size(); i++) {

            if (i == saveVO.getSocietyOffices())
                break;

            int code = list2.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Society Office " + (i + 1), (code == 0 ? "" : code + "")));
            officeAndCommonPoint++;
        }

        // Club house/common points
        List<Integer> list4 = rangeVO.getOfficeAndCommonPointRange();
        for (int i = 0; i < list4.size(); i++) {

            if (i == saveVO.getClubHouseAndMoreCommonPoint())
                break;

            int code = list4.get(officeAndCommonPoint);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Common Point " + (i + 1), (code == 0 ? "" : code + "")));
            officeAndCommonPoint++;
        }

        // Society manager and committee member
//        List<Integer> list5 = rangeVO.getSocietyManagerAndCommitteeMembersRange();
//        for (int i = 0; i < list5.size(); i++) {
//
//            if (i == saveVO.getSocietyManagerAndCommitteeMembers())
//                break;
//
//            int code = list5.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "0", (i == 0 ? "Society Manager" : "Committee member " + (i + 1)), (code == 0 ? "" : code + "")));
//        }

        //Parking
//        List<Integer> list6 = rangeVO.getParkingRange();
//        for (int i = 0; i < list6.size(); i++) {
//
//            if (i == saveVO.getParking())
//                break;
//
//            int code = list6.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "0", (i == 0 ? "Parking" : "Parking " + (i + 1)), (code == 0 ? "" : code + "")));
//        }

        //Society housekeeping and staff
//        List<Integer> list7 = rangeVO.getHouseKeepingRange();
//        for (int i = 0; i < list7.size(); i++) {
//
//            if (i == saveVO.getSocietyHousekeepingAndStaff())
//                break;
//
//            int code = list7.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Staff " + (i + 1), (code == 0 ? "" : code + "")));
//        }

        // Shops
//        List<Integer> list8 = rangeVO.getCommercialShopRange();
//        for (int i = 0; i < list8.size(); i++) {
//
//            if (i == saveVO.getShops())
//                break;
//
//            int code = list8.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Shop " + (i + 1), (code == 0 ? "" : code + "")));
//        }

        // Services
//        List<Integer> list9 = rangeVO.getCommercialServicesRange();
//        for (int i = 0; i < list9.size(); i++) {
//
//            if (i == saveVO.getServices())
//                break;
//
//            int code = list9.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Service " + (i + 1), (code == 0 ? "" : code + "")));
//        }


        StringBuilder data1 = new StringBuilder();
        for (int i = 0; i < mainList.size(); i++) {
            TowerVO singleTowerVO = mainList.get(i);
            data1.append("\n" + singleTowerVO.getSn() + ","
                    + singleTowerVO.getBuildingId() + ","
                    + ((digit == 2) ? (i == 0 ? singleTowerVO.getFlats() : (Integer.parseInt(singleTowerVO.getFlats()) == 0 ? "" : singleTowerVO.getFlats().substring(1))) : (i == 0 ? singleTowerVO.getFlats() : Integer.parseInt(singleTowerVO.getFlats()) == 0 ? "" : singleTowerVO.getFlats()))
                    + ","
                    + singleTowerVO.getLabel() + ","
                    + singleTowerVO.getShortCode());
        }


        final String baseFolder;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            baseFolder = getExternalFilesDir(null).getAbsolutePath();
        } else {
            baseFolder = getFilesDir().getAbsolutePath();
        }

//        Date currentTime = Calendar.getInstance().getTime();
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
                        })
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
                                            Intent intent = new Intent(SampleActivity.this, HomeActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }
                            }
                , 10);
    }

    private void forMultiTower() throws IOException {

        mainList.add(new TowerVO("", "", "", "0", "", ""));
        mainList.add(new TowerVO("", "", "", "0", "", ""));
        mainList.add(new TowerVO("", "", "", "0", "", ""));
        mainList.add(new TowerVO("Common Point SN", "", "", "0", "Labels", "Short-code"));


        int count = 1;

        // Gate
        List<Integer> list1 = rangeVO.getGateAndTowerRange();
        int gateTowerIndex = 0;
        for (int i = 0; i < list1.size(); i++) {

            if (i == saveVO.getGate())
                break;

            int code = list1.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", (i == 0 ? "Main Gate" : "Gate " + (i + 1)), (code == 0 ? "" : code + "")));
            gateTowerIndex++;
        }

        // Tower/lift/lobby
        for (int i = 0; i < list1.size(); i++) {

            if (i == saveVO.getTowerLiftLobby())
                break;

            int code = list1.get(gateTowerIndex);
            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Lobby " + (i + 1), (code == 0 ? "" : code + "")));
            gateTowerIndex++;
        }


        // Society Office
        int officeAndCommonPoint = 0;
        List<Integer> list2 = rangeVO.getOfficeAndCommonPointRange();
        for (int i = 0; i < list2.size(); i++) {

            if (i == saveVO.getSocietyOffices())
                break;

            int code = list2.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Society Office " + (i + 1), (code == 0 ? "" : code + "")));
            officeAndCommonPoint++;
        }

        // Club house/common points
        List<Integer> list4 = rangeVO.getOfficeAndCommonPointRange();
        for (int i = 0; i < list4.size(); i++) {

            if (i == saveVO.getClubHouseAndMoreCommonPoint())
                break;

            int code = list4.get(officeAndCommonPoint);
            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Common Point " + (i + 1), (code == 0 ? "" : code + "")));
            officeAndCommonPoint++;
        }

        // Gate
//        List<Integer> list1 = rangeVO.getGateRange();
//        for (int i = 0; i < list1.size(); i++) {
//
//            if (i == saveVO.getGate())
//                break;
//
//            int code = list1.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", (i == 0 ? "Main Gate" : "Gate " + (i + 1)), (code == 0 ? "" : code + "")));
//        }
//
//        // Society Office
//        List<Integer> list2 = rangeVO.getSocietyOfficesRange();
//        for (int i = 0; i < list2.size(); i++) {
//
//            if (i == saveVO.getSocietyOffices())
//                break;
//
//            int code = list2.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Society Office " + (i + 1), (code == 0 ? "" : code + "")));
//        }
//
//        // Tower/lift/lobby
//        List<Integer> list3 = rangeVO.getTowerLobbiesRange();
//        for (int i = 0; i < list3.size(); i++) {
//
//            if (i == saveVO.getTowerLiftLobby())
//                break;
//
//            int code = list3.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Lobby " + (i + 1), (code == 0 ? "" : code + "")));
//        }
//
//        // Club house/common points
//        List<Integer> list4 = rangeVO.getClubHouseCommonHallAndMoreCommonPointsRange();
//        for (int i = 0; i < list4.size(); i++) {
//
//            if (i == saveVO.getClubHouseAndMoreCommonPoint())
//                break;
//
//            int code = list4.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Common Point " + (i + 1), (code == 0 ? "" : code + "")));
//        }

        // Society manager and committee member
//        List<Integer> list5 = rangeVO.getSocietyManagerAndCommitteeMembersRange();
//        for (int i = 0; i < list5.size(); i++) {
//
//            if (i == saveVO.getSocietyManagerAndCommitteeMembers())
//                break;
//
//            int code = list5.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", (i == 0 ? "Society Manager" : "Committee member " + (i + 1)), (code == 0 ? "" : code + "")));
//        }

        //Parking
//        List<Integer> list6 = rangeVO.getParkingRange();
//        for (int i = 0; i < list6.size(); i++) {
//
//            if (i == saveVO.getParking())
//                break;
//
//            int code = list6.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", (i == 0 ? "Parking" : "Parking " + (i + 1)), (code == 0 ? "" : code + "")));
//        }

        //Society housekeeping and staff
//        List<Integer> list7 = rangeVO.getHouseKeepingRange();
//        for (int i = 0; i < list7.size(); i++) {
//
//            if (i == saveVO.getSocietyHousekeepingAndStaff())
//                break;
//
//            int code = list7.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Staff " + (i + 1), (code == 0 ? "" : code + "")));
//        }

        // Shops
//        List<Integer> list8 = rangeVO.getCommercialShopRange();
//        for (int i = 0; i < list8.size(); i++) {
//
//            if (i == saveVO.getShops())
//                break;
//
//            int code = list8.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Shop " + (i + 1), (code == 0 ? "" : code + "")));
//        }

        // Services
//        List<Integer> list9 = rangeVO.getCommercialServicesRange();
//        for (int i = 0; i < list9.size(); i++) {
//
//            if (i == saveVO.getServices())
//                break;
//
//            int code = list9.get(i);
//            mainList.add(new TowerVO(String.valueOf(count++), "", "", "0", "Service " + (i + 1), (code == 0 ? "" : code + "")));
//        }


//      data.add(new String[]{"SN 0", "Building id 1", "Towers 2", "Flats 3", "Labels 4", "Short-code 5"});
        StringBuilder data1 = new StringBuilder();
        for (int i = 0; i < mainList.size(); i++) {
            TowerVO towerVO = mainList.get(i);
            data1.append("\n" + towerVO.getSn() + ","
                    + towerVO.getBuildingId() + ","
                    + towerVO.getTowers() + ","
                    + ((digit == 2) ? (i == 0 ? towerVO.getFlats() : (Integer.parseInt(towerVO.getFlats()) == 0 ? "" : towerVO.getFlats().substring(1))) : (i == 0 ? towerVO.getFlats() : Integer.parseInt(towerVO.getFlats()) == 0 ? "" : towerVO.getFlats()))

//                    + (i == 0 ? towerVO.getFlats() : Integer.parseInt(towerVO.getFlats()) == 0 ? "" : towerVO.getFlats())
                    + ","
                    + towerVO.getLabel() + ","
                    + towerVO.getShortCode());
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
                        })

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
                                            Intent intent = new Intent(SampleActivity.this, HomeActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }
                            }
                , 10);
    }


    public void shareFileToUser(File file) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),
                BuildConfig.APPLICATION_ID + ".provider", file));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}