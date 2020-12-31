package com.company;

import java.util.Arrays;

public class ObstacleTile extends Tile {
    private int[] tileIndexes;

    public ObstacleTile(int[] singleTilePixelMap, int[] tileIndexes, String path) {
        super(singleTilePixelMap);
        this.tileIndexes = tileIndexes;
        this.setSingleTilePixelMap(this.getTilesFromSpreadsheet(getFullSpreadsheetPixelMap(path), tileIndexes));
    }

    public ObstacleTile(int[] tileIndexes, String path) {
        this(new int[(64*tileIndexes.length)], tileIndexes, path);
    }

    public ObstacleTile(String description, String path) {
        this(new int[1], path);
    }

    public ObstacleTile(String path) {
        this("Null Obstacle", path);
    }

    public int[] getTileIndexes() {
        return tileIndexes;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + Arrays.toString(tileIndexes);
    }
}
