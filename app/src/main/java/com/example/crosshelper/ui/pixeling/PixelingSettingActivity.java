package com.example.crosshelper.ui.pixeling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.crosshelper.R;
import com.example.crosshelper.editor.EditorActivity;
import com.example.crosshelper.editor.ImageViewController;
import com.example.crosshelper.storage.StorageController;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class PixelingSettingActivity extends AppCompatActivity {
    ImageViewController imageViewController;
    Bitmap resizedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixeling_setting);
        ImageView loadedPictureView = findViewById(R.id.loadedPictureImageView);
        loadedPictureView.setImageURI(getIntent().getData());

        Bitmap imageBitmap;
        try {
            imageBitmap = MediaStore.Images.Media.getBitmap(
                    this.getContentResolver(), getIntent().getData()
            );
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        imageViewController = new ImageViewController(
                this,
                new WeakReference<>(loadedPictureView),
                imageBitmap,
                getResources().getDisplayMetrics().widthPixels,
                255,
                50,
                new Point(
                        getResources().getDisplayMetrics().widthPixels,
                        getResources().getDisplayMetrics().heightPixels
                )
        );
        resizedBitmap = imageViewController.getPixelatedBitmap();

        SeekBar seekBar = findViewById(R.id.pixelingSeekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Limit from 5 to 105
                imageViewController.resizeImage(
                        imageBitmap,
                        seekBar.getProgress() + 5
                );
            }
        });

        Button acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(
                view -> {
                    Intent intent = new Intent(
                            PixelingSettingActivity.this, EditorActivity.class
                    );

                    StorageController.editor.putString(
                            StorageController.BITMAP,
                            StorageController.encodeToBase64(
                                    imageViewController.getPixelatedBitmap()
                            )
                    );
                    StorageController.editor.commit();
                    intent.putExtra("pixelsInBigSide", seekBar.getProgress() + 5);
                    startActivity(intent);
                }
        );
    }
}