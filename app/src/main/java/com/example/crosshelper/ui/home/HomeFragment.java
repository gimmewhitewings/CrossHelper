package com.example.crosshelper.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crosshelper.CardsRecyclerAdapter;
import com.example.crosshelper.R;
import com.example.crosshelper.SchemeUnit;
import com.example.crosshelper.editor.EditorActivity;
import com.example.crosshelper.storage.StorageController;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements CardsRecyclerAdapter.OnCardListener {
    private ArrayList<SchemeUnit> schemes;

    private void initializeData() {
        schemes = new ArrayList<>();
        schemes.add(new SchemeUnit(R.drawable.two_minions, 200, 100, 50));
        schemes.add(new SchemeUnit(R.drawable.minion, 50, 100, 5));
        schemes.add(new SchemeUnit(R.drawable.another_minion, 100, 45, 6));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
        schemes.add(new SchemeUnit(R.drawable.bob_minion_pixel_art, 30, 30, 5));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeData();
        RecyclerView recyclerview = view.findViewById(R.id.cardsRecyclerView);
        recyclerview.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        CardsRecyclerAdapter cardsRecyclerAdapter = new CardsRecyclerAdapter(schemes, this);
        recyclerview.setAdapter(cardsRecyclerAdapter);

        StorageController.settings = getActivity().getSharedPreferences(
                StorageController.STORAGE_NAME,
                Context.MODE_PRIVATE
        );
        StorageController.editor = StorageController.settings.edit();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {
        super.dump(prefix, fd, writer, args);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(this.getContext(), EditorActivity.class);

        Bitmap bitmap = BitmapFactory.decodeResource(
                getResources(),
                schemes.get(position).getImageId()
        );

        StorageController.editor.putString(
                StorageController.BITMAP,
                StorageController.encodeToBase64(bitmap)
        );
        StorageController.editor.commit();
        intent.putExtra("pixelsInBigSide", 40);
        startActivity(intent);
    }
}