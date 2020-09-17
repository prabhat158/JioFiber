package com.example.jiofiberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class FinalMultiGroup1 extends AppCompatActivity {


    LinearLayout linearLayout;
    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_multi_group1);


        linearLayout = findViewById(R.id.linearlayout);
        materialButton = findViewById(R.id.nextbtn);

        final List<View> list=new ArrayList<View>();

        Intent intent = getIntent();
        int num = Integer.parseInt(intent.getStringExtra("number"));
        for(int i=0; i< num; i++){
            final LayoutInflater factory = getLayoutInflater();
            final View textEntryView = factory.inflate(R.layout.tg_group1, null);
            TextView textView = textEntryView.findViewById(R.id.towergrpno);
            textView.setText("Group " + (i+1));
            list.add(textEntryView);
            linearLayout.addView(textEntryView);
        }

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(View view1: list){
                    TextInputEditText textInputEditText = view1.findViewById(R.id.TextInputEditText0);
                    TextInputEditText textInputEditText1 = view1.findViewById(R.id.TextInputEditText);
                    FinalMultiGroup2.list_of_tower.add(new FinalMultiGroup2.tower_list(textInputEditText.getText().toString(),
                            Integer.parseInt(textInputEditText1.getText().toString())));

                    Log.d("check",textInputEditText.getText().toString());
                    Log.d("check",textInputEditText1.getText().toString());
                }

                Intent intent1 = new Intent(FinalMultiGroup1.this, FinalMultiGroup2.class);
                intent1.putExtra("index", 0);
                startActivity(intent1);
            }
        });
    }
}