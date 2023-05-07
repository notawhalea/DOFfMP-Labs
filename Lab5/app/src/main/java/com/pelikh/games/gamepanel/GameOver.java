package com.pelikh.games.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.pelikh.games.R;


public class GameOver {
    private Context context;

    public GameOver(Context context) {
        this.context = context;
    }
    public void draw(Canvas canvas) {
        String text = "Game Over";
        float x = 800;
        float y = 200;
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.gameOver));
        paint.setTextSize(150);


        canvas.drawText(text, x, y, paint);
    }
}
