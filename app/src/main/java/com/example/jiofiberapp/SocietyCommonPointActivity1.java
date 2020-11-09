package com.example.jiofiberapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jiofiberapp.model.ExtraVO;
import com.example.jiofiberapp.model.TowerVO;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class SocietyCommonPointActivity1 extends AppCompatActivity {

    MaterialButton nextButton;
    boolean isSingleTower;
    private CheckBox selectAllCheckBox;
    private CheckBox societyRepresentativeCheckBox;
    private CheckBox societyOfficeCheckBox;
    private CheckBox clubHouseCheckBox;
    private CheckBox mainGateSecurityCheckBox;
    private CheckBox societyHousekeepingCheckBox;
    private CheckBox gardenerCheckBox;
    private CheckBox groceryCheckBox;
    private CheckBox pharmacyCheckBox;
    private CheckBox milkShopCheckBox;
    private CheckBox parlorCheckBox;
    private CheckBox ironingShopCheckBox;
    private CheckBox barberCheckBox;

    private ArrayList<TowerVO> mainList = new ArrayList<>();

    private void moveToNext() {

        ExtraVO extraVO = new ExtraVO();

        extraVO.setSocietyRepresentative(societyRepresentativeCheckBox.isChecked());
        extraVO.setSocietyOffice(societyOfficeCheckBox.isChecked());
        extraVO.setClubHouse(clubHouseCheckBox.isChecked());

        extraVO.setMainGateSecurity(mainGateSecurityCheckBox.isChecked());
        extraVO.setSocietyHousekeeping(societyHousekeepingCheckBox.isChecked());
        extraVO.setGardener(gardenerCheckBox.isChecked());

        extraVO.setGrocery(groceryCheckBox.isChecked());
        extraVO.setPharmacy(pharmacyCheckBox.isChecked());
        extraVO.setMilkShop(milkShopCheckBox.isChecked());

        extraVO.setParlor(parlorCheckBox.isChecked());
        extraVO.setIroningShop(ironingShopCheckBox.isChecked());
        extraVO.setBarber(barberCheckBox.isChecked());

        Intent intent = new Intent(this, SocietyCommonPointActivity2.class);
        intent.putExtra("DATA", extraVO);
        intent.putParcelableArrayListExtra("LIST", (ArrayList<? extends Parcelable>) mainList);
        intent.putExtra("isSingleTower", isSingleTower);
        startActivity(intent);
    }

    private void manageSelectAllCheckBox(boolean isAll) {

        societyRepresentativeCheckBox.setChecked(true);
        mainGateSecurityCheckBox.setChecked(true);

        societyOfficeCheckBox.setChecked(isAll);
        clubHouseCheckBox.setChecked(isAll);

        societyHousekeepingCheckBox.setChecked(isAll);
        gardenerCheckBox.setChecked(isAll);

        groceryCheckBox.setChecked(isAll);
        pharmacyCheckBox.setChecked(isAll);
        milkShopCheckBox.setChecked(isAll);


        parlorCheckBox.setChecked(isAll);
        ironingShopCheckBox.setChecked(isAll);
        barberCheckBox.setChecked(isAll);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_common_point1);
        Bundle bundle = getIntent().getExtras();

        isSingleTower = bundle.getBoolean("isSingleTower");
        mainList = getIntent().getParcelableArrayListExtra("LIST");


        selectAllCheckBox = findViewById(R.id.selectAllCheckBox);
        societyRepresentativeCheckBox = findViewById(R.id.societyRepresentativeCheckBox);
        societyOfficeCheckBox = findViewById(R.id.societyOfficeCheckBox);
        clubHouseCheckBox = findViewById(R.id.clubHouseCheckBox);
        mainGateSecurityCheckBox = findViewById(R.id.mainGateSecurityCheckBox);
        societyHousekeepingCheckBox = findViewById(R.id.societyHousekeepingCheckBox);
        gardenerCheckBox = findViewById(R.id.gardenerCheckBox);
        groceryCheckBox = findViewById(R.id.groceryCheckBox);
        pharmacyCheckBox = findViewById(R.id.pharmacyCheckBox);
        milkShopCheckBox = findViewById(R.id.milkShopCheckBox);
        parlorCheckBox = findViewById(R.id.parlorCheckBox);
        ironingShopCheckBox = findViewById(R.id.ironingShopCheckBox);
        barberCheckBox = findViewById(R.id.barberCheckBox);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToNext();
            }
        });

        selectAllCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                manageSelectAllCheckBox(b);
            }
        });

        selectAllCheckBox.setChecked(true);
    }
}