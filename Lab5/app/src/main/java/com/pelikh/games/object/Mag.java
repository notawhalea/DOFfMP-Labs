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

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Mag extends Circle{

    private Timer timer;

    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.1;

    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;
    private final Player player;

    private Bitmap enemyBitmap;

    private HealthBar healthBar;

    private Rect enemyRect;

    private int width;
    private int height;

    private int frame_index;

    List<Spell> spellList;

    Context context;
    private double distanceToPlayer;


    public Mag(Context context, Player player, int MAX_HEALTH_POINTS, List<Spell> spellList) {
        super(
                ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 2000,
                Math.random() * 2000,
                32,
                MAX_HEALTH_POINTS
        );
        this.context = context;
        this.player = player;
        this.healthBar = new HealthBar(context, this);
        width = 64;
        height = 64;
        enemyRect = new Rect(0,0,width,height);

        this.spellList = spellList;
        frame_index = 0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        enemyBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.mag3, bitmapOptions);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fire();
            }
        }, 0, 5000);
    }

    public void fire() {
        updateDirection();
        spellList.add(new Spell(context, this));
    }

    private void updateDirection() {
        // update velocity of the enemy so that the velocity is on the direction of the player
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        // calculate distance between enemy (this) and player
        distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        // calculate direction from enemy to player
        directionX = distanceToPlayerX / distanceToPlayer;
        directionY = distanceToPlayerY / distanceToPlayer;
    }


    @Override
    public void update() {

        updateDirection();

        if (distanceToPlayer > 0) { // avoid division by zero
            velocityX = directionX * MAX_SPEED;
            velocityY = directionY * MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        // update the position of the enemy
        positionX += velocityX;
        positionY += velocityY;
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
        if (frame_index == 6)
            frame_index = 0;

        healthBar.draw(canvas, gameDisplay);
    }
}
