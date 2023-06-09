package com.pelikh.games.map;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 64;
    public static final int TILE_HEIGHT_PIXELS = 64;
    public static final int NUMBER_OF_ROW_TILES = 60;
    public static final int NUMBER_OF_COLUMN_TILES = 60;

    private int[][] layout;

    public MapLayout() {
        initializeLayout();
    }

    private void initializeLayout() {
        layout = new int[][] {
                {2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,2,2,2,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                {2,2,0,0,3,0,2,2,2,2,1,1,1,1,1,1,4,1,1,1,1,1,1,0,0,0,1,1,1,0,0,1,1,1,1,2,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                {2,0,0,0,0,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,1,0,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,},
                {2,0,0,2,2,2,2,0,0,2,2,2,2,2,2,1,1,1,1,1,1,1,0,0,1,0,0,2,2,1,1,0,0,0,1,1,1,1,2,2,1,1,4,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,1,1,},
                {0,0,2,2,2,2,1,0,0,0,0,0,3,2,2,2,1,1,1,1,1,0,0,1,0,0,2,1,1,1,0,0,1,1,1,1,4,1,1,2,4,4,4,4,1,1,1,1,1,1,0,0,0,0,0,0,1,0,1,1,},
                {0,2,2,2,2,2,1,1,1,1,1,1,0,2,2,2,1,1,1,0,0,1,1,4,0,2,1,1,1,0,1,1,1,2,2,2,2,2,2,2,3,0,4,4,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,},
                {0,0,2,2,1,1,1,1,1,1,1,4,1,1,1,1,1,1,0,0,1,1,1,1,0,2,1,1,0,1,1,2,2,2,2,2,2,2,1,2,2,2,2,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                {0,0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,0,1,2,2,2,2,2,1,1,1,1,1,1,1,2,4,4,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,},
                {0,2,2,2,1,1,1,1,1,4,1,1,1,1,2,1,0,0,1,1,4,1,1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,},
                {0,2,2,2,1,1,4,1,1,1,1,1,1,3,2,0,0,1,1,1,1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,4,1,1,1,2,1,1,1,0,0,0,1,2,2,2,1,1,1,1,1,1,},
                {0,2,2,2,1,1,1,1,1,1,1,1,1,2,0,0,1,1,1,4,4,4,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,4,4,1,1,1,1,1,1,1,0,0,0,2,2,2,1,1,1,1,1,1,4,},
                {0,2,2,1,1,1,1,4,1,1,1,1,2,2,0,1,1,1,1,4,4,4,1,1,2,2,2,2,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,2,2,1,1,1,1,1,1,4,4,},
                {0,1,1,1,1,1,0,0,0,1,1,2,2,1,0,1,1,1,1,4,4,4,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,4,1,0,2,2,2,1,1,4,1,1,1,4,1,},
                {0,1,1,3,3,1,0,0,0,1,2,2,1,1,1,1,4,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,2,2,1,1,0,0,4,1,1,1,4,1,},
                {1,1,1,1,1,1,0,0,0,2,2,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,4,1,1,0,1,1,1,0,1,1,1,1,1,1,2,2,2,1,1,0,0,1,0,1,1,4,4,},
                {1,1,1,1,1,1,0,0,0,1,1,1,1,1,4,4,1,4,0,0,0,1,1,1,0,1,1,1,1,1,0,0,1,4,1,0,0,1,1,1,0,0,1,1,1,1,2,2,2,1,1,4,1,0,1,0,1,1,4,4,},
                {1,1,1,1,1,1,0,0,2,1,1,1,1,4,4,4,1,1,0,1,0,0,1,1,0,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,0,0,1,1,1,2,2,1,0,1,4,1,0,0,0,1,1,4,4,},
                {1,1,1,1,1,1,3,2,2,1,1,1,1,4,4,1,1,1,0,1,1,0,1,1,0,2,2,1,1,1,1,1,1,4,4,1,1,1,1,1,3,1,0,1,2,2,2,2,1,0,1,4,1,1,0,0,1,1,4,4,},
                {1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,3,3,0,0,1,0,0,2,2,2,2,1,1,1,1,1,4,4,1,1,1,1,2,2,2,2,2,2,2,2,1,0,1,4,1,1,1,0,0,1,1,4,},
                {1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,3,0,0,1,1,0,4,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,2,2,2,0,1,1,0,4,4,1,1,0,0,1,1,1,},
                {1,1,1,1,1,1,2,4,2,2,2,1,1,1,1,1,1,1,0,0,1,1,1,0,1,2,2,2,2,2,2,4,1,1,1,1,1,1,1,1,1,1,1,4,2,2,0,0,0,1,1,1,4,1,1,0,0,1,1,1,},
                {1,1,2,1,1,1,2,2,2,2,2,2,1,1,1,0,1,1,0,0,1,1,1,0,1,2,2,2,2,2,2,2,2,1,1,0,1,1,1,1,1,1,1,1,1,2,2,0,0,1,1,1,4,1,1,1,0,1,1,1,},
                {1,1,2,1,1,1,1,1,1,2,2,2,1,1,0,0,4,4,1,0,1,1,1,0,0,2,2,2,2,2,2,2,2,1,1,0,0,1,1,1,0,0,0,1,1,1,2,2,0,0,1,1,4,1,1,1,0,1,1,1,},
                {1,2,1,1,1,1,1,1,1,1,2,2,1,1,0,0,1,4,4,4,0,1,1,1,0,1,2,2,2,2,2,2,2,2,1,1,0,1,1,2,2,1,1,0,0,1,1,1,2,2,1,1,4,4,1,0,0,1,1,1,},
                {1,2,1,1,1,1,1,1,1,4,1,2,1,1,0,0,0,1,0,4,4,4,1,1,0,1,2,2,2,2,2,2,2,2,1,1,0,0,1,1,2,2,2,1,1,1,0,0,1,2,1,4,4,4,1,0,1,1,1,1,},
                {1,2,2,1,1,1,1,1,1,4,1,1,1,1,1,0,0,1,0,0,0,4,0,1,0,1,2,2,2,2,2,2,2,2,1,1,0,0,1,1,1,1,2,2,1,1,1,0,1,2,1,4,1,1,0,1,1,1,1,0,},
                {1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,1,0,1,1,3,1,2,2,2,2,2,2,2,1,0,0,1,1,1,4,1,2,2,1,1,1,0,1,2,1,4,1,1,0,1,1,1,1,0,},
                {1,1,2,1,1,1,1,1,3,1,1,1,1,1,1,0,0,1,0,4,0,1,0,0,1,1,1,2,2,2,2,2,2,2,1,0,1,1,1,1,1,2,2,1,1,0,0,4,2,4,1,4,1,1,0,1,4,1,1,0,},
                {1,1,2,2,1,1,1,2,2,2,2,1,1,1,1,0,1,1,0,1,0,0,1,0,1,1,1,1,2,2,2,2,2,2,1,0,1,1,1,1,2,2,1,1,0,0,1,2,2,1,4,4,1,1,1,0,1,1,1,0,},
                {1,1,1,2,1,1,1,0,0,1,2,2,1,1,1,0,0,1,0,0,1,0,1,0,0,0,1,1,1,1,2,2,2,2,1,1,1,1,1,2,2,1,1,0,0,1,1,2,1,1,1,1,1,1,1,0,0,2,1,0,},
                {1,1,1,2,1,2,1,0,0,1,1,1,2,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,2,2,2,2,3,1,1,1,2,2,1,0,0,0,1,3,2,2,1,1,1,1,1,1,1,1,0,0,1,0,},
                {1,1,1,2,1,2,1,0,0,1,4,1,2,1,1,1,0,0,0,0,0,0,0,0,0,0,3,1,0,0,2,2,2,2,1,1,1,1,2,1,0,0,1,1,1,2,2,1,1,1,1,4,4,4,4,4,4,0,1,0,},
                {4,0,0,1,1,2,1,0,0,1,1,1,2,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,2,2,2,1,1,1,1,1,1,0,1,1,1,2,2,1,1,1,4,4,0,0,0,1,1,1,0,1,0,},
                {1,0,1,1,1,2,1,1,1,0,0,1,2,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,2,2,2,1,1,1,1,1,1,0,0,1,1,1,1,1,4,4,1,0,0,0,0,1,1,0,0,1,0,},
                {0,0,1,1,3,2,1,1,3,1,0,0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,0,1,1,1,1,4,4,1,1,3,0,0,0,1,0,0,1,1,0,},
                {0,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,2,2,2,1,1,2,1,1,1,1,4,0,0,1,1,4,4,1,1,1,2,0,1,1,1,1,1,1,1,1,},
                {0,0,1,1,2,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,4,1,1,1,1,1,2,2,2,1,1,1,2,1,1,1,1,1,1,1,4,4,1,1,1,1,2,1,1,1,1,1,1,1,1,1,},
                {1,0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,4,4,4,4,4,1,1,1,1,2,2,1,1,1,1,2,2,1,1,1,1,1,1,4,1,1,1,1,1,2,1,1,1,1,1,1,1,4,1,},
                {1,1,1,2,2,1,1,2,2,2,2,2,2,2,2,1,1,1,4,1,0,0,4,4,1,1,1,1,1,2,2,2,1,1,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,},
                {1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,4,4,4,4,0,1,1,1,1,1,2,2,2,2,1,4,4,0,1,4,4,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,4,1,1,1,1,1,},
                {1,1,3,1,2,3,1,1,1,1,1,1,1,1,1,2,1,1,0,0,1,1,0,1,1,1,1,3,2,2,2,1,1,4,0,0,0,1,1,1,1,1,1,1,1,1,1,4,1,1,1,2,1,1,1,1,1,1,1,1,},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,0,0,1,1,0,1,1,1,1,1,2,2,2,1,1,1,4,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,},
                {4,1,1,1,1,2,2,1,4,1,1,1,1,4,1,0,2,2,1,1,1,0,1,1,1,1,2,2,2,2,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,},
                {1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,4,1,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,1,},
                {1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,1,3,1,1,0,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,4,4,1,1,2,1,1,1,2,1,1,1,1,},
                {1,3,1,2,1,1,4,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,},
                {1,1,2,2,1,1,1,1,1,1,2,2,2,2,1,1,0,0,1,1,1,0,0,2,2,1,1,1,1,1,1,1,3,1,1,1,1,2,2,2,2,2,2,2,2,2,1,1,1,1,1,2,1,1,1,2,1,1,1,1,},
                {1,1,2,1,1,1,1,1,2,2,0,0,0,1,1,1,2,2,0,1,1,0,1,1,1,1,1,1,4,4,1,1,1,1,1,1,2,2,1,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,2,1,1,1,1,},
                {2,1,1,1,1,1,1,2,2,1,0,1,0,0,1,1,1,2,2,0,0,0,0,1,1,1,1,1,4,4,4,1,1,1,1,2,2,1,1,1,1,0,0,1,1,2,2,1,1,1,1,1,2,1,1,2,1,1,1,1,},
                {2,1,1,1,1,1,2,1,0,0,0,1,1,0,1,1,1,1,2,2,0,0,0,0,0,0,1,1,4,1,4,4,4,4,1,2,1,1,4,1,0,0,0,0,0,1,2,2,1,1,1,1,2,1,2,2,1,1,1,1,},
                {2,1,1,1,1,1,2,1,0,1,1,1,1,0,1,1,1,1,1,2,1,1,1,0,1,0,0,0,4,1,1,1,1,1,1,2,1,1,1,0,0,0,0,0,0,1,1,2,2,2,1,1,2,1,2,1,1,1,1,1,},
                {2,2,1,1,1,1,1,1,0,1,3,1,1,0,0,4,1,1,0,0,2,1,1,0,0,0,0,0,1,1,1,1,1,1,1,2,2,1,1,0,0,0,1,0,0,1,1,1,1,2,2,1,2,2,2,1,1,1,1,1,},
                {1,1,1,4,1,1,2,2,0,0,1,1,1,1,0,0,0,0,0,0,2,1,1,1,1,1,0,0,0,1,1,1,1,4,1,2,2,1,0,0,0,1,4,0,0,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,},
                {1,1,4,1,1,1,1,2,1,0,1,1,1,1,1,1,0,0,0,0,2,1,1,1,1,1,1,0,1,1,1,1,1,2,2,2,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,},
                {1,1,4,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,0,0,2,1,1,1,1,1,1,1,0,1,1,1,2,2,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,2,1,1,1,},
                {1,1,1,1,1,1,1,1,2,1,1,1,1,4,1,1,1,1,0,0,1,1,1,1,3,1,1,1,0,1,1,2,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,2,1,1,},
                {1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,0,0,0,1,1,2,1,1,},
                {1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,0,0,0,4,4,1,2,1,1,},
                {1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,2,2,2,1,1,1,1,1,1,1,1,4,4,4,1,1,3,1,1,1,1,1,1,1,2,2,1,1,1,0,0,1,1,1,1,1,1,1,},
                {1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,4,4,4,4,4,4,4,4,4,4,2,1,1,1,1,1,1,1,4,4,4,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,1}
        };
    }

    public int[][] getLayout() {
        return layout;
    }
}
