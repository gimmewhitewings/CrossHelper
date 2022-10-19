package com.example.crosshelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsRecyclerAdapter extends RecyclerView.Adapter<CardsRecyclerAdapter.CardsViewHolder> {

    ArrayList<SchemeUnit> schemes;
    private OnCardListener onCardListener;


    public CardsRecyclerAdapter(ArrayList<SchemeUnit> schemes, OnCardListener onCardListener) {
        this.onCardListener = onCardListener;
        this.schemes = schemes;
    }

    class CardsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public TextView sizeText;
        public TextView sizeValue;
        public TextView colorsText;
        public TextView colorsValue;
        public ImageView imageId;

        OnCardListener onCardListener;

        public CardsViewHolder(@NonNull View itemView, OnCardListener onCardListener) {
            super(itemView);
            imageId = itemView.findViewById(R.id.cardImage);
            cardView = itemView.findViewById(R.id.cardView);
            sizeText = itemView.findViewById(R.id.sizeText);
            sizeValue = itemView.findViewById(R.id.sizeValue);
            colorsText = itemView.findViewById(R.id.colorsText);
            colorsValue = itemView.findViewById(R.id.colorsValue);
            this.onCardListener = onCardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new CardsViewHolder(view, onCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {
        holder.imageId.setImageResource(schemes.get(position).imageId);
        holder.sizeValue.setText(schemes.get(position).size);
        holder.colorsValue.setText(schemes.get(position).colors);
        holder.sizeText.setText(R.string.size_text);
        holder.colorsText.setText(R.string.colors_text);
    }

    @Override
    public int getItemCount() {
        return schemes.size();
    }

    public interface OnCardListener {
        void onCardClick(int position);
    }

}
