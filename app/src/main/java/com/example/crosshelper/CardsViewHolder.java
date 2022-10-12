package com.example.crosshelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
public class CardsViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView sizeText;
    public TextView sizeValue;
    public TextView colorsText;
    public TextView colorsValue;
    public ImageView imageId;

    public CardsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageId = itemView.findViewById(R.id.cardImage);
        cardView = itemView.findViewById(R.id.cardView);
        sizeText = itemView.findViewById(R.id.sizeText);
        sizeValue = itemView.findViewById(R.id.sizeValue);
        colorsText = itemView.findViewById(R.id.colorsText);
        colorsValue = itemView.findViewById(R.id.colorsValue);
    }
}