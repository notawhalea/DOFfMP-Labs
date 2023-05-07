package com.pelikh.games.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private final SpriteBitmap spriteBitmap;
    private final Rect rect;

    public Sprite(SpriteBitmap spriteBitmap, Rect rect) {
        this.spriteBitmap = spriteBitmap;
        this.rect = rect;
    }


    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteBitmap.getBitmap(),
                rect,
                new Rect(x,y,x+getWidth(),y+getHeight()),
                null
        );
    }

    public int getHeight() {
        return  rect.height();
    }

    public int getWidth() {
        return  rect.width();
    }
}
