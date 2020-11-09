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

import com.example.jiofiberapp.model.ExtraVO;
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

import easyfilepickerdialog.kingfisher.com.library.model.DialogConfig;
import easyfilepickerdialog.kingfisher.com.library.model.SupportFile;
import easyfilepickerdialog.kingfisher.com.library.view.FilePickerDialogFragment;

public class SocietyCommonPointActivity2 extends AppCompatActivity {


    MaterialButton submitButton;
    boolean isSingleTower;
    ExtraVO extraVO;
    private List<Integer> householdServicesList = new ArrayList<>();
    private List<Integer> householdItemsList = new ArrayList<>();
    private List<Integer> foodOutletsList = new ArrayList<>();
    private List<Integer> kiranaItemsShopsList = new ArrayList<>();
    private List<Integer> otherHousekeepingList = new ArrayList<>();
    private List<Integer> poolSportsOthersList = new ArrayList<>();
    private List<Integer> otherOfficeList = new ArrayList<>();
    private ArrayList<TowerVO> mainList = new ArrayList<>();

    // 1
    private TextInputEditText otherRepresentativeTextInputEditText;
    private TextInputEditText otherOfficeTextInputEditText;
    private TextInputEditText societyRepairServicesTextInputEditText;
    private TextInputEditText poolSportsOthersTextInputEditText;
    private TextInputEditText societyResidentsOwnVenturesTextInputEditText;
    //2
    private TextInputEditText towerLiftSecurityTextInputEditText;
    private TextInputEditText otherGatesTextInputEditText;
    private TextInputEditText parkingTextInputEditText;
    private TextInputEditText maidsCooksTextInputEditText;
    private TextInputEditText carBikeWashTextInputEditText;
    private TextInputEditText otherHousekeepingTextInputEditText;
    //3
    private TextInputEditText kiranaItemsShopsTextInputEditText;
    private TextInputEditText foodOutletsTextInputEditText;
    private TextInputEditText householdItemsTextInputEditText;
    //4
    private TextInputEditText personalServicesTextInputEditText;
    private TextInputEditText householdServicesTextInputEditText;
    private TextInputEditText repairSupportServicesTextInputEditText;

