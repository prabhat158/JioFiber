package com.jiocentrex;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    public static String name_of_society = "";
    public static String type_of_society = "";
    // New
    public String nameAuthorised;
    public String mobileNumber;
    public String emailId;
    public String headQuaterName;
    public String headQuaterId;
    public String jioCenterId;
    public String ornNumber;
    public String address;
    public String pincode;
    String TAG = "tag";
    TextInputEditText textInputEditText;
    MaterialButton materialButton;
    AutoCompleteTextView editTextFilledExposedDropdown;
    // New
    TextInputEditText nameAuthorisedInputEditText;
    TextInputEditText mobileNumberTextInputEditText;
    TextInputEditText emailIdInputEditText;
    TextInputEditText headQuaterNameInputEditText;
    TextInputEditText headQuaterIdInputEditText;
    TextInputEditText ornNumberInputEditText;
    TextInputEditText addressInputEditText;
    TextInputEditText pincodeInputEditText;
    EditText jioIdInputEditText;


    private boolean isValidEmail(String text) {
        return text.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") || text.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        String[] COUNTRIES = new String[]{"Single Tower society", "Multi Tower society"};
//        "Multi Tower group society", "Others"

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        R.layout.list_item,
                        COUNTRIES);

        editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);

        editTextFilledExposedDropdown.setAdapter(adapter);
        editTextFilledExposedDropdown.setInputType(InputType.TYPE_NULL);

        editTextFilledExposedDropdown.setKeyListener(null);

        textInputEditText = findViewById(R.id.TextInputEditText);
        setupPrefixSample();

        // New
        nameAuthorisedInputEditText = findViewById(R.id.nameAuthorisedInputEditText);
        mobileNumberTextInputEditText = findViewById(R.id.mobileNumberTextInputEditText);
        emailIdInputEditText = findViewById(R.id.emailIdInputEditText);
        headQuaterNameInputEditText = findViewById(R.id.headQuaterNameInputEditText);
        headQuaterIdInputEditText = findViewById(R.id.headQuaterIdInputEditText);
        ornNumberInputEditText = findViewById(R.id.ornNumberInputEditText);
        pincodeInputEditText = findViewById(R.id.pincodeInputEditText);
        addressInputEditText = findViewById(R.id.addressInputEditText);


        materialButton = findViewById(R.id.nextbtn);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_of_society = textInputEditText.getText().toString().trim();
                type_of_society = editTextFilledExposedDropdown.getText().toString();

                nameAuthorised = nameAuthorisedInputEditText.getText().toString().trim();
                mobileNumber = mobileNumberTextInputEditText.getText().toString().trim();
                emailId = emailIdInputEditText.getText().toString().trim();
                headQuaterName = headQuaterNameInputEditText.getText().toString().trim();
                headQuaterId = headQuaterIdInputEditText.getText().toString().trim();
                ornNumber = ornNumberInputEditText.getText().toString().trim();

                address = addressInputEditText.getText().toString().trim();
                pincode = pincodeInputEditText.getText().toString().trim();

                if (name_of_society.equals("")) {
                    textInputEditText.requestFocus();
                    textInputEditText.setError("Enter name of society");
                    return;
                }

                if (type_of_society.equals("")) {
                    editTextFilledExposedDropdown.setError("Select type of society");
                    return;
                }
                if (address.equals("")) {
                    addressInputEditText.setError("Enter address");
                    return;
                }
                if (pincode.equals("") || pincode.length() != 6) {
                    pincodeInputEditText.setError("Enter pincode");
                    return;
                }

                // New
                if (nameAuthorised.equals("")) {
                    nameAuthorisedInputEditText.requestFocus();
                    nameAuthorisedInputEditText.setError("Enter name authorised");
                    return;
                }

                if (mobileNumber.equals("")) {
                    mobileNumberTextInputEditText.requestFocus();
                    mobileNumberTextInputEditText.setError("Enter mobile number");
                    return;
                }
                if (mobileNumber.length() != 10) {
                    mobileNumberTextInputEditText.requestFocus();
                    mobileNumberTextInputEditText.setError("Enter 10 digit mobile number");
                    return;
                }

                if (emailId.equals("") || !isValidEmail(emailId)) {
                    emailIdInputEditText.requestFocus();
                    emailIdInputEditText.setError("Enter valid email");
                    return;
                }

                if (headQuaterName.equals("")) {
                    headQuaterNameInputEditText.requestFocus();
                    headQuaterNameInputEditText.setError("Enter head quoter name");
                    return;
                }

                if (headQuaterId.equals("")) {
                    headQuaterIdInputEditText.requestFocus();
                    headQuaterIdInputEditText.setError("Enter head quoter name");
                    return;
                }

                if (headQuaterId.length() != 10) {
                    headQuaterIdInputEditText.requestFocus();
                    headQuaterIdInputEditText.setError("Enter valid 10 digit head quoter id");
                    return;
                }

                if (ornNumber.equals("")) {
                    ornNumberInputEditText.requestFocus();
                    ornNumberInputEditText.setError("Enter orn number");
                    return;
                }

                if (jioCenterId == null || jioCenterId.equals("") || jioCenterId.length() != 18) {
                    jioIdInputEditText.requestFocus();
                    jioIdInputEditText.setError("Enter jio center id");
                    return;
                }

                if (ornNumber.length() != 12) {
                    ornNumberInputEditText.requestFocus();
                    ornNumberInputEditText.setError("Enter valid 12 alpha numeric orn number");
                    return;
                }


                Intent intent;
                if (editTextFilledExposedDropdown.getText().toString().equals("Single Tower society")) {
                    intent = new Intent(HomeActivity.this, NewSingleTowerActivity.class);
                } else {
                    intent = new Intent(HomeActivity.this, NewMultiTowerTowerActivity.class);
                }
                intent.putExtra("nameOfTowerOrSociety", name_of_society);


                // NEw
                intent.putExtra("nameAuthorised", nameAuthorised);
                intent.putExtra("mobileNumber", mobileNumber);
                intent.putExtra("emailId", emailId);
                intent.putExtra("headQuaterName", headQuaterName);
                intent.putExtra("headQuaterId", headQuaterId);
                intent.putExtra("ornNumber", ornNumber);
                intent.putExtra("jioCenterId", jioCenterId);
                intent.putExtra("address", address);
                intent.putExtra("pincode", pincode);
                startActivity(intent);


