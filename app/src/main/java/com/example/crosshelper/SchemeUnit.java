package com.example.crosshelper;

public class SchemeUnit {
    int imageId;
    int width;
    int height;
    String size;
    int colorsAmount;

    public SchemeUnit(int imageId, int width, int height, int colorsAmount) {
        this.imageId = imageId;
        this.width = width;
        this.height = height;
        this.size = width + "x" + height;
        this.colorsAmount = colorsAmount;
    }
}
