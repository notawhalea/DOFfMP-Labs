package com.pelikh.games;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public abstract class Circle extends GameObject {
    protected double radius;
    protected Paint paint;
    public Circle(int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }

    /**
     * isColliding check if two circle objects are colliding, based on their position and radii
     * @param object1
     * @param object2
     * @return
     */
    public static boolean isColliding(Circle object1, Circle object2) {
        double distance = getDistanceBetweenObjects(object1, object2);
        double distanceToCollision = object1.getRadius() + object2.getRadius();
        return distance < distanceToCollision;
    }

    private double getRadius() {
        return radius;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
    }
}
