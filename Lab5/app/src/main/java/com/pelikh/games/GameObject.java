package com.pelikh.games;

import android.graphics.Canvas;

public abstract class GameObject {
    protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;

    public GameObject(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    protected static double getDistanceBetweenObjects(GameObject object1, GameObject object2) {
        return Math.sqrt(
                Math.pow(object2.getPositionX() - object1.getPositionX(), 2) +
                Math.pow(object2.getPositionY() - object1.getPositionY(), 2)
        );
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    protected double getPositionX() {
        return positionX;
    }

    protected double getPositionY() {
        return positionY;
    }
}
