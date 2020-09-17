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

public class FinalMultiGroup2 extends AppCompatActivity {

    public static int no_of_group = 0;
    public static List<tower_list> list_of_tower = new ArrayList<tower_list>();


    LinearLayout linearLayout;
    MaterialButton materialButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_multi_group2);


        linearLayout = findViewById(R.id.linearlayout);
        materialButton = findViewById(R.id.nextbtn);

        Intent intent = getIntent();
        final int index =intent.getIntExtra("index", 0);

        TextView tv = findViewById(R.id.grpno);
        tv.setText("Group " + (index+1));
        if((index+1) == list_of_tower.size()){
            materialButton.setText("Submit");
        }

        final List<View> list=new ArrayList<View>();

        for(int i=0; i< list_of_tower.get(index).getNo_of_tower(); i++){
            final LayoutInflater factory = getLayoutInflater();
            final View textEntryView = factory.inflate(R.layout.basicform, null);
            TextView textView = textEntryView.findViewById(R.id.towerno);
            textView.setText("Tower " + (i+1));
            list.add(textEntryView);
            linearLayout.addView(textEntryView);
        }

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for(View view1: list){
//                    TextInputEditText textInputEditText = view1.findViewById(R.id.TextInputEditText);
//                    Log.d("check",textInputEditText.getText().toString());
//                }
                if((index+1)!= list_of_tower.size()){
                    Intent intent1 = new Intent(FinalMultiGroup2.this, FinalMultiGroup2.class);
                    intent1.putExtra("index", index+1);
                    startActivity(intent1);
                }else{
                    Intent intent1 = new Intent(FinalMultiGroup2.this, HomeActivity.class);
                    startActivity(intent1);
                }
            }
        });

    }

    public static class tower_list{
        String name;
        int no_of_tower;

        public tower_list(String name, int no_of_tower) {
            this.name = name;
            this.no_of_tower = no_of_tower;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNo_of_tower() {
            return no_of_tower;
        }

        public void setNo_of_tower(int no_of_tower) {
            this.no_of_tower = no_of_tower;
        }
    }
}