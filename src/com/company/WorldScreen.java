package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldScreen extends Screen {
    private WorldBackgroundMap worldBackgroundMap;
    private List<Entity> entityList = new ArrayList<>();
    private List<Obstacle> obstacleList = new ArrayList<>();

    public WorldScreen(String description, int windowWidth, int windowHeight, Entity player) {
        super(description, windowWidth, windowHeight);
        worldBackgroundMap = new WorldBackgroundMap(description, new HashMap<>(), new int[windowHeight*windowWidth], windowHeight, windowWidth);
        loadMapData();
        addEntityToList(player);
    }

    public WorldScreen(String description, Entity player) {
        this(description,800,800, player);
    }

    public WorldScreen(Entity player) {
        this("null", player);
    }

    public WorldBackgroundMap getWorldBackgroundMap() {
        return worldBackgroundMap;
    }

    public void setWorldBackgroundMap(WorldBackgroundMap worldBackgroundMap) {
        this.worldBackgroundMap = worldBackgroundMap;
    }

    public boolean addEntityToList(Entity entity) {
        if (entity != null) {
            this.entityList.add(entity);
            return true;
        } else {
            System.out.println("Null object");
            return false;
        }
    }

    public boolean updateEntity(Entity entity) {
        for (Entity checkEntity : entityList) {
            if (checkEntity.equals(entity)) {
                this.entityList.set(entityList.indexOf(checkEntity),entity);
                return true;
            }
        }
        return false;
    }

    public boolean addObstacleToList(Obstacle obstacle) {
        if (obstacle != null) {
            this.obstacleList.add(obstacle);
            return true;
        } else {
            System.out.println("Null object");
            return false;
        }
    }

    public boolean updateObstacle(Obstacle obstacle) {
        for (Obstacle checkObstacle : obstacleList) {
            if (checkObstacle.equals(obstacle)) {
                this.obstacleList.set(obstacleList.indexOf(checkObstacle),obstacle);
                return true;
            }
        }
        return false;
    }

    public boolean loadMapData() {
        if (MapData.getInstance().getEntityList() == null) {
            System.out.println("Map data instance has null lists.");
            return false;
        }
        this.entityList.addAll(MapData.getInstance().getEntityList());
        this.obstacleList.addAll(MapData.getInstance().getObstacleList());
        return true;
    }

    @Override
    public void paint(Graphics g) {
        this.getBufferedImage().setRGB(0,0,this.getBufferedImage().getWidth(),this.getBufferedImage().getHeight(),this.worldBackgroundMap.getWorldBackgroundPixelMap(entityList, obstacleList),0,this.getBufferedImage().getWidth());
        g.drawImage(this.getBufferedImage(),0,0,this);
    }
}
