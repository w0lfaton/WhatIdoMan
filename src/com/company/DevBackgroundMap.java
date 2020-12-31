package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DevBackgroundMap extends BackgroundMap {
    private BackgroundTile grassPathwayUD = new BackgroundTile(new int[]{18, 19, 50, 51}, getGeneralPath());
    private BackgroundTile grassPathwayDLU = new BackgroundTile(new int[]{16, 17, 48, 49}, getGeneralPath());
    private BackgroundTile grassPathwayPLUS = new BackgroundTile(new int[]{20, 21, 52, 53}, getGeneralPath());
    private BackgroundTile grassPathwayX = new BackgroundTile(new int[]{22, 23, 54, 55}, getGeneralPath());
    private BackgroundTile grassPathway3LUR = new BackgroundTile(new int[]{24, 25, 56, 57}, getGeneralPath());
    private BackgroundTile grassPathway2UL = new BackgroundTile(new int[]{26, 27, 58, 59}, getGeneralPath());
    private BackgroundTile grassPathwayCDL = new BackgroundTile(new int[]{28, 29, 60, 61}, getGeneralPath());
    private BackgroundTile grassPathwaySPOT = new BackgroundTile(new int[]{30, 31, 62, 63}, getGeneralPath());
    private BackgroundTile clayPathway = new BackgroundTile(new int[]{15}, getGeneralPath());
    private BackgroundTile toolbarBackground = new BackgroundTile(new int[]{47,47,47,47}, getGeneralPath());
    private BackgroundTile toolbarEdge1U = new BackgroundTile(new int[]{46}, getGeneralPath());
    private BackgroundTile toolbarEdge2U = new BackgroundTile(new int[]{45}, getGeneralPath());
    private BackgroundTile toolbarEdge1R = (BackgroundTile) new BackgroundTile(new int[]{46}, getGeneralPath()).tileRotate("right");
    private BackgroundTile toolbarEdge2R = (BackgroundTile) new BackgroundTile(new int[]{45}, getGeneralPath()).tileRotate("right");
    private BackgroundTile toolbarEdge1L = (BackgroundTile) new BackgroundTile(new int[]{46}, getGeneralPath()).tileRotate("left");
    private BackgroundTile toolbarEdge2L = (BackgroundTile) new BackgroundTile(new int[]{45}, getGeneralPath()).tileRotate("left");
    private BackgroundTile toolbarEdge1D = (BackgroundTile) new BackgroundTile(new int[]{46}, getGeneralPath()).tileRotate("right").tileRotate("right");
    private BackgroundTile toolbarEdge2D = (BackgroundTile) new BackgroundTile(new int[]{45}, getGeneralPath()).tileRotate("right").tileRotate("right");
    private BackgroundTile toolbarCorner1UR = new BackgroundTile(new int[]{14}, getGeneralPath());
    private BackgroundTile toolbarCorner2UR = new BackgroundTile(new int[]{13}, getGeneralPath());
    private BackgroundTile toolbarCorner1DR = (BackgroundTile) new BackgroundTile(new int[]{14}, getGeneralPath()).tileRotate("right");
    private BackgroundTile toolbarCorner2DR = (BackgroundTile) new BackgroundTile(new int[]{13}, getGeneralPath()).tileRotate("right");
    private BackgroundTile toolbarCorner1DL = (BackgroundTile) new BackgroundTile(new int[]{14}, getGeneralPath()).tileRotate("right").tileRotate("right");
    private BackgroundTile toolbarCorner2DL = (BackgroundTile) new BackgroundTile(new int[]{13}, getGeneralPath()).tileRotate("right").tileRotate("right");
    private BackgroundTile toolbarCorner1UL = (BackgroundTile) new BackgroundTile(new int[]{14}, getGeneralPath()).tileRotate("left");
    private BackgroundTile toolbarCorner2UL = (BackgroundTile) new BackgroundTile(new int[]{13}, getGeneralPath()).tileRotate("left");

    private int[] displayPixelMap;
    private int[] fullPixelMapData;
    private int selectionIndex = 0;
    private int inputXModifier = 0;
    private int inputYModifier = 0;

    private List<Entity> entityList = new ArrayList<>();
    private List<Obstacle> obstacleList = new ArrayList<>();
    private List<BackgroundTile> selectionTileList = new LinkedList<>();


    public DevBackgroundMap(String description, Map<Integer, BackgroundTile> backgroundTiles, int[] backgroundPixelMap, int mapHeight, int mapWidth) {
        super(description, backgroundTiles, backgroundPixelMap, mapHeight, mapWidth);
        init();
    }

    public DevBackgroundMap(String description, Map<Integer, BackgroundTile> backgroundTiles) {
        super(description, backgroundTiles);
        init();
    }

    public DevBackgroundMap(String description) {
        super(description);
        init();
    }

    public int[] getDisplayPixelMap() {
        return displayPixelMap;
    }

    public int getInputXModifier() {
        return inputXModifier;
    }

    public void setInputXModifier(int inputXModifier) {
        this.inputXModifier = inputXModifier;
    }

    public int getInputYModifier() {
        return inputYModifier;
    }

    public void setInputYModifier(int inputYModifier) {
        this.inputYModifier = inputYModifier;
    }

    public void init() {
        displayPixelMap = getBackgroundPixelMap();
        toolbarEdge1U.doublePixelMap();
        toolbarEdge2U.doublePixelMap();
        toolbarEdge1R.doublePixelMap();
        toolbarEdge2R.doublePixelMap();
        toolbarEdge1D.doublePixelMap();
        toolbarEdge2D.doublePixelMap();
        toolbarEdge1L.doublePixelMap();
        toolbarEdge2L.doublePixelMap();
        toolbarCorner1UR.doublePixelMap();
        toolbarCorner2UR.doublePixelMap();
        toolbarCorner1DR.doublePixelMap();
        toolbarCorner2DR.doublePixelMap();
        toolbarCorner1UL.doublePixelMap();
        toolbarCorner2UL.doublePixelMap();
        toolbarCorner1DL.doublePixelMap();
        toolbarCorner2DL.doublePixelMap();
        grassPathwayUD.doublePixelMap();
        grassPathwayDLU.doublePixelMap();
        grassPathwayPLUS.doublePixelMap();
        grassPathwayX.doublePixelMap();
        grassPathway3LUR.doublePixelMap();
        grassPathway2UL.doublePixelMap();
        grassPathwayCDL.doublePixelMap();
        grassPathwaySPOT.doublePixelMap();
        clayPathway.doublePixelMap();
        clayPathway.doublePixelMap();

        selectionTileList.add(grassPathwayUD);
        selectionTileList.add(grassPathwayDLU);
        selectionTileList.add(grassPathwayPLUS);
        selectionTileList.add(grassPathwayX);
        selectionTileList.add(grassPathway3LUR);
        selectionTileList.add(grassPathway2UL);
        selectionTileList.add(grassPathwayCDL);
        selectionTileList.add(grassPathwaySPOT);
        selectionTileList.add(clayPathway);
    }

    public void updateUI() {
        this.setBackgroundPixelMap(displayPixelMap.clone());
        addToolbarBox();
        addToolbarTiles();
    }

    public int getSelectionIndex() {
        return selectionIndex;
    }

    public boolean incrementSelectionIndex() {
        if ((selectionIndex+1) != selectionTileList.size()) {
            selectionIndex++;
            return true;
        } else {
            System.out.println("Reached the end of the tile selection list");
            return false;
        }
    }

    public boolean decrementSelectionIndex() {
        if (selectionIndex != 0) {
            selectionIndex--;
            return true;
        } else {
            System.out.println("Reached the start of the tile selection list");
            return false;
        }
    }

    public boolean rotateHoverTile() {
        selectionTileList.set(selectionIndex, (BackgroundTile) selectionTileList.get(selectionIndex).tileRotate("right"));
        return true;
    }

    public boolean drawTileToMap(int xPos, int yPos) {
        addHoverTile(xPos, yPos, true);
        displayPixelMap = this.getBackgroundPixelMap().clone();
        return true;
    }

    public void addHoverTile(int xPos, int yPos, boolean saveToPixelMap) {
        //Check that the mouse coordinates stay within screen bounds - saves us running into an "array out of bounds" exception
        if (yPos > getMapHeight()) {
            yPos = getMapHeight();
        } else if (yPos < 0) {
            yPos = 0;
        }
        if (xPos > getMapWidth()) {
            xPos = getMapWidth();
        } else if (xPos < 0) {
            xPos = 0;
        }
        //Create var for storing new potential mouse coordinates - setting them at current mouse coordinates to reduce code
        int new_x;
        int new_y;
        //We want to lock the hover tile into it's tile for all cases except for the pineapple.
        new_x = (int) Math.floor((double) xPos/32)*32;
        new_y = (int) Math.floor((double) yPos/32)*32;
        if (!saveToPixelMap) {
            addTileToPixelMap(new_x, new_y, selectionTileList.get(selectionIndex), false, 0, 0);
        } else {
            addTileToPixelMap(new_x, new_y, selectionTileList.get(selectionIndex), true, getInputXModifier(), getInputYModifier());
        }
    }

    private void addToolbarBox() {
        int xAxisRange = getMapWidth()/16 - 1;
        int yAxisRange = 2 + (int) Math.floor(Math.sqrt((double) (getMapHeight()/16)));
        int weirdVal = 38;
        for (int y = 0; y < yAxisRange;y++) {
            for (int x = 0; x < xAxisRange; x++) {
                int yDif = y * 16 + getMapHeight()-((yAxisRange*16)+weirdVal);
                int xDif = x *16;
                if (y == 0 && x == 0) {
                    addTileToPixelMap(xDif,yDif, toolbarCorner1UL, false, 0, 0);
                } else if (y == 0 && x < xAxisRange-1) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge1U, false, 0, 0);
                } else if (y == 0) {
                    addTileToPixelMap(xDif,yDif, toolbarCorner1UR, false, 0, 0);
                } else if (y == 1 && x == 0) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge1L, false, 0, 0);
                } else if (y == 1 && x == 1) {
                    addTileToPixelMap(xDif,yDif, toolbarCorner2UL, false, 0, 0);
                } else if (y == 1 && x < xAxisRange-2) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge2U, false, 0, 0);
                } else if (y == 1  && x == xAxisRange-2) {
                    addTileToPixelMap(xDif,yDif, toolbarCorner2UR, false, 0, 0);
                } else if (y == 1) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge1R, false, 0, 0);
                } else if (y < yAxisRange-2 && x == 0) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge1L, false, 0, 0);
                } else if (y < yAxisRange-2 && x == 1) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge2L, false, 0, 0);
                } else if (y < yAxisRange-2 && x == xAxisRange-2) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge2R, false, 0, 0);
                } else if (y < yAxisRange-2 && x == xAxisRange-1) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge1R, false, 0, 0);
                } else if (y == yAxisRange-2 && x == 0) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge1L, false, 0, 0);
                } else if (y == yAxisRange-2 && x == 1) {
                    addTileToPixelMap(xDif,yDif, toolbarCorner2DL, false, 0, 0);
                } else if (y == yAxisRange-2 && x < xAxisRange-2) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge2D, false, 0, 0);
                } else if (y == yAxisRange-2 && x == xAxisRange-2) {
                    addTileToPixelMap(xDif,yDif, toolbarCorner2DR, false, 0, 0);
                } else if (y == yAxisRange-2) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge1R, false, 0, 0);
                } else if (y == yAxisRange-1 && x == 0) {
                    addTileToPixelMap(xDif,yDif, toolbarCorner1DL, false, 0, 0);
                } else if (y == yAxisRange-1 && x < xAxisRange-1) {
                    addTileToPixelMap(xDif,yDif, toolbarEdge1D, false, 0, 0);
                } else if (y == yAxisRange-1) {
                    addTileToPixelMap(xDif,yDif, toolbarCorner1DR, false, 0, 0);
                } else {
                    addTileToPixelMap(xDif, yDif, toolbarBackground, false, 0, 0);
                }
            }
        }
    }

    private void addToolbarTiles() {
        int xStartPos = toolbarCorner2UL.getxStartPosition() + 16;
        int yStartPos = toolbarCorner2UL.getyStartPosition() + 16;
        for (int i = 0; i < selectionTileList.size(); i++) {
            addTileToPixelMap(xStartPos + (i * 40),yStartPos, selectionTileList.get(i), false, 0,0);
        }
    }

    private void addButton() {

    }

    private void addTileToPixelMap(int xPos, int yPos, Tile tile, boolean saveToFullMap, int inputXModifier, int inputYModifier) {
        int yPosCounter = yPos;
        int xPosCounter = xPos;
        int pixelCounter = 0;
        main:
        for (int y = 0; y < tile.getTileHeight(); y++) {
            for (int x = 0; x < tile.getTileWidth(); x++) {
                int modifiedYCounter = yPosCounter + inputYModifier;
                int modifiedXCounter = xPosCounter + inputXModifier;
                /*if (modifiedYCounter < 0) {
                    int pixelsIn = Math.abs(modifiedYCounter);
                    pixelCounter+= tile.getTileWidth() * (pixelsIn);
                    if (pixelCounter >= (tile.getTileWidth() * tile.getTileHeight())) {
                        break main;
                    }
                    modifiedYCounter = 0;
                    y+= pixelsIn;
                }*/
                int screenCounter = (yPosCounter * getMapWidth()) + xPosCounter;
                if (tile.getSingleTilePixelMap()[pixelCounter] != 0xffff0066) {
                    this.getBackgroundPixelMap()[screenCounter] = tile.getSingleTilePixelMap()[pixelCounter];
                    if (saveToFullMap) {
                        int fullPixelMapCounter = (modifiedYCounter * getMapWidth()) + modifiedXCounter;
                        this.fullPixelMapData[fullPixelMapCounter] = tile.getSingleTilePixelMap()[pixelCounter];
                    }
                }
                if ((x == 0 && y == 0) && tile instanceof BackgroundTile) {
                    ((BackgroundTile) tile).setxStartPosition(xPosCounter);
                    ((BackgroundTile) tile).setyStartPosition(yPosCounter);
                }
                xPosCounter++;
                pixelCounter++;
            }
            xPosCounter -= tile.getTileWidth();
            yPosCounter++;
        }
    }

    private boolean loadFullPixelMap() {
        try {
            MapData.getInstance().loadData("devMapData.txt");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
