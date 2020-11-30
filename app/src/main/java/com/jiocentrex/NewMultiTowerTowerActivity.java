package com.jiocentrex;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class NewMultiTowerTowerActivity extends AppCompatActivity {
    public static String top_floor_last_flat_number_in_the_tallest_tower = "";
    public static String first_flat_number_on_beginning_of_residential_floor = "";
    public static String digits_in_flat_number = "";
    public static String number_of_tower_in_society = "";

    String nameOfSociety;
    String buildingID;

    String TAG = "tag";
    TextInputEditText TextInputEditText1;
    TextInputEditText TextInputEditText2;
    TextInputEditText TextInputEditText3;
    TextInputEditText TextInputEditText4;
    CheckBox isStartFlatNumberSameCheckBox;

    public String nameAuthorised;
    public String mobileNumber;
    public String emailId;
    public String headQuaterName;
    public String headQuaterId;
    public String ornNumber;
    public String address;
    public String pincode;

    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_multi_tower);

        Bundle bundle = getIntent().getExtras();
        buildingID = bundle.getString("buildingID");
        nameOfSociety = bundle.getString("nameOfTowerOrSociety");

        // new
        nameAuthorised = bundle.getString("nameAuthorised");
        mobileNumber = bundle.getString("mobileNumber");
        emailId = bundle.getString("emailId");
        headQuaterName = bundle.getString("headQuaterName");
        headQuaterId = bundle.getString("headQuaterId");
        ornNumber = bundle.getString("ornNumber");
        address = bundle.getString("address");
        pincode = bundle.getString("pincode");




        TextInputEditText1 = findViewById(R.id.TextInputEditText1);
        TextInputEditText2 = findViewById(R.id.TextInputEditText2);
        TextInputEditText3 = findViewById(R.id.TextInputEditText3);
        TextInputEditText4 = findViewById(R.id.TextInputEditText4);
        isStartFlatNumberSameCheckBox = findViewById(R.id.isStartFlatNumberSameCheckBox);

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
                } else {
                    TextInputEditText4.setText("");
                }
            }
        });


        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                top_floor_last_flat_number_in_the_tallest_tower = TextInputEditText1.getText().toString();
                first_flat_number_on_beginning_of_residential_floor = TextInputEditText2.getText().toString();
                number_of_tower_in_society = TextInputEditText3.getText().toString();
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

                if (number_of_tower_in_society.equals("")) {
                    TextInputEditText3.requestFocus();
                    TextInputEditText3.setError("Enter number of towers in the society");
                    return;
                }

                if (Integer.parseInt(number_of_tower_in_society) > 87) {
                    TextInputEditText3.requestFocus();
                    TextInputEditText3.setError("Enter valid number of towers between 1 to 87");
                    return;
                }

                Intent intent = new Intent(NewMultiTowerTowerActivity.this, FinalMuti.class);
                intent.putExtra("number", number_of_tower_in_society);
                intent.putExtra("buildingID", buildingID);
                intent.putExtra("nameOfTowerOrSociety", nameOfSociety);
                intent.putExtra("fixFirstFlatNumber", first_flat_number_on_beginning_of_residential_floor);
                intent.putExtra("digit", Integer.parseInt(digits_in_flat_number));
                intent.putExtra("isStartFlatNumberSame", isStartFlatNumberSameCheckBox.isChecked());


                //new
                intent.putExtra("nameAuthorised", nameAuthorised);
                intent.putExtra("mobileNumber", mobileNumber);
                intent.putExtra("emailId", emailId);
                intent.putExtra("headQuaterName", headQuaterName);
                intent.putExtra("headQuaterId", headQuaterId);
                intent.putExtra("ornNumber", ornNumber);
                intent.putExtra("address", address);
                intent.putExtra("pincode", pincode);

                startActivity(intent);
            }
        });
    }
}