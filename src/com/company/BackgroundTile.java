package com.company;

public class BackgroundTile extends Tile {
    private int xStartPosition;
    private int yStartPosition;
    private int[] tileIndexes;

    public BackgroundTile(int[] singleTilePixelMap, int[] tileIndexes) {
        super(singleTilePixelMap);
        this.tileIndexes = tileIndexes;
        // TODO: Isn't correct with 3,6,9 tile dimension - needs fixing.
        int dimension = (int) Math.sqrt(64*tileIndexes.length);
        this.setTileWidth(dimension);
        this.setTileHeight(dimension);
    }

    public BackgroundTile(int[] tileIndexes, String path) {
        this(new int[(64*tileIndexes.length)], tileIndexes);
        this.setSingleTilePixelMap(this.getTilesFromSpreadsheet(this.getFullSpreadsheetPixelMap(path), tileIndexes));
    }

    public BackgroundTile(int[] tileIndexes) {
        this(new int[(64*tileIndexes.length)], tileIndexes);
    }

    public BackgroundTile() {
        this(new int[]{0});
    }

    public int[] getTileIndexes() {
        return tileIndexes;
    }

    public int getxStartPosition() {
        return xStartPosition;
    }

    public void setxStartPosition(int xStartPosition) {
        this.xStartPosition = xStartPosition;
    }

    public int getyStartPosition() {
        return yStartPosition;
    }

    public void setyStartPosition(int yStartPosition) {
        this.yStartPosition = yStartPosition;
    }

    public BackgroundTile getNewInstance() {
        return new BackgroundTile(this.getSingleTilePixelMap(),this.getTileIndexes());
    }
}