//                if (editTextFilledExposedDropdown.getText().toString().equals("Single Tower society")) {
////                    Intent intent = new Intent(HomeActivity.this, NewSingleTowerActivity.class);
//                    Intent intent = new Intent(HomeActivity.this, SocietyCommonPointActivity1.class);
//                    intent.putExtra("nameOfTowerOrSociety", name_of_society);
//                    intent.putExtra("buildingID", building_id);
//                    startActivity(intent);
//                } else if (editTextFilledExposedDropdown.getText().toString().equals("Multi Tower society")) {
////                    Intent intent = new Intent(HomeActivity.this, NewMultiTowerTowerActivity.class);
//                    Intent intent = new Intent(HomeActivity.this, SocietyCommonPointActivity1.class);
//                    intent.putExtra("nameOfSociety", name_of_society);
//                    intent.putExtra("buildingID", building_id);
//                    startActivity(intent);
//                }

//                else if (editTextFilledExposedDropdown.getText().toString().equals("Multi Tower group society")) {
//                    startActivity(new Intent(HomeActivity.this, MultiTowerGroupSociety.class));
//                } else if (editTextFilledExposedDropdown.getText().toString().equals("Others")) {
//                    Intent intent = new Intent(HomeActivity.this, Other.class);
//                    intent.putExtra(AppConstants.SOCIETY_NAME, textInputEditText.getText().toString());
//                    startActivity(intent);
//                }

//                Log.d("check", textInputEditText.getText().toString());
//                Log.d("check", editTextFilledExposedDropdown.getText().toString());
            }
        });

        isReadStoragePermissionGranted();
    }

    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted1");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted1");
            return true;
        }
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted2");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted2");
            return true;
        }
    }

    private void setupPrefixSample() {
        jioIdInputEditText = findViewById(R.id.jioIdInputEditText);
        final List<String> affineFormats = new ArrayList<>();
        affineFormats.add("[A]-[AA]-[AAAA]-[AAA]-[0000]"); // [A]-[AA]-[AAAA]-[AAA]-[AAAA]
        final MaskedTextChangedListener listener = MaskedTextChangedListener.Companion.installOn(
                jioIdInputEditText,
                "[A]-[AA]-[AAAA]-[AAA]-[0000]",
                affineFormats,
                AffinityCalculationStrategy.PREFIX,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue, @NonNull String formattedText) {
                        logValueListener(maskFilled, extractedValue, formattedText);
                        jioCenterId = formattedText;
                    }
                }
        );
//        editText.setHint(listener.placeholder());
    }

    private void logValueListener(boolean maskFilled, @NonNull String extractedValue, @NonNull String formattedText) {
        final String className = MainActivity.class.getSimpleName();
        Log.d(className, extractedValue);
        Log.d(className, String.valueOf(maskFilled));
        Log.d(className, formattedText);
    }
}