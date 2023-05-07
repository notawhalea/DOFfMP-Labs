package com.pelikh.games.tile;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.pelikh.games.graphics.Sprite;
import com.pelikh.games.graphics.SpriteBitmap;
import com.pelikh.games.tile.Tile;

public class TreeTile extends Tile {

    private final Sprite grassSprite;
    private final Sprite treeSprite;

    public TreeTile(SpriteBitmap spriteBitmap, Rect mapLocationRect) {
        super(mapLocationRect);
        grassSprite = spriteBitmap.getGrassSprite();
        treeSprite = spriteBitmap.getTreeSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.draw(canvas, mapLocation.left, mapLocation.top);
        treeSprite.draw(canvas, mapLocation.left, mapLocation.top);
    }
}
