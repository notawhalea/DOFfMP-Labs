package com.pelikh.games.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.pelikh.games.GameDisplay;
import com.pelikh.games.object.Circle;

public class DynamicObject extends Circle {

    public Rect rectObject;


    public DynamicObject(int color, double positionX, double positionY, double radius, int MAX_HEALTH_POINTS, BlockType blockType, Rect rect) {
        super(color, positionX, positionY, radius, MAX_HEALTH_POINTS);

        super.blockType = blockType;
        rectObject = rect;
    }

    @Override
    public void update() {

    }


    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {

        int x = (int)gameDisplay.gameToDisplayCoordinatesX(positionX - radius);
        int y = (int)gameDisplay.gameToDisplayCoordinatesY(positionY - radius);


        canvas.drawBitmap(
                DynamicMapObject.dynamicBitmap,
                rectObject,
                new Rect(
                        x,
                        y,
                        x + 64,
                        y + 64),
                null
        );
    }
}
