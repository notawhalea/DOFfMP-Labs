package com.pelikh.games.map;

import static com.pelikh.games.map.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.pelikh.games.map.MapLayout.NUMBER_OF_ROW_TILES;
import static com.pelikh.games.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.pelikh.games.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.pelikh.games.GameDisplay;
import com.pelikh.games.graphics.SpriteBitmap;
import com.pelikh.games.tile.Tile;


public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteBitmap spriteBitmap;
    private Bitmap mapBitmap;

    public Tilemap(SpriteBitmap spriteBitmap) {
        mapLayout = new MapLayout();
        this.spriteBitmap = spriteBitmap;
        initializeTileMap();
    }

    private void initializeTileMap() {
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[NUMBER_OF_ROW_TILES][NUMBER_OF_COLUMN_TILES];

        for (int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++) {
                tilemap[iRow][iCol] = Tile.getTile(
                    layout[iRow][iCol],
                    spriteBitmap,
                    getRectByIndex(iRow, iCol)
                );
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
          NUMBER_OF_COLUMN_TILES * TILE_WIDTH_PIXELS,
              NUMBER_OF_ROW_TILES * TILE_HEIGHT_PIXELS,
              config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++) {
                tilemap[iRow][iCol].draw(mapCanvas);
            }
        }

    }

    private Rect getRectByIndex(int iRow, int iCol) {
        return new Rect(
                iCol * TILE_HEIGHT_PIXELS,
                iRow * TILE_HEIGHT_PIXELS,
                (iCol + 1)*TILE_WIDTH_PIXELS,
                (iRow + 1)*TILE_HEIGHT_PIXELS
        );
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(
                mapBitmap,
                gameDisplay.getGameRect(),
                gameDisplay.DISPLAY_RECT,
                null
        );
    }
}
