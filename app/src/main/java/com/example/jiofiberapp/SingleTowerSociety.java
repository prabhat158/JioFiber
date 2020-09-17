package com.example.jiofiberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleTowerSociety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tower_society);

        String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyCsvFile.csv"); // Here csv file name is MyCsvFile.csv

        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));

            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[]{"Country", "Capital"});
            data.add(new String[]{"India", "New Delhi"});
            data.add(new String[]{"United States", "Washington D.C"});
            data.add(new String[]{"Germany", "Berlin"});

            writer.writeAll(data); // data is adding to csv
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}