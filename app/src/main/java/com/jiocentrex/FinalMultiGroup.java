package com.jiocentrex;

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

public class FinalMultiGroup extends AppCompatActivity {

    LinearLayout linearLayout;
    MaterialButton materialButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_multi_group);

        linearLayout = findViewById(R.id.linearlayout);
        materialButton = findViewById(R.id.nextbtn);

        final List<towerGrp> list=new ArrayList<towerGrp>();

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(towerGrp tg: list){
                    TextInputEditText te0 = tg.getView().findViewById(R.id.TextInputEditText0);
                    Log.d("check towerGrp",  te0.getText().toString());
                    for(tower tw: tg.getTowers()){
                        TextInputEditText te1 = tw.getView().findViewById(R.id.TextInputEditText0);
                        Log.d("check tower",  te1.getText().toString());
                        for (wing wg: tw.getWings()){
                            TextInputEditText te2 = wg.getView().findViewById(R.id.TextInputEditText0);
                            Log.d("check wing",  te2.getText().toString());
                        }
                    }
                }
            }
        });

        Intent intent = getIntent();
        int num = Integer.parseInt(intent.getStringExtra("number"));

        for(int i=0; i< num; i++){
            final LayoutInflater factory = getLayoutInflater();
            final View textEntryView = factory.inflate(R.layout.tg_group, null);
            final towerGrp towerGrp1 = new towerGrp(textEntryView);
            TextView textView = textEntryView.findViewById(R.id.towergrpno);
            MaterialButton materialButton = textEntryView.findViewById(R.id.addtower);
            final LinearLayout linearLayoutTower = textEntryView.findViewById(R.id.lltower);
            textView.setText("Group " + (i+1));
            materialButton.setText("Add Tower for Group "+ (i+1));
            materialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final LayoutInflater factory = getLayoutInflater();
                    final View tg_tower = factory.inflate(R.layout.tg_tower, null);
                    final tower tower1 = new tower(tg_tower);
                    TextView textView = tg_tower.findViewById(R.id.towertowerno);
                    MaterialButton mb = tg_tower.findViewById(R.id.delete);
                    textView.setText("Tower " + (towerGrp1.getTowers().size()+1));
                    mb.setText("Remove Tower " + (towerGrp1.getTowers().size()+1));

                    MaterialButton materialButton1 = tg_tower.findViewById(R.id.addwing);
                    materialButton1.setText("Add Wing for Tower "+ (towerGrp1.getTowers().size()+1));
                    final LinearLayout linearLayoutWing = tg_tower.findViewById(R.id.llwing);

                    mb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int index = towerGrp1.getTowers().indexOf(tower1);
                            towerGrp1.getTowers().remove(tower1);
                            linearLayoutTower.removeView(tg_tower);

                            for(int i=index; i<towerGrp1.getTowers().size();i++){
                                TextView tv = towerGrp1.getTowers().get(i).getView().findViewById(R.id.towertowerno);
                                MaterialButton mb = towerGrp1.getTowers().get(i).getView().findViewById(R.id.delete);
                                MaterialButton materialButton1 = towerGrp1.getTowers().get(i).getView().findViewById(R.id.addwing);
                                tv.setText("Tower " + (i+1));
                                mb.setText("Remove Tower " + (i+1));
                                materialButton1.setText("Add Wing for Tower "+ (i+1));
                            }
                        }
                    });

                    materialButton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final LayoutInflater factory = getLayoutInflater();
                            final View tg_wing = factory.inflate(R.layout.tg_wing, null);

                            final wing wing1 = new wing(tg_wing);

                            TextView textView = tg_wing.findViewById(R.id.towerwingno);
                            MaterialButton mb_wing = tg_wing.findViewById(R.id.delete);

                            textView.setText("Wing " + (tower1.getWings().size()+1));
                            mb_wing.setText("Remove Wing " + (tower1.getWings().size()+1));

                            mb_wing.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int ind = tower1.getWings().indexOf(wing1);

                                    tower1.getWings().remove(wing1);
                                    linearLayoutWing.removeView(tg_wing);

                                    for(int i=ind; i<tower1.getWings().size();i++){

                                        TextView tv = tower1.getWings().get(i).getView().findViewById(R.id.towerwingno);
                                        MaterialButton mb = tower1.getWings().get(i).getView().findViewById(R.id.delete);

                                        tv.setText("Wing " + (i+1));
                                        mb.setText("Remove Wing " + (i+1));
                                    }
                                }
                            });

                            tower1.getWings().add(wing1);
                            linearLayoutWing.addView(tg_wing);
                        }
                    });

                    towerGrp1.getTowers().add(tower1);
                    linearLayoutTower.addView(tg_tower);
                }
            });

            list.add(towerGrp1);
            linearLayout.addView(textEntryView);
        }

    }

    public class towerGrp{
        private View view;
        private List<tower> towers = new ArrayList<tower>();
        public towerGrp(View view){
            this.view = view;
        }
        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }

        public List<tower> getTowers() {
            return towers;
        }

        public void setTowers(List<tower> towers) {
            this.towers = towers;
        }
    }

    public class tower{
        private View view;
        private List<wing> wings = new ArrayList<wing>();
        public tower( View view){
            this.view = view;
        }
        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }

        public List<wing> getWings() {
            return wings;
        }

        public void setWings(List<wing> wings) {
            this.wings = wings;
        }
    }

    public class wing{
        private View view;

        public wing(View view) {
            this.view = view;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }
    }
}