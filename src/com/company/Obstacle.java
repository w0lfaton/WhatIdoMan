package com.company;

public class Obstacle {
    private ObstacleTile obstacleTile;
    private static int INSTANCE_COUNT = 0;
    private final int id;
    private final String name;
    private int xStartPosition;
    private int yStartPosition;
    private int width;
    private int height;
    private String description;

    public Obstacle(ObstacleTile obstacleTile, String name, int xStartPosition, int yStartPosition, String description) {
        this.obstacleTile = obstacleTile;
        this.xStartPosition = xStartPosition;
        this.yStartPosition = yStartPosition;
        this.name = name;
        this.description = description;
        INSTANCE_COUNT++;
        this.id = INSTANCE_COUNT;
    }

    public void setObstacleTile(ObstacleTile obstacleTile) {
        this.obstacleTile = obstacleTile;
    }

    public ObstacleTile getObstacleTile() {
        return obstacleTile;
    }

    public int getXStartPosition() {
        return xStartPosition;
    }

    public int getYStartPosition() {
        return yStartPosition;
    }

    public String getDescription() {
        return description;
    }

    public static int getInstanceCount() {
        return INSTANCE_COUNT;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if (obj instanceof Obstacle) {
            Obstacle theObject = (Obstacle) obj;
            // If it's the same class, then check if the name is correct
            if (this.name.equals(theObject.getName())) {
                // Name is correct so check and return if ID is the same.
                return this.id == theObject.getId();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Obstacle{:"+id
                +":"
                +name
                +":"
                +description
                +":"
                +xStartPosition
                +":"
                +yStartPosition
                +":"
                +obstacleTile.toString()
                +"}";
    }
}
