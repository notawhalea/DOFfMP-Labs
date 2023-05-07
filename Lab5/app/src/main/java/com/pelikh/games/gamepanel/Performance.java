package com.pelikh.games.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.TextView;


import androidx.core.content.ContextCompat;

import com.pelikh.games.Game;
import com.pelikh.games.Gameloop;
import com.pelikh.games.R;
import com.pelikh.games.object.Player;

public class Performance{
    private Gameloop gameLoop;
    private Context context;

    public Performance(Context context, Gameloop gameloop) {
        this.context = context;
        this.gameLoop = gameloop;
    }

    public void draw(Canvas canvas) {
        //drawUPS(canvas);
        //drawFPS(canvas);
        drawCountKills(canvas);
        drawCountEnemies(canvas);
    }

    public void drawCountKills(Canvas canvas) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/Laura.ttf");
        String kills = Integer.toString(Player.countKills);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setTypeface(typeface);

        paint.setColor(color);
        paint.setTextSize(100);
        canvas.drawText("KILLS: " + kills, 100, 100, paint);
    }

    public void drawCountEnemies(Canvas canvas) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/Laura.ttf");
        String enemiesCount = Integer.toString(Game.countEnemies);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setTypeface(typeface);

        paint.setColor(color);
        paint.setTextSize(100);
        canvas.drawText("Enemies: " + enemiesCount, Game.displayMetrics.widthPixels / 2 - 60, 100, paint);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }
}
