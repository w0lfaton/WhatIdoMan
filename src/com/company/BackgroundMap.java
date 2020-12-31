package com.company;

import java.util.HashMap;
import java.util.Map;

public class BackgroundMap {
    private final static String GENERAL_PATH = "BitmapSheet.bmp";
    private final String description;
    private static int mapCounter;
    private final int ID;
    private Map<Integer, BackgroundTile> backgroundTiles;
    private int[] backgroundPixelMap;
    private int mapHeight;
    private int mapWidth;
    private int tileCounter = 0;
    private int tileIndicator = 0;

    public BackgroundMap(String description, Map<Integer, BackgroundTile> backgroundTiles, int[] backgroundPixelMap, int mapHeight, int mapWidth) {
        this.description = description;
        this.backgroundTiles = backgroundTiles;
        this.backgroundPixelMap = backgroundPixelMap;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        mapCounter++;
        this.ID = mapCounter;

        addBackgroundTileToTileList(new BackgroundTile(new int[]{8,8,39,8}, GENERAL_PATH));
        fillWorldBackgroundPixelMap();
    }

    public BackgroundMap(String description, Map<Integer, BackgroundTile> backgroundTiles) {
        this(description, backgroundTiles, new int[640000], 800, 800);
    }

    public BackgroundMap(String description) {
        this(description, new HashMap<>());
    }

    public String getDescription() {
        return description;
    }

    public static int getMapCounter() {
        return mapCounter;
    }

    public int getID() {
        return ID;
    }

    public static String getGeneralPath() {
        return GENERAL_PATH;
    }

    public Map<Integer, BackgroundTile> getBackgroundTiles() {
        return backgroundTiles;
    }

    public int[] getBackgroundPixelMap() {
        return backgroundPixelMap;
    }

    public void setBackgroundPixelMap(int[] backgroundPixelMap) {
        this.backgroundPixelMap = backgroundPixelMap;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public boolean addBackgroundTileToTileList(BackgroundTile tile) {
        if (tile != null) {
            this.backgroundTiles.put(tileCounter,tile);
            tileCounter++;
            return true;
        } else {
            return false;
        }
    }

    // TODO: Make filling in the background tiles more dynamic - not just one variation of tile.
    private boolean fillWorldBackgroundPixelMap() {
        if (ID > 0) {
            int pixelMapCounter = 0;
            int tilePixelPosition;
            int tileXPos = 0;
            int tileYPos = 0;
            int tileDimension = this.getBackgroundTiles().get(tileIndicator).getTileWidth();
            main:
            for (int y = 0; y < mapHeight; y++) {
                for (int x = 0; x < mapWidth; x++) {
                    tilePixelPosition = (tileYPos * tileDimension) + tileXPos;
                    this.backgroundPixelMap[pixelMapCounter] = this.getBackgroundTiles().get(tileIndicator).getSingleTilePixelMap()[tilePixelPosition];
                    tileXPos++;
                    pixelMapCounter++;
                    if (tileXPos == tileDimension) tileXPos = 0;
                }
                tileYPos++;
                if (tileYPos == tileDimension) tileYPos = 0;
            }
            return true;
        } else {
            System.out.println("World ID invalid.");
            return false;
        }
    }

    public int[] translateZoom(int xPos, int yPos, int zoomIndicator) {
        int[] enlargedMap = new int[mapWidth*mapHeight];
        int zoomX = 0;
        int zoomY = 0;

        int yPosCounter = yPos;
        int xPosCounter = xPos;
        int pixelCounter = 0;
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int screenCounter = (yPosCounter * mapWidth) + xPosCounter;
                enlargedMap[pixelCounter] = backgroundPixelMap[screenCounter];
                pixelCounter++;
                zoomX++;
                if (zoomX == zoomIndicator) {
                    xPosCounter++;
                    zoomX = 0;
                }
            }
            xPosCounter = xPos;
            zoomY++;
            if (zoomY == zoomIndicator) {
                yPosCounter++;
                zoomY = 0;
            }
        }
        return enlargedMap;
    }
}
