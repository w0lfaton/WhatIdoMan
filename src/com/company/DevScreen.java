package com.company;

import java.awt.*;
import java.util.HashMap;

public class DevScreen extends Screen {
    private DevBackgroundMap devBackgroundMap;

    public DevScreen(String description, int windowWidth, int windowHeight) {
        super(description, windowWidth, windowHeight);
        devBackgroundMap = new DevBackgroundMap("development", new HashMap<>(), new int[windowHeight*windowWidth], windowHeight, windowWidth);
    }

    public DevScreen(String description) {
        this(description, 800,800);
    }

    public boolean nextTile() {
        return devBackgroundMap.incrementSelectionIndex();
    }

    public boolean previousTile() {
        return devBackgroundMap.decrementSelectionIndex();
    }

    public boolean mouseClicked(int x, int y) {
        return devBackgroundMap.drawTileToMap(getInputXLocation(),getInputYLocation());
    }

    public boolean rotateTile() {
        return devBackgroundMap.rotateHoverTile();
    }

    @Override
    public void paint(Graphics g) {
        devBackgroundMap.updateUI();
        devBackgroundMap.addHoverTile(getInputXLocation(), getInputYLocation(), false);
        this.getBufferedImage().setRGB(0,0,this.getBufferedImage().getWidth(),this.getBufferedImage().getHeight(),this.devBackgroundMap.getBackgroundPixelMap(),0,this.getBufferedImage().getWidth());
        g.drawImage(this.getBufferedImage(),0,0,this);
    }
}
