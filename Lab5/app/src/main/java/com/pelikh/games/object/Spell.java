package com.pelikh.games.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import com.pelikh.games.GameDisplay;
import com.pelikh.games.Gameloop;
import com.pelikh.games.R;

public class Spell extends Circle{

    public static final double SPEED_PIXELS_PER_SECOND = 800.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;
    private  Rect spellRect;
    private  Bitmap spell_sprite;


    private int frame_index;
    private final Circle spellcaster;

    Context context;

    public Spell(Context context, Circle spellcaster) {
        super(
                ContextCompat.getColor(context, R.color.spell),
                spellcaster.getPositionX() + spellcaster.getDirectionX() * 50,
                spellcaster.getPositionY() + spellcaster.getDirectionY() * 50,
                16,
                0
        );
        this.context = context;
        this.spellcaster = spellcaster;
        directionX = spellcaster.getDirectionX();
        directionY = spellcaster.getDirectionY();

        loadBitMap();
    }

    private void loadBitMap() {
        if (spellcaster instanceof Player)
            spellRect = new Rect(32,0,64,32);
        else if (spellcaster instanceof Mag)
            spellRect = new Rect(0,0,32,32);

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        spell_sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.spell, bitmapOptions);
    }

    @Override
    public void update() {
        positionX += directionX * MAX_SPEED;
        positionY += directionY * MAX_SPEED;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {

        int x = (int)gameDisplay.gameToDisplayCoordinatesX(positionX - radius);
        int y = (int)gameDisplay.gameToDisplayCoordinatesY(positionY - radius);

        canvas.drawBitmap(
                spell_sprite,
                spellRect,
                new Rect(
                        x,
                        y,
                        x + 32,
                        y + 32),
                null
        );
    }
}
