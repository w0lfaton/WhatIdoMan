package com.company;

public class Colour {
    private int r,g,b;
    private int rgb;

    public Colour(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rgb = ((b | (g << 8)) | (r << 16)) | (255 << 24);
    }

    public Colour(int rgb) {
        this((rgb & 0xff0000) >> 16, (rgb & 0xff00) >> 8, (rgb & 0xff));
    }

    public int getRgb() {
        return rgb;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
