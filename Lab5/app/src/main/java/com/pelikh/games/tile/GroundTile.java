package com.pelikh.games.tile;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.pelikh.games.graphics.Sprite;
import com.pelikh.games.graphics.SpriteBitmap;
import com.pelikh.games.tile.Tile;

public class GroundTile extends Tile {
    private final Sprite sprite;

    public GroundTile(SpriteBitmap spriteBitmap, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteBitmap.getGroundSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocation.left, mapLocation.top);
    }
}
