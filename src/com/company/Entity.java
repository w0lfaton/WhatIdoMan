package com.company;

import java.util.List;

public class Entity {
    private EntityTile entityTile;
    private static int INSTANCE_COUNT = 0;
    private final int ID;
    private final String name;
    private final String type;
    private int health;
    private int xPosition;
    private int yPosition;

    public Entity(EntityTile entityTile, String name, String type, int health, int xPosition, int yPosition) {
        this.entityTile = entityTile;
        this.name = name;
        this.type = type;
        this.health = health;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        INSTANCE_COUNT++;
        this.ID = INSTANCE_COUNT;
    }

    public Entity(EntityTile entityTile, String name, String type) {
        this(entityTile,name,type,100,0,0);
    }

    public EntityTile getEntityTile() {
        return entityTile;
    }

    public void setEntityTile(EntityTile entityTile) {
        this.entityTile = entityTile;
    }

    public static int getInstanceCount() {
        return INSTANCE_COUNT;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public boolean equals(Object obj) {
        //Copy check
        if(this == obj) {
            return true;
        }
        if (obj instanceof Entity) {
            Entity theObject = (Entity) obj;
            // If it's the same class, then check if the name is correct
            if (this.name.equals(theObject.getName())) {
                // Name is correct so check and return if bodyTypes are same.
                return this.type.equals(theObject.getType());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31 + this.type.hashCode();
    }

    @Override
    public String toString() {
        return "Entity{:"+ID
                +":"
                +name
                +":"
                +type
                +":"
                +health
                +":"
                +xPosition
                +":"
                +yPosition
                +":"
                +entityTile.toString()
                +"}";
    }
}
