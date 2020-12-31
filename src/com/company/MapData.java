package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapData implements DataInterface {
    private static MapData instance = new MapData();

    private List<Entity> entityList = new ArrayList<>();
    private List<Obstacle> obstacleList = new ArrayList<>();
    private int[] pixelMap;

    private MapData() {

    }

    public static MapData getInstance() {
        return instance;
    }

    public List<Entity> getEntityList() {
        return new ArrayList<>(this.entityList);
    }

    public List<Obstacle> getObstacleList() {
        return new ArrayList<>(this.obstacleList);
    }

    public int[] getPixelMap() {
        return pixelMap.clone();
    }

    public void setPixelMap(int[] pixelMap) {
        this.pixelMap = pixelMap;
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

    @Override
    public void loadData(String filename) throws IOException {
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");
            }
        } finally {
            if (br!=null) {
                br.close();
            }
        }
    }

    @Override
    public void saveData(String filename) throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Obstacle> obstacleIterator = obstacleList.iterator();
            bw.write("OBSTACLES:");
            bw.newLine();
            while (obstacleIterator.hasNext()) {
                Obstacle obstacle = obstacleIterator.next();
                bw.write(obstacle.toString());
                bw.newLine();
            }
            Iterator<Entity> entityIterator = entityList.iterator();
            bw.write("ENTITIES:");
            bw.newLine();
            while (entityIterator.hasNext()) {
                Entity entity = entityIterator.next();
                bw.write(entity.toString());
                bw.newLine();
            }
            bw.write("PIXELMAP:");
            bw.newLine();
            for (int i : this.pixelMap) {
                bw.write(i);
                bw.write(",");
            }
        } finally {
            if (bw!=null) {
                bw.close();
            }
        }
    }
}
