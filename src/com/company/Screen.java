package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends Canvas {
    private static int screenCounter;
    private final int uniqueID;
    private final String description;
    private BufferedImage bufferedImage;
    private int inputXLocation;
    private int inputYLocation;
    private int zoomMultiplier = 0;

    public Screen(String description, int windowWidth, int windowHeight) {
        super();
        this.description = description;
        this.bufferedImage = new BufferedImage(windowWidth,windowHeight,BufferedImage.TYPE_INT_ARGB);
        screenCounter++;
        uniqueID = screenCounter;
    }

    public Screen(String description) {
        this(description, 800, 800);
    }

    public static int getScreenCounter() {
        return screenCounter;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public String getDescription() {
        return description;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public int getInputXLocation() {
        return inputXLocation;
    }

    public void setInputXLocation(int inputXLocation) {
        this.inputXLocation = inputXLocation;
    }

    public int getInputYLocation() {
        return inputYLocation;
    }

    public void setInputYLocation(int inputYLocation) {
        this.inputYLocation = inputYLocation;
    }

    public int getZoomMultiplier() {
        return zoomMultiplier;
    }

    public boolean increaseZoom() {
        if (zoomMultiplier < 9) {
            zoomMultiplier++;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean decreaseZoom() {
        if (zoomMultiplier > 1) {
            zoomMultiplier--;
            return true;
        } else {
            return false;
        }
    }
}
