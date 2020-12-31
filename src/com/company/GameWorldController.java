package com.company;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class GameWorldController extends Canvas {
    private int inputXLocation = 0;
    private int inputYLocation = 0;
    private List<Screen> screenList;
    private int screenIndicator = 0;

    public boolean isUIOpen = false;

    public GameWorldController(List<Screen> screenList) {
        this.screenList = screenList;
    }

    public GameWorldController() {
        //default List
        this(new LinkedList<>());
    }

    public List<Screen> getScreenList() {
        return screenList;
    }

    public int getScreenIndicator() {
        return screenIndicator;
    }

    public void setScreenIndicator(int screenIndicator) {
        if (screenIndicator < screenList.size()) {
            this.screenIndicator = screenIndicator;
        } else {
            System.out.println("Screen indicator index too large - cannot choose screen.");
        }
    }

    public int getInputXLocation() {
        return inputXLocation;
    }

    public int getInputYLocation() {
        return inputYLocation;
    }

    public void setInputXLocation(int inputXLocation) {
        screenList.get(screenIndicator).setInputXLocation(inputXLocation);
        this.inputXLocation = inputXLocation;
    }

    public void setInputYLocation(int inputYLocation) {
        screenList.get(screenIndicator).setInputYLocation(inputYLocation);
        this.inputYLocation = inputYLocation;
    }

    public boolean addScreen(Screen screen) {
        if (screen != null) {
            this.screenList.add(screen);
            return true;
        } else {
            return false;
        }
    }

    public boolean movePlayerUp(Entity player) {
        if (!isUIOpen && screenIndicator == 0) {
            player.setYPosition(player.getYPosition() - 1);
            return ((WorldScreen) screenList.get(screenIndicator)).updateEntity(player);
        } else {
            return false;
        }
    }
    public boolean movePlayerDown(Entity player) {
        if (!isUIOpen && screenIndicator == 0) {
            player.setYPosition(player.getYPosition() + 1);
            return ((WorldScreen) screenList.get(screenIndicator)).updateEntity(player);
        } else {
            return false;
        }
    }
    public boolean movePlayerLeft(Entity player) {
        if (!isUIOpen && screenIndicator == 0) {
            player.setXPosition(player.getXPosition() - 1);
            return ((WorldScreen) screenList.get(screenIndicator)).updateEntity(player);
        } else {
            return false;
        }
    }
    public boolean movePlayerRight(Entity player) {
        if (!isUIOpen && screenIndicator == 0) {
            player.setXPosition(player.getXPosition() + 1);
            return ((WorldScreen) screenList.get(screenIndicator)).updateEntity(player);
        } else {
            return false;
        }
    }
    public boolean mouseClicked(int x, int y) {
        if (screenList.get(screenIndicator) instanceof DevScreen) {
            return this.mouseClickedDevScreen(x,y);
        } else {
            return false;
        }
    }
    public boolean mouseWheelUp() {
        return screenList.get(screenIndicator).increaseZoom();
    }
    public boolean mouseWheelDown() {
        return screenList.get(screenIndicator).decreaseZoom();
    }
    public boolean showNextTile() {
        if (!isUIOpen && (screenList.get(screenIndicator) instanceof DevScreen)) {
            return ((DevScreen) screenList.get(screenIndicator)).nextTile();
        } else {
           return false;
        }
    }
    public boolean showPreviousTile() {
        if (!isUIOpen && (screenList.get(screenIndicator) instanceof DevScreen)) {
            return ((DevScreen) screenList.get(screenIndicator)).previousTile();
        } else {
            return false;
        }
    }
    public boolean rotateTileInDevScreen() {
        if (screenList.get(screenIndicator) instanceof DevScreen) {
            return ((DevScreen) screenList.get(screenIndicator)).rotateTile();
        } else {
            return false;
        }
    }
    public void incrementIndicator() {
        if (screenIndicator != screenList.size()-1) {
            this.screenIndicator++;
        } else {
            System.out.println("Reached the end of the list.");
        }
    }
    public void decrementIndicator() {
        if (screenIndicator != 0) {
            this.screenIndicator--;
        } else {
            System.out.println("Reached the start of the list.");
        }
    }

    private boolean mouseClickedDevScreen(int x, int y) {
        return ((DevScreen) screenList.get(screenIndicator)).mouseClicked(x, y);
    }

    @Override
    public void paint(Graphics g) {
        screenList.get(screenIndicator).paint(g);
    }
}
