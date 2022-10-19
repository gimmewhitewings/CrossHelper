package com.example.crosshelper.editor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.crosshelper.MainActivity;
import com.example.crosshelper.R;
import com.example.crosshelper.SchemeUnit;

import java.lang.ref.WeakReference;

public class EditorActivity extends AppCompatActivity {
    ImageViewController imageViewController;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        SchemeUnit schemeUnit = getIntent().getParcelableExtra("scheme_object");

        imageViewController = new ImageViewController(
                this,
                new WeakReference<>(findViewById(R.id.imageView)),
                BitmapFactory.decodeResource(getResources(), schemeUnit.getImageId()),
                getResources().getDisplayMetrics().widthPixels,
                60,
                40,
                new Point(
                        getResources().getDisplayMetrics().widthPixels,
                        getResources().getDisplayMetrics().heightPixels
                )
        );

        SwitchCompat switchButton = findViewById(R.id.switch1);
        switchButton.setOnCheckedChangeListener((compoundButton, b) ->
                imageViewController.setMode(b)
        );

        Button undoButton = findViewById(R.id.undoButton);
        undoButton.setOnClickListener(
                view -> imageViewController.undo()
        );

        Button redoButton = findViewById(R.id.redoButton);
        redoButton.setOnClickListener(
                view -> imageViewController.redo()
        );

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        imageViewController.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}