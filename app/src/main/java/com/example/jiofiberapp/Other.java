package com.example.jiofiberapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import easyfilepickerdialog.kingfisher.com.library.model.DialogConfig;
import easyfilepickerdialog.kingfisher.com.library.model.SupportFile;
import easyfilepickerdialog.kingfisher.com.library.view.FilePickerDialogFragment;

public class Other extends AppCompatActivity {

    String societyName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(AppConstants.SOCIETY_NAME))
                societyName = bundle.getString(AppConstants.SOCIETY_NAME);
        }

//        final TextInputEditText ti1 = findViewById(R.id.TextInputEditText0);
        final TextInputEditText ti3 = findViewById(R.id.TextInputEditText1);

        MaterialButton materialButton = findViewById(R.id.nextbtn);


        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (/*ti1.getText().toString().equals("") ||*/ ti3.getText().toString().equals("")) {
                    ti3.setError("Enter Number of houses/flats/floors");
                    return;
                }
                try {
//                    writer = new CSVWriter(new FileWriter(file));

                    List<String[]> data = new ArrayList<String[]>();

                    data.add(new String[]{"serial_number", "label", "short_code"});
//                    data.add(new String[]{"1", ti1.getText().toString()+"-"+ti3.getText().toString(), ti3.getText().toString()});
                    data.add(new String[]{"1", societyName + "-" + ti3.getText().toString(), ti3.getText().toString()});

                    StringBuilder data1 = new StringBuilder();
                    for (int i = 0; i < data.size(); i++) {
                        data1.append("\n" + data.get(i)[0] + "," + data.get(i)[1] + "," + data.get(i)[2]);
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
                                                        Intent intent = new Intent(Other.this, HomeActivity.class);
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
        });


    }

    private void shareFileToUser(File file) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),
                BuildConfig.APPLICATION_ID + ".provider", file));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}