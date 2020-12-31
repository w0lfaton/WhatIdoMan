package com.company;

import java.util.Arrays;

public class EntityTile extends Tile {
    private int[] tileIndexes;

    public EntityTile(int[] singleTilePixelMap, int[] tileIndexes, String path) {
        super(singleTilePixelMap);
        this.tileIndexes = tileIndexes;
        this.setSingleTilePixelMap(this.getTilesFromSpreadsheet(getFullSpreadsheetPixelMap(path), tileIndexes));
    }

    public EntityTile(int[] tileIndexes, String path) {
        this(new int[(64*tileIndexes.length)], tileIndexes, path);
    }

    public EntityTile(String description, String path) {
        this(new int[1], path);
    }

    public EntityTile(String path) {
        this("Null Entity", path);
    }

    public int[] getTileIndexes() {
        return tileIndexes;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + Arrays.toString(tileIndexes);
    }
}
