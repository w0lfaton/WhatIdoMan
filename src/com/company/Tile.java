package com.company;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;

public class Tile {
    private int[] singleTilePixelMap;
    private final static int PIXEL_MULTIPLIER = 8;
    private int tileWidth;
    private int tileHeight;

    public Tile(int[] singleTilePixelMap, int tileWidth, int tileHeight) {
        this.singleTilePixelMap = singleTilePixelMap;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public Tile(int[] singleTilePixelMap) {
        this(singleTilePixelMap, (int) Math.sqrt(singleTilePixelMap.length), (int) Math.sqrt(singleTilePixelMap.length));
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int[] getSingleTilePixelMap() {
        return singleTilePixelMap;
    }

    public int PIXEL_MULTIPLIER() {
        return PIXEL_MULTIPLIER;
    }

    public void setSingleTilePixelMap(int[] singleTilePixelMap) {
        this.singleTilePixelMap = singleTilePixelMap;
    }

    //TODO: Possible redundant - so need to reevaluate and clear if not needed.
    public int[] getTileFromSpreadsheet(int[] sheetPixelMap, int tileIndicator) {
        short xCounter = 0;
        short tileCounter = 0;
        int[] tilePixelMap = new int[64];
        if (sheetPixelMap != null) {
            for (int i = 0; i < 64; i++) {
                if (xCounter == 8) {
                    tileCounter += 248;
                    xCounter = 0;
                }
                tilePixelMap[i] = sheetPixelMap[tileCounter+((tileIndicator*8)*(((int) Math.floor((double) tileIndicator/32))*8))];
                xCounter++;
                tileCounter++;
                if (tileCounter == sheetPixelMap.length) {
                    break;
                }
            }
            return tilePixelMap;
        } else {
            return null;
        }
    }

    //TODO: Need to fix for tileIndexes length 3,6,9 etc; eg. What if I want a wide tilePixelMap or a long one?
    public int[] getTilesFromSpreadsheet(int[] sheetPixelMap, int[] tileIndexes) {
        short tileCounter = 0;
        short tileIndex;
        int pixelCount = 64*tileIndexes.length;
        int pixelCountDimension = (int) Math.sqrt(pixelCount);
        int[] tilePixelMap = new int[pixelCount];
        //Make sure that the passed sheetPixelMap is not null - that there were no issues loading the file.
        if (sheetPixelMap != null) {
            int sheetPixelMapDimension = (int) Math.sqrt(sheetPixelMap.length);
            for (int y = 0; y < pixelCountDimension;y++){
                for (int x = 0; x < pixelCountDimension;x++){
                    //TODO: Configure the logic so more tile indexes than 4 are supported.
                    int tilePositionIndicator;
                    if ((x >= 8 && x < 16) && (y >= 8 && y < 16)) {
                        tileIndex = 3;
                        tilePositionIndicator = ((y-8)*sheetPixelMapDimension) + (x - 8);
                    } else if ((x >= 8 && x < 16) && (y < 8)) {
                        tileIndex = 1;
                        tilePositionIndicator = ((y)*sheetPixelMapDimension) + (x - 8);
                    } else if ((y >= 8 && y < 16) && (x < 8)) {
                        tileIndex = 2;
                        tilePositionIndicator = ((y-8)*sheetPixelMapDimension) + (x);
                    } else {
                        tileIndex = 0;
                        tilePositionIndicator = (y*sheetPixelMapDimension) + x;
                    }
                    //The above should be correct for 4 tiles - need to make it so it works for even 9 tiles, which could be tough:
                    //System.out.println("X position: " + x + " Y position: " + y + " Tile Index being added:" + tileIndex);

                    int tileStartIndicator;
                    if (tileIndexes[tileIndex] >= 32) {
                        tileStartIndicator = (int) ((Math.floor((double) tileIndexes[tileIndex]/32) * 8) * sheetPixelMapDimension)
                                + ((tileIndexes[tileIndex] - (32 * (int) Math.floor((double) tileIndexes[tileIndex]/32))) * 8);
                    } else {
                        tileStartIndicator = tileIndexes[tileIndex] * 8;
                    }

                    Colour pixelColour = new Colour(sheetPixelMap[tilePositionIndicator + tileStartIndicator]);

                    tilePixelMap[tileCounter] = sheetPixelMap[tilePositionIndicator + tileStartIndicator];
                    tileCounter++;
                }
            }
            return tilePixelMap;
        } else {
            //Need to log when issues with loading the spreadsheet occur.
            return null;
        }
    }

    public int[] getFullSpreadsheetPixelMap(String path) {
        return loadSpreadsheet(path);
    }

    private int[] loadSpreadsheet(String path) {
        BufferedImage spreadsheet;
        //Can cascade the method into one try/catch.
        try {
            spreadsheet = ImageIO.read(new File(path));
            int[] spreadSheetPixelMap = new int[spreadsheet.getWidth() * spreadsheet.getHeight()];
            return spreadsheet.getRGB(0,0,spreadsheet.getWidth(),spreadsheet.getHeight(), spreadSheetPixelMap, 0, spreadsheet.getWidth());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating pixelmap or reading image.");
            return null;
        }
    }

    public Tile tileRotate(String direction) {
        int xPos = 0;
        int yPos = 0;
        int pixelCounter = 0;
        int[] newPixelMap = new int[this.getSingleTilePixelMap().length];
        if (direction.contains("left")) {
            xPos = this.getTileWidth() - 1;
            for (int x = 0; x < this.getTileWidth(); x++) {
                for (int y = 0; y < this.getTileHeight(); y++) {
                    int screenCounter = (yPos * this.getTileWidth()) + xPos;
                    newPixelMap[pixelCounter] = this.getSingleTilePixelMap()[screenCounter];
                    yPos++;
                    pixelCounter++;
                }
                yPos -= this.getTileHeight();
                xPos--;
            }
            this.setSingleTilePixelMap(newPixelMap);
            return this;
        } else if (direction.contains("mirror")) {
            xPos = this.getTileWidth() - 1;
            for (int y = 0; y < this.getTileHeight(); y++) {
                for (int x = 0; x < this.getTileWidth(); x++) {
                    int screenCounter = (yPos * this.getTileWidth()) + xPos;
                    newPixelMap[pixelCounter] = this.getSingleTilePixelMap()[screenCounter];
                    xPos--;
                    pixelCounter++;
                }
                xPos += this.getTileWidth();
                yPos++;
            }
            this.setSingleTilePixelMap(newPixelMap);
            return this;
        } else if (direction.contains("right")) {
            yPos = this.getTileHeight() - 1;
            for (int x = 0; x < this.getTileWidth(); x++) {
                for (int y = 0; y < this.getTileHeight(); y++) {
                    int screenCounter = (yPos * this.getTileWidth()) + xPos;
                    newPixelMap[pixelCounter] = this.getSingleTilePixelMap()[screenCounter];
                    yPos--;
                    pixelCounter++;
                }
                yPos += this.getTileHeight();
                xPos++;
            }
            this.setSingleTilePixelMap(newPixelMap);
            return this;
        } else {
            System.out.println("Input unrecognized");
            return null;
        }
    }

    public void doublePixelMap() {
        int newWidth = this.getTileWidth() * 2;
        int newHeight = this.getTileWidth() * 2;
        int[] doubledMap = new int[newWidth * newHeight];
        int pixelCounter = 0;
        int xPos = 0;
        int yPos = 0;

        int zoomX = 0;
        int zoomY = 0;
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int screenCounter = (yPos * this.getTileWidth()) + xPos;
                doubledMap[pixelCounter] = this.getSingleTilePixelMap()[screenCounter];
                pixelCounter++;
                zoomX++;
                if (zoomX == 2) {
                    xPos++;
                    zoomX = 0;
                }
            }
            xPos = 0;
            zoomY++;
            if (zoomY == 2) {
                yPos++;
                zoomY = 0;
            }
        }

        if (doubledMap.length == (this.getSingleTilePixelMap().length*4)) {
            this.setTileWidth(newWidth);
            this.setTileHeight(newHeight);
            this.setSingleTilePixelMap(doubledMap);
        } else {
            System.out.println("Loop broke - doubledMap length = " + doubledMap.length + ", original length = " + this.getSingleTilePixelMap().length);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(singleTilePixelMap);
    }
}
