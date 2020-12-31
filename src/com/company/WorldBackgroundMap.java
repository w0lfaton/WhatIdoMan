package com.company;

import java.util.List;
import java.util.Map;

//is this Final? Do I need to make it immutable?
public class WorldBackgroundMap extends BackgroundMap {

    public WorldBackgroundMap(String worldDescription, Map<Integer, BackgroundTile> backgroundTiles, int[] worldBackgroundPixelMap, int mapHeight, int mapWidth) {
        super(worldDescription, backgroundTiles, worldBackgroundPixelMap, mapHeight, mapWidth);
    }

    public WorldBackgroundMap(String worldDescription, Map<Integer, BackgroundTile> backgroundTiles) {
        super(worldDescription, backgroundTiles);
    }

    public WorldBackgroundMap(String worldDescription) {
        super(worldDescription);
    }

    public int[] getWorldBackgroundPixelMap(List<Entity> entityList, List<Obstacle> obstacleList) {
        if (this.getBackgroundPixelMap() != null) {
            for (Entity entity : entityList) {
                addEntity(entity.getXPosition(),entity.getYPosition(),entity);
            }
            for (Obstacle obstacle : obstacleList) {
                addObstacle(obstacle.getXStartPosition(),obstacle.getYStartPosition(), obstacle);
            }
            return this.getBackgroundPixelMap();
        } else {
            return null;
        }
    }

    private void addTileToPixelMap(int xPos, int yPos, Tile tile) {
        int yPosCounter = yPos;
        int xPosCounter = xPos;
        String xyCord = xPos + "," + yPos;
        int pixelCounter = 0;
        main:
        for (int y = 0; y < tile.getTileHeight(); y++) {
            for (int x = 0; x < tile.getTileWidth(); x++) {
                if (yPosCounter < 0) {
                    int pixelsIn = Math.abs(yPosCounter);
                    pixelCounter+= tile.getTileWidth() * (pixelsIn);
                    if (pixelCounter >= (tile.getTileWidth() * tile.getTileHeight())) {
                        break main;
                    }
                    yPosCounter = 0;
                    y+= pixelsIn;
                }
                int screenCounter = (yPosCounter * getMapWidth()) + xPosCounter;

                //TODO: rework this logic as the IF statement doesn't work currently.
                if (tile.getSingleTilePixelMap()[pixelCounter] != 0xffff0066) {
                    /*if (tile.isSolid) {
                        screenSolidmap[screenCounter] = 1;
                    }
                    if (tile.isCollectible) {
                        screenCollectiblemap[screenCounter] = 1;
                        screenCollectibleMap.put(screenCounter, xyCord);
                    }*/
                    this.getBackgroundPixelMap()[screenCounter] = tile.getSingleTilePixelMap()[pixelCounter];
                }
                xPosCounter++;
                pixelCounter++;
            }
            xPosCounter -= tile.getTileWidth();
            yPosCounter++;
        }
    }

    private void addEntity(int xPos, int yPos, Entity entity) {
        int yPosCounter = yPos;
        int xPosCounter = xPos;
        int pixelCounter = 0;
        for (int y = 0; y < entity.getEntityTile().getTileHeight(); y++) {
            for (int x = 0; x < entity.getEntityTile().getTileWidth(); x++) {
                int screenCounter = (yPosCounter * this.getMapWidth()) + xPosCounter;
                if (entity.getEntityTile().getSingleTilePixelMap()[pixelCounter] != 0xffff0066) {
                    this.getBackgroundPixelMap()[screenCounter] = entity.getEntityTile().getSingleTilePixelMap()[pixelCounter];
                }
                xPosCounter++;
                pixelCounter++;
            }
            xPosCounter -= entity.getEntityTile().getTileWidth();
            yPosCounter++;
        }
    }

    private void addObstacle(int xPos, int yPos, Obstacle obstacle) {
        int yPosCounter = yPos;
        int xPosCounter = xPos;
        int pixelCounter = 0;
        for (int y = 0; y < obstacle.getObstacleTile().getTileHeight(); y++) {
            for (int x = 0; x < obstacle.getObstacleTile().getTileWidth(); x++) {
                int screenCounter = (yPosCounter * this.getMapWidth()) + xPosCounter;
                if (obstacle.getObstacleTile().getSingleTilePixelMap()[pixelCounter] != 0xffff0066) {
                    this.getBackgroundPixelMap()[screenCounter] = obstacle.getObstacleTile().getSingleTilePixelMap()[pixelCounter];
                }
                xPosCounter++;
                pixelCounter++;
            }
            xPosCounter -= obstacle.getObstacleTile().getTileWidth();
            yPosCounter++;
        }
    }

}
