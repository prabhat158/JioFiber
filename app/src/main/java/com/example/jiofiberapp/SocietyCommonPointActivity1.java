package com.example.jiofiberapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jiofiberapp.model.CommonVO;
import com.example.jiofiberapp.model.ExtraVO;
import com.example.jiofiberapp.model.TowerVO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SocietyCommonPointActivity1 extends AppCompatActivity {

    MaterialButton nextButton;
    boolean isSingleTower;
    private CheckBox selectAllCheckBox;
    List<View> towerLiftLobbySecurityView = new ArrayList<>();
    List<View> moreCommonPointView = new ArrayList<>();
    private ArrayList<TowerVO> mainList = new ArrayList<>();
    private CheckBox societyOfficeCheckBox;
    private CheckBox clubHouseCheckBox;
    private CheckBox mainGateSecurityCheckBox;
    private CheckBox societyHousekeepingCheckBox;
    private CheckBox societyManagerCheckBox;
    private CheckBox paringCheckBox;
    private CheckBox morePointInSocietyCheckBox;
    private LinearLayout moreQuestionLinearLayout;
    private TextInputEditText moreCommonPointTextInputEditText;
    private TextInputEditText numberOfGatesTextInputEditText;
    private TextInputEditText towerLiftLobbySecurityTextInputEditText;
    private LinearLayout towerLiftLobbySecurityLinearLayout;
    private TextInputEditText otherOfficesInSocietyTextInputEditText;
    private TextInputEditText committeeMemberTextInputEditText;
    private TextInputEditText otherSocietyStaffTextInputEditText;

    private TextInputEditText societyResidentsOwnVenturesTextInputEditText;
    private TextInputEditText moreParkingAreasTextInputEditText;
    private LinearLayout moreCommonPointLinearLayout;


    private void moveToNext() {

        ExtraVO extraVO = new ExtraVO();

        extraVO.setMainGateSecurity(mainGateSecurityCheckBox.isChecked());
        extraVO.setSocietyOffice(societyOfficeCheckBox.isChecked());
        extraVO.setSocietyManager(societyManagerCheckBox.isChecked());
        extraVO.setSocietyHousekeeping(societyHousekeepingCheckBox.isChecked());
        extraVO.setClubHouse(clubHouseCheckBox.isChecked());
        extraVO.setParking(paringCheckBox.isChecked());
        extraVO.setMorePointInSociety(morePointInSocietyCheckBox.isChecked());

        extraVO.setNumberOfGates(Integer.parseInt(Objects.requireNonNull(numberOfGatesTextInputEditText.getText()).toString().trim().equals("") ? "0" : numberOfGatesTextInputEditText.getText().toString()));
        extraVO.setOtherOfficesInSociety(Integer.parseInt(Objects.requireNonNull(otherOfficesInSocietyTextInputEditText.getText()).toString().trim().equals("") ? "0" : otherOfficesInSocietyTextInputEditText.getText().toString()));
        extraVO.setTowerLiftLobbySecurity(Integer.parseInt(Objects.requireNonNull(towerLiftLobbySecurityTextInputEditText.getText()).toString().trim().equals("") ? "0" : towerLiftLobbySecurityTextInputEditText.getText().toString()));
        extraVO.setCommitteeMembers(Integer.parseInt(Objects.requireNonNull(committeeMemberTextInputEditText.getText()).toString().trim().equals("") ? "0" : committeeMemberTextInputEditText.getText().toString()));
        extraVO.setMoreCommonPoints(Integer.parseInt(Objects.requireNonNull(moreCommonPointTextInputEditText.getText()).toString().trim().equals("") ? "0" : moreCommonPointTextInputEditText.getText().toString()));
        extraVO.setOtherSocietyStaff(Integer.parseInt(Objects.requireNonNull(otherSocietyStaffTextInputEditText.getText()).toString().trim().equals("") ? "0" : otherSocietyStaffTextInputEditText.getText().toString()));
        extraVO.setMoreParkingAreas(Integer.parseInt(Objects.requireNonNull(moreParkingAreasTextInputEditText.getText()).toString().trim().equals("") ? "0" : moreParkingAreasTextInputEditText.getText().toString()));
        extraVO.setResidentOwnVentures(Integer.parseInt(Objects.requireNonNull(societyResidentsOwnVenturesTextInputEditText.getText()).toString().trim().equals("") ? "0" : societyResidentsOwnVenturesTextInputEditText.getText().toString()));


        //Tower Lift Lobby Security
        List<CommonVO> towerLiftLobbySecurityList = new ArrayList<>();
        for (int m = 0; m < towerLiftLobbySecurityView.size(); m++) {
            View view1 = towerLiftLobbySecurityView.get(m);

            TextView code = view1.findViewById(R.id.codeTextView);
            TextInputEditText name = view1.findViewById(R.id.nameTextInputEditText);

            if (Objects.requireNonNull(name.getText()).toString().trim().equals("")) {
                name.setError("Enter Name");
                return;
            }
            towerLiftLobbySecurityList.add(new CommonVO(Integer.parseInt(code.getText().toString()), name.getText().toString()));
        }
        extraVO.setTowerLiftLobbySecurityData(towerLiftLobbySecurityList);

        // More Common Points
        List<CommonVO> moreCommonPointList = new ArrayList<>();
        for (int m = 0; m < moreCommonPointView.size(); m++) {
            View view1 = moreCommonPointView.get(m);

            TextView code = view1.findViewById(R.id.codeTextView);
            TextInputEditText name = view1.findViewById(R.id.nameTextInputEditText);

            if (Objects.requireNonNull(name.getText()).toString().trim().equals("")) {
                name.setError("Enter Name");
                return;
            }
            moreCommonPointList.add(new CommonVO(Integer.parseInt(code.getText().toString()), name.getText().toString()));
        }
        extraVO.setMoreCommonPointsData(moreCommonPointList);


//        Intent intent = new Intent(this,
//                SocietyCommonPointActivity2.class);
//        intent.putExtra("DATA", extraVO);
//        intent.putParcelableArrayListExtra("LIST", (ArrayList<? extends Parcelable>) mainList);
//        intent.putExtra("isSingleTower", isSingleTower);
//        startActivity(intent);
    }

    private void manageSelectAllCheckBox(boolean isAll) {
        societyManagerCheckBox.setChecked(isAll);
        societyOfficeCheckBox.setChecked(isAll);
        clubHouseCheckBox.setChecked(isAll);
        mainGateSecurityCheckBox.setChecked(true);
        societyHousekeepingCheckBox.setChecked(isAll);
        paringCheckBox.setChecked(isAll);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_society_common_point1);
        Bundle bundle = getIntent().getExtras();

        isSingleTower = bundle.getBoolean("isSingleTower");
        mainList = getIntent().getParcelableArrayListExtra("LIST");


        selectAllCheckBox = findViewById(R.id.selectAllCheckBox);

        societyManagerCheckBox = findViewById(R.id.societyManagerCheckBox);
        societyOfficeCheckBox = findViewById(R.id.societyOfficeCheckBox);
        clubHouseCheckBox = findViewById(R.id.clubHouseCheckBox);
        mainGateSecurityCheckBox = findViewById(R.id.mainGateSecurityCheckBox);
        societyHousekeepingCheckBox = findViewById(R.id.societyHousekeepingCheckBox);
        paringCheckBox = findViewById(R.id.paringCheckBox);
        morePointInSocietyCheckBox = findViewById(R.id.morePointInSocietyCheckBox);
        moreCommonPointTextInputEditText = findViewById(R.id.moreCommonPointTextInputEditText);
        moreQuestionLinearLayout = findViewById(R.id.moreQuestionLinearLayout);
        numberOfGatesTextInputEditText = findViewById(R.id.numberOfGatesTextInputEditText);
        otherOfficesInSocietyTextInputEditText = findViewById(R.id.otherOfficesInSocietyTextInputEditText);
        towerLiftLobbySecurityTextInputEditText = findViewById(R.id.towerLiftLobbySecurityTextInputEditText);
        committeeMemberTextInputEditText = findViewById(R.id.committeeMemberTextInputEditText);
        moreParkingAreasTextInputEditText = findViewById(R.id.moreParkingAreasTextInputEditText);
        societyResidentsOwnVenturesTextInputEditText = findViewById(R.id.societyResidentsOwnVenturesTextInputEditText);
        otherSocietyStaffTextInputEditText = findViewById(R.id.otherSocietyStaffTextInputEditText);
        towerLiftLobbySecurityLinearLayout = findViewById(R.id.towerLiftLobbySecurityLinearLayout);
        moreCommonPointLinearLayout = findViewById(R.id.moreCommonPointLinearLayout);
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

        morePointInSocietyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    moreQuestionLinearLayout.setVisibility(View.GONE);
                } else
                    moreQuestionLinearLayout.setVisibility(View.VISIBLE);
            }
        });

        manageMoreQuestionLogic();
    }

    @SuppressLint("SetTextI18n")
    private void manageMoreQuestionLogic() {

        ExtraVO extraVO = new ExtraVO();

        if (!Objects.requireNonNull(towerLiftLobbySecurityTextInputEditText.getText()).toString().equals("")) {
            int towerLiftLobbySecurity = Integer.parseInt(towerLiftLobbySecurityTextInputEditText.getText().toString());

            towerLiftLobbySecurityView = new ArrayList<View>();
            List<Integer> towerList = extraVO.getTowerLiftLobbySecurityList(); // Need to add or skip data in list on basis of selection
            for (int i = 0; i < towerLiftLobbySecurity; i++) {
                final LayoutInflater factory = getLayoutInflater();
                final View textEntryView = factory.inflate(R.layout.common_view, null);
                TextView textView = textEntryView.findViewById(R.id.codeTextView);
                textView.setText(String.valueOf(towerList.get(i)));
                towerLiftLobbySecurityView.add(textEntryView);
                towerLiftLobbySecurityLinearLayout.addView(textEntryView);
            }
        }


        if (!Objects.requireNonNull(moreParkingAreasTextInputEditText.getText()).toString().equals("")) {
            int moreCommonPoint = Integer.parseInt(moreParkingAreasTextInputEditText.getText().toString());

            moreCommonPointView = new ArrayList<View>();
            List<Integer> moreCommonList = extraVO.getMoreCommonPointList();
            for (int i = 0; i < moreCommonPoint; i++) {
                final LayoutInflater factory = getLayoutInflater();
                final View textEntryView = factory.inflate(R.layout.common_view, null);
                TextView textView = textEntryView.findViewById(R.id.codeTextView);
                textView.setText(String.valueOf(moreCommonList.get(i)));
                moreCommonPointView.add(textEntryView);
                moreCommonPointLinearLayout.addView(textEntryView);
            }
        }
    }
}