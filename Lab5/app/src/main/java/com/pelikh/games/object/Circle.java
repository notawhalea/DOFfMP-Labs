package com.pelikh.games.object;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.pelikh.games.GameDisplay;
import com.pelikh.games.map.BlockType;

public abstract class Circle extends GameObject {
    protected double radius;

    public BlockType blockType;

    public BlockType getBlockType() {
        return blockType;
    }

    public static double lengthCollision;
    protected Paint paint;
    public Circle(int color, double positionX, double positionY, double radius, int MAX_HEALTH_POINTS) {
        super(positionX, positionY, MAX_HEALTH_POINTS);
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
        if (distance < distanceToCollision) {
            lengthCollision = distanceToCollision - distance;
            return true;
        }
        //return distance < distanceToCollision;
        return false;
    }

    private double getRadius() {
        return radius;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawCircle(
                (float)gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float)gameDisplay.gameToDisplayCoordinatesY(positionY),
                (float)radius,
                paint
        );
    }
}