    private void moveToNext() {

        extraVO.setOtherRepresentative(Integer.parseInt(otherRepresentativeTextInputEditText.getText().toString()));
        extraVO.setOtherOffice(Integer.parseInt(otherOfficeTextInputEditText.getText().toString()));
        extraVO.setSocietyRepairServices(Integer.parseInt(societyRepairServicesTextInputEditText.getText().toString()));
        extraVO.setPoolSportsOthers(Integer.parseInt(poolSportsOthersTextInputEditText.getText().toString()));
        extraVO.setSocietyResidentsOwnVentures(Integer.parseInt(societyResidentsOwnVenturesTextInputEditText.getText().toString()));


        extraVO.setTowerLiftSecurity(Integer.parseInt(towerLiftSecurityTextInputEditText.getText().toString()));
        extraVO.setOtherGates(Integer.parseInt(otherGatesTextInputEditText.getText().toString()));
        extraVO.setParking(Integer.parseInt(parkingTextInputEditText.getText().toString()));
        extraVO.setMaidsCooks(Integer.parseInt(maidsCooksTextInputEditText.getText().toString()));
        extraVO.setCarBikeWash(Integer.parseInt(carBikeWashTextInputEditText.getText().toString()));
        extraVO.setOtherHousekeeping(Integer.parseInt(otherHousekeepingTextInputEditText.getText().toString()));

        extraVO.setKiranaItemsShops(Integer.parseInt(kiranaItemsShopsTextInputEditText.getText().toString()));
        extraVO.setFoodOutlets(Integer.parseInt(foodOutletsTextInputEditText.getText().toString()));
        extraVO.setHouseholdItems(Integer.parseInt(householdItemsTextInputEditText.getText().toString()));

        extraVO.setPersonalServices(Integer.parseInt(personalServicesTextInputEditText.getText().toString()));
        extraVO.setHouseholdServices(Integer.parseInt(householdItemsTextInputEditText.getText().toString()));
        extraVO.setRepairSupportServices(Integer.parseInt(repairSupportServicesTextInputEditText.getText().toString()));

        if (isSingleTower) {
            try {
                forSingleTower();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            forMultiTower();
        }
    }

    private void forMultiTower() {

    }

    private void setDefaultValue() {

        otherRepresentativeTextInputEditText.setText("0");
        otherOfficeTextInputEditText.setText("0");
        societyRepairServicesTextInputEditText.setText("0");
        poolSportsOthersTextInputEditText.setText("0");
        societyResidentsOwnVenturesTextInputEditText.setText("0");

        //2
        towerLiftSecurityTextInputEditText.setText("0");
        otherGatesTextInputEditText.setText("0");
        parkingTextInputEditText.setText("0");
        maidsCooksTextInputEditText.setText("0");
        carBikeWashTextInputEditText.setText("0");
        otherHousekeepingTextInputEditText.setText("0");

        //3
        kiranaItemsShopsTextInputEditText.setText("0");
        foodOutletsTextInputEditText.setText("0");
        householdItemsTextInputEditText.setText("0");

        //4
        personalServicesTextInputEditText.setText("0");
        householdServicesTextInputEditText.setText("0");
        repairSupportServicesTextInputEditText.setText("0");
    }


    private void manageSocietyCoreServiceListener() {

        otherRepresentativeTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                    extraVO.setOtherRepresentative(currentValue);
                } else
                    extraVO.setOtherRepresentative(0);
            }
        });

        final List<Integer> temp1 = extraVO.getOtherOfficeList();
        temp1.add(extraVO.getSocietyOfficeCode());
        otherOfficeList = extraVO.isSocietyOffice() ? extraVO.getOtherOfficeList() : temp1;
        otherOfficeTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = extraVO.isSocietyOffice() ? temp1.size() : otherOfficeList.size();

                    if (currentValue > maxValue) {
                        otherOfficeTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }

                    extraVO.setOtherOffice(currentValue);
                } else
                    extraVO.setOtherOffice(0);
            }
        });

        societyRepairServicesTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    extraVO.setSocietyRepairServices(currentValue);
                } else
                    extraVO.setSocietyRepairServices(0);
            }
        });


        final List<Integer> temp2 = extraVO.getPoolSportsOthersList();
        temp2.add(extraVO.getClubHouseCode());
        poolSportsOthersList = extraVO.isClubHouse() ? extraVO.getPoolSportsOthersList() : temp2;
        poolSportsOthersTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = extraVO.isClubHouse() ? temp2.size() : poolSportsOthersList.size();

                    if (currentValue > maxValue) {
                        poolSportsOthersTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }

                    extraVO.setPoolSportsOthers(currentValue);
                } else
                    extraVO.setPoolSportsOthers(0);

            }
        });

        final List<Integer> list2 = extraVO.getSocietyResidentsOwnVenturesList();
        societyResidentsOwnVenturesTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = list2.size();

                    if (currentValue > maxValue) {
                        societyResidentsOwnVenturesTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }

                    extraVO.setSocietyResidentsOwnVentures(currentValue);
                } else
                    extraVO.setSocietyResidentsOwnVentures(0);
            }
        });
    }

    private void manageSocietyCommonHelpListener() {

        towerLiftSecurityTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                    extraVO.setTowerLiftSecurity(currentValue);
                } else
                    extraVO.setTowerLiftSecurity(0);

            }
        });

        otherGatesTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    extraVO.setOtherGates(currentValue);
                } else
                    extraVO.setOtherGates(0);

            }
        });

        parkingTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    extraVO.setParking(currentValue);
                } else
                    extraVO.setParking(0);

            }
        });

        maidsCooksTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    extraVO.setMaidsCooks(currentValue);
                } else extraVO.setMaidsCooks(0);

            }
        });

        carBikeWashTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    extraVO.setCarBikeWash(currentValue);
                } else extraVO.setCarBikeWash(0);

            }
        });


        final List<Integer> temp1 = extraVO.getOtherHousekeepingList();
        temp1.add(extraVO.getSocietyHousekeepingCode());
        otherHousekeepingList = extraVO.isSocietyHousekeeping() ? extraVO.getOtherHousekeepingList() : temp1;
        otherHousekeepingTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = extraVO.isSocietyHousekeeping() ? temp1.size() : otherHousekeepingList.size();

                    if (currentValue > maxValue) {
                        otherHousekeepingTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }

                    extraVO.setOtherHousekeeping(currentValue);
                } else extraVO.setOtherHousekeeping(0);

            }
        });
    }

    private void manageCommercialShopListener() {


        final List<Integer> temp1 = extraVO.getKiranaItemsShopsList();
        temp1.add(extraVO.getGroceryCode());
        kiranaItemsShopsList = extraVO.isGrocery() ? extraVO.getKiranaItemsShopsList() : temp1;
        kiranaItemsShopsTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                    int maxValue = extraVO.isGrocery() ? temp1.size() : kiranaItemsShopsList.size();

                    if (currentValue > maxValue) {
                        kiranaItemsShopsTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }

                    extraVO.setKiranaItemsShops(currentValue);
                } else extraVO.setKiranaItemsShops(0);

            }
        });

        final List<Integer> temp2 = extraVO.getFoodOutletsList();
        temp2.add(extraVO.getMilkShopCode());
        foodOutletsList = extraVO.isMilkShop() ? extraVO.getFoodOutletsList() : temp2;
        foodOutletsTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = extraVO.isMilkShop() ? temp1.size() : foodOutletsList.size();

                    if (currentValue > maxValue) {
                        foodOutletsTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }

                    extraVO.setFoodOutlets(currentValue);
                } else extraVO.setFoodOutlets(0);

            }
        });


        final List<Integer> temp3 = extraVO.getHouseholdItemsList();
        temp3.add(extraVO.getPharmacyCode());
        householdItemsList = extraVO.isPharmacy() ? extraVO.getHouseholdItemsList() : temp3;
        householdItemsTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = extraVO.isPharmacy() ? temp1.size() : householdItemsList.size();

                    if (currentValue > maxValue) {
                        householdItemsTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }

                    extraVO.setHouseholdItems(currentValue);
                } else extraVO.setHouseholdItems(0);

            }
        });

    }

    private void manageCommercialServicesListener() {

        final List<Integer> list1 = extraVO.getPersonalServicesList();
        personalServicesTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                    int maxValue = list1.size();

                    if (currentValue > maxValue) {
                        personalServicesTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }
                    extraVO.setPersonalServices(currentValue);
                } else extraVO.setPersonalServices(0);

            }
        });


        if (!extraVO.isBarber()) {
            final List<Integer> temp1 = extraVO.getHouseholdServicesList();
            temp1.add(extraVO.getBarberCode());
            householdServicesList = new ArrayList<>();
            householdServicesList.addAll(temp1);
        }

        if (!extraVO.isParlor()) {
            final List<Integer> temp1 = householdServicesList.isEmpty() ? extraVO.getHouseholdServicesList() : householdServicesList;
            temp1.add(extraVO.getParlorCode());
            householdServicesList = new ArrayList<>();
            householdServicesList.addAll(temp1);
        }

        if (!extraVO.isIroningShop()) {
            final List<Integer> temp1 = householdServicesList.isEmpty() ? extraVO.getHouseholdServicesList() : householdServicesList;
            temp1.add(extraVO.getIroningShopCode());
            householdServicesList = new ArrayList<>();
            householdServicesList.addAll(temp1);
        }

        householdServicesTextInputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = householdServicesList.size();

                    if (currentValue > maxValue) {
                        householdServicesTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }

                    extraVO.setHouseholdServices(currentValue);
                } else
                    extraVO.setHouseholdServices(0);

            }
        });


        final List<Integer> list2 = extraVO.getRepairSupportServicesList();
        repairSupportServicesTextInputEditText.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    int currentValue = Integer.parseInt(s.toString());
                    int maxValue = list2.size();

                    if (currentValue > maxValue) {
                        repairSupportServicesTextInputEditText.setError("Maximum limit is " + maxValue);
                        return;
                    }
                    extraVO.setRepairSupportServices(currentValue);
                } else
                    extraVO.setRepairSupportServices(0);

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_common_point2);

        Bundle bundle = getIntent().getExtras();

        extraVO = (ExtraVO) bundle.getSerializable("DATA");

        isSingleTower = bundle.getBoolean("isSingleTower");
        mainList = getIntent().getParcelableArrayListExtra("LIST");

        submitButton = findViewById(R.id.submitButton);
        otherRepresentativeTextInputEditText = findViewById(R.id.otherRepresentativeTextInputEditText);
        otherOfficeTextInputEditText = findViewById(R.id.otherOfficeTextInputEditText);
        societyRepairServicesTextInputEditText = findViewById(R.id.societyRepairServicesTextInputEditText);
        poolSportsOthersTextInputEditText = findViewById(R.id.poolSportsOthersTextInputEditText);
        societyResidentsOwnVenturesTextInputEditText = findViewById(R.id.societyResidentsOwnVenturesTextInputEditText);


        towerLiftSecurityTextInputEditText = findViewById(R.id.towerLiftSecurityTextInputEditText);
        otherGatesTextInputEditText = findViewById(R.id.otherGatesTextInputEditText);
        parkingTextInputEditText = findViewById(R.id.parkingTextInputEditText);
        maidsCooksTextInputEditText = findViewById(R.id.maidsCooksTextInputEditText);
        carBikeWashTextInputEditText = findViewById(R.id.carBikeWashTextInputEditText);
        otherHousekeepingTextInputEditText = findViewById(R.id.otherHousekeepingTextInputEditText);

        kiranaItemsShopsTextInputEditText = findViewById(R.id.kiranaItemsShopsTextInputEditText);
        foodOutletsTextInputEditText = findViewById(R.id.foodOutletsTextInputEditText);
        householdItemsTextInputEditText = findViewById(R.id.householdItemsTextInputEditText);


        personalServicesTextInputEditText = findViewById(R.id.personalServicesTextInputEditText);
        householdServicesTextInputEditText = findViewById(R.id.householdServicesTextInputEditText);
        repairSupportServicesTextInputEditText = findViewById(R.id.repairSupportServicesTextInputEditText);

        setDefaultValue();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToNext();
            }
        });

        manageSocietyCoreServiceListener();
        manageSocietyCommonHelpListener();
        manageCommercialShopListener();
        manageCommercialServicesListener();
    }


    private void forSingleTower() throws IOException {

        mainList.add(new TowerVO("", "", "0", "", ""));
        mainList.add(new TowerVO("", "", "0", "", ""));
        mainList.add(new TowerVO("", "", "0", "", ""));
        mainList.add(new TowerVO("Common Point SN", "", "0", "Labels", "Short-code"));


        // Manage  Form 1
        int count = 1;

        if (extraVO.isSocietyRepresentative())
            mainList.add(new TowerVO(String.valueOf(count), "", "0", "Society Representative", extraVO.getSocietyRepresentativeCode() + ""));

        if (extraVO.isSocietyOffice())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Society Office", extraVO.getSocietyOfficeCode() + ""));

        if (extraVO.isClubHouse())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Club House", extraVO.getClubHouseCode() + ""));

        if (extraVO.isMainGateSecurity())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Main Gate Security", extraVO.getMainGateSecurityCode() + ""));

        if (extraVO.isSocietyHousekeeping())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Society Housekeeping", extraVO.getSocietyHousekeepingCode() + ""));

        if (extraVO.isGardener())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Gardener", extraVO.getGardenerCode() + ""));

        if (extraVO.isGrocery())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Grocery", extraVO.getGroceryCode() + ""));

        if (extraVO.isPharmacy())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Pharmacy", extraVO.getPharmacyCode() + ""));

        if (extraVO.isMilkShop())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Milk Shop", extraVO.isMilkShop() + ""));

        if (extraVO.isParlor())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Parlor", extraVO.getParlorCode() + ""));

        if (extraVO.isIroningShop())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Ironing Shop", extraVO.getIroningShopCode() + ""));

        if (extraVO.isBarber())
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Barber", extraVO.getBarberCode() + ""));


        // Manage  Form 2

        List<Integer> list1 = extraVO.getOtherRepresentativeList();
        for (int i = 0; i < extraVO.getOtherRepresentative(); i++) {
            int code = list1.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Other Representative", code + ""));
        }

        List<Integer> list2 = extraVO.getOtherOfficeList();
        for (int i = 0; i < extraVO.getOtherOffice(); i++) {
            int code = list2.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Other Office", code + ""));
        }

        List<Integer> list3 = extraVO.getSocietyRepairServicesList();
        for (int i = 0; i < extraVO.getSocietyRepairServices(); i++) {
            int code = list3.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Society Repair Service", code + ""));
        }

        List<Integer> list4 = extraVO.getPoolSportsOthersList();
        for (int i = 0; i < extraVO.getPoolSportsOthers(); i++) {
            int code = list4.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Pool Sports Others", code + ""));
        }

        List<Integer> list5 = extraVO.getSocietyResidentsOwnVenturesList();
        for (int i = 0; i < extraVO.getSocietyResidentsOwnVentures(); i++) {
            int code = list5.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Society Residents Own Ventures", code + ""));
        }

        //////////////////////////////////////
        List<Integer> list6 = extraVO.getTowerLiftSecurityList();
        for (int i = 0; i < extraVO.getTowerLiftSecurity(); i++) {
            int code = list6.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Tower Lift Security", code + ""));
        }

        List<Integer> list7 = extraVO.getOtherGatesList();
        for (int i = 0; i < extraVO.getOtherGates(); i++) {
            int code = list7.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Other Gates", code + ""));
        }

        List<Integer> list8 = extraVO.getParkingList();
        for (int i = 0; i < extraVO.getParking(); i++) {
            int code = list8.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Parking", code + ""));
        }

        List<Integer> list9 = extraVO.getMaidsCooksPair();
        for (int i = 0; i < extraVO.getMaidsCooks(); i++) {
            int code = list9.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Maid Cooks", code + ""));
        }

        List<Integer> list10 = extraVO.getCarBikeWashList();
        for (int i = 0; i < extraVO.getCarBikeWash(); i++) {
            int code = list10.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Car Bike Wash", code + ""));
        }


        List<Integer> list11 = extraVO.getOtherHousekeepingList();
        for (int i = 0; i < extraVO.getOtherHousekeeping(); i++) {
            int code = list11.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Housekeeping", code + ""));
        }

        //////////////////////////////////
        List<Integer> list12 = extraVO.getKiranaItemsShopsList();
        for (int i = 0; i < extraVO.getKiranaItemsShops(); i++) {
            int code = list12.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Kirana Items Shops", code + ""));
        }

        List<Integer> list13 = extraVO.getFoodOutletsList();
        for (int i = 0; i < extraVO.getFoodOutlets(); i++) {
            int code = list13.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Food Outlet", code + ""));
        }

        List<Integer> list14 = extraVO.getHouseholdItemsList();
        for (int i = 0; i < extraVO.getHouseholdItems(); i++) {
            int code = list14.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Household Items", code + ""));
        }

        List<Integer> list15 = extraVO.getPersonalServicesList();
        for (int i = 0; i < extraVO.getPersonalServices(); i++) {
            int code = list15.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Personal Services", code + ""));
        }

        List<Integer> list16 = extraVO.getHouseholdServicesList();
        for (int i = 0; i < extraVO.getHouseholdServices(); i++) {
            int code = list16.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Household Services", code + ""));
        }


        List<Integer> list17 = extraVO.getRepairSupportServicesList();
        for (int i = 0; i < extraVO.getRepairSupportServices(); i++) {
            int code = list17.get(i);
            mainList.add(new TowerVO(String.valueOf(count++), "", "0", "Household Services", code + ""));
        }


        StringBuilder data1 = new StringBuilder();
        for (int i = 0; i < mainList.size(); i++) {
            TowerVO singleTowerVO = mainList.get(i);
            data1.append("\n" + singleTowerVO.getSn() + "," + singleTowerVO.getBuildingId() + "," + (i == 0 ? singleTowerVO.getFlats() : Integer.parseInt(singleTowerVO.getFlats()) == 0 ? "" : singleTowerVO.getFlats()) + "," + singleTowerVO.getLabel() + "," + singleTowerVO.getShortCode());
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
                                            Intent intent = new Intent(SocietyCommonPointActivity2.this, HomeActivity.class);
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