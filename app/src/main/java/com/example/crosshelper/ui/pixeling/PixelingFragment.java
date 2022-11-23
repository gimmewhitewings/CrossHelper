package com.example.crosshelper.ui.pixeling;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.crosshelper.R;

public class PixelingFragment extends Fragment {

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            result -> {
                // pass result to pixeling setting activity
                Intent intent = new Intent(getActivity(), PixelingSettingActivity.class);
                intent.setData(result);
                startActivity(intent);
            });

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pixeling, container, false);
        Button addSchemeButton = view.findViewById(R.id.addSchemeButton);
        addSchemeButton.setOnClickListener(v -> {
            // pick image from gallery
            mGetContent.launch("image/*");
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}