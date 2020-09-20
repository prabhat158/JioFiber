package com.example.jiofiberapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.Nullable;

public class BottomDialogFragment extends BottomSheetDialogFragment {

    public static BottomDialogFragment newInstance() {
        return new BottomDialogFragment();
    }

    private Button shareFile;
    private Button viewFile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        shareFile = view.findViewById(R.id.shareFile);
        viewFile = view.findViewById(R.id.viewFile);

        shareFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract.shareFile();
            }
        });

        viewFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract.viewFile();
            }
        });


        return view;
    }

    private ManageClickContract contract;

    public void setManageClickContract(ManageClickContract contract) {
        this.contract = contract;
    }

    public interface ManageClickContract {
        void viewFile();

        void shareFile();
    }
}
