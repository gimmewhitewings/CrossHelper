package com.example.crosshelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsRecyclerAdapter extends RecyclerView.Adapter<CardsViewHolder> {

    ArrayList<SchemeUnit> schemes;


    public CardsRecyclerAdapter(ArrayList<SchemeUnit> schemes) {
        this.schemes = schemes;
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {
        holder.imageId.setImageResource(schemes.get(position).imageId);
        holder.sizeValue.setText(schemes.get(position).size);
        holder.colorsValue.setText(Integer.toString(schemes.get(position).colorsAmount));
        holder.sizeText.setText(R.string.size_text);
        holder.colorsText.setText(R.string.colors_text);
    }

    @Override
    public int getItemCount() {
        return schemes.size();
    }
}
