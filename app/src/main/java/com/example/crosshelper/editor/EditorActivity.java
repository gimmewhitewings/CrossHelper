package com.example.crosshelper.editor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crosshelper.MainActivity;
import com.example.crosshelper.R;
import com.example.crosshelper.editor.recyclerview.CustomRecyclerAdapter;
import com.example.crosshelper.editor.recyclerview.RecyclerListener;
import com.example.crosshelper.storage.StorageController;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class EditorActivity extends AppCompatActivity implements RecyclerListener {
    ImageViewController imageViewController;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Bitmap bitmap = StorageController.decodeToBase64(
                StorageController.settings.getString(StorageController.BITMAP, "")
        );

        // Loading images directly and from processing
        imageViewController = new ImageViewController(
                this,
                new WeakReference<>(findViewById(R.id.imageView)),
                bitmap,
                getResources().getDisplayMetrics().widthPixels,
                60,
                getIntent().getIntExtra("pixelsInBigSide", 0),
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
        closeButton.setOnClickListener(view -> {
            Intent intent = new Intent(EditorActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Prepare list of colors
        ArrayList<Integer> colors = new ArrayList<>(imageViewController.getAllColors());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(
                new GridLayoutManager(
                        this,
                        2,
                        GridLayoutManager.HORIZONTAL,
                        false
                )
        );
        recyclerView.setAdapter(new CustomRecyclerAdapter(this, colors));

        // To automatically adjust when scrolling through a list
        LinearSnapHelper pagerSnapHelper = new LinearSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        imageViewController.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onElementClick(int color) {
        imageViewController.highLightAllPixelsWithColor(color);
    }
}