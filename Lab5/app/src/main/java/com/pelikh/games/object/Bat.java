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
import com.pelikh.games.gamepanel.HealthBar;

public class Bat extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.5;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = Gameloop.MAX_UPS / SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;
    private Bitmap enemyBitmap;

    private HealthBar healthBar;

    private Rect enemyRect;

    private int width;
    private int height;

    private int frame_index;



    public Bat(Context context, Player player, int MAX_HEALTH_POINTS) {
        super(
                ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 1000,
                Math.random() * 1000,
                32,
                MAX_HEALTH_POINTS
        );
        this.player = player;
        this.healthBar = new HealthBar(context, this);
        width = 64;
        height = 64;
        enemyRect = new Rect(0,0,width,height);

        frame_index = 0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        enemyBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat2, bitmapOptions);
    }

    public static boolean ReadyToSpawn() {
        if (updatesUntilNextSpawn <= 0) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn--;
            return false;
        }
    }

    @Override
    public void update() {
        // update velocity of the enemy so that the velocity is on the direction of the player
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        // calculate distance between enemy (this) and player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        // calculate direction from enemy to player
        double directionX = distanceToPlayerX / distanceToPlayer;
        double directionY = distanceToPlayerY / distanceToPlayer;

        if (distanceToPlayer > 0) { // avoid division by zero
            velocityX = directionX * MAX_SPEED;
            velocityY = directionY * MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        // update the position of the enemy
        //positionX += velocityX;
        //positionY += velocityY;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {

        int x = (int)gameDisplay.gameToDisplayCoordinatesX(positionX - radius);
        int y = (int)gameDisplay.gameToDisplayCoordinatesY(positionY - radius);


        canvas.drawBitmap(
                enemyBitmap,
                new Rect(width * frame_index,0, width * (frame_index + 1),height),
                new Rect(
                        x,
                        y,
                        x + width,
                        y + height),
                null
        );

        frame_index++;
        if (frame_index == 4)
            frame_index = 0;


        healthBar.draw(canvas, gameDisplay);
    }
}
