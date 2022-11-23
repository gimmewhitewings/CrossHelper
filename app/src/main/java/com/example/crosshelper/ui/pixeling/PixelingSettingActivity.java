package com.example.crosshelper.ui.pixeling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.crosshelper.R;

public class PixelingSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixeling_setting);
        ImageView loadedPicture = findViewById(R.id.loadedPictureImageView);
        loadedPicture.setImageURI(getIntent().getData());
    }
}