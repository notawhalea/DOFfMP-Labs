package com.pelikh.games.tile;


import android.graphics.Canvas;
import android.graphics.Rect;

import com.pelikh.games.graphics.SpriteBitmap;

public abstract class Tile {

    protected final Rect mapLocation;

    public Tile(Rect mapLocationRect) {
        this.mapLocation = mapLocationRect;
    }

    public enum TileType {
        WATER_TILE,
        LAVA_TILE,
        GROUND_TILE,
        GRASS_TILE,
        TREE_TILE
    }

    public static Tile getTile(int idTileType, SpriteBitmap spriteBitmap, Rect mapLocationRect) {
        switch(TileType.values()[idTileType]) {

            case WATER_TILE:
                return new WaterTile(spriteBitmap, mapLocationRect);
            case LAVA_TILE:
                return new LavaTile(spriteBitmap, mapLocationRect);
            case GROUND_TILE:
                return new GroundTile(spriteBitmap, mapLocationRect);
            case GRASS_TILE:
                return new GrassTile(spriteBitmap, mapLocationRect);
            case TREE_TILE:
                return new TreeTile(spriteBitmap, mapLocationRect);
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
