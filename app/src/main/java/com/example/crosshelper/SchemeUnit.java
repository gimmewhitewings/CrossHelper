package com.example.crosshelper;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SchemeUnit implements Parcelable {
    int imageId;
    int width;
    int height;
    String size;
    String colors;
    int colorsAmount;
    boolean isFavorite;

    public SchemeUnit(int imageId, int width, int height, int colorsAmount) {
        this.imageId = imageId;
        this.width = width;
        this.height = height;
        this.size = width + "x" + height;
        this.colors = String.valueOf(colorsAmount);
        this.colorsAmount = colorsAmount;
        this.isFavorite = false;
    }

    public int getImageId() {
        return imageId;
    }

    protected SchemeUnit(Parcel in) {
        imageId = in.readInt();
        width = in.readInt();
        height = in.readInt();
        size = in.readString();
        colors = in.readString();
        colorsAmount = in.readInt();
    }

    public static final Creator<SchemeUnit> CREATOR = new Creator<SchemeUnit>() {
        @Override
        public SchemeUnit createFromParcel(Parcel in) {
            return new SchemeUnit(in);
        }

        @Override
        public SchemeUnit[] newArray(int size) {
            return new SchemeUnit[size];
        }
    };

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageId);
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getSize() {
        return size;
    }

    public int getColorsAmount() {
        return colorsAmount;
    }

    public void setColorsAmount(int colorsAmount) {
        this.colorsAmount = colorsAmount;
    }

}
