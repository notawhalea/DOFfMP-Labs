package com.pelikh.games.object;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.pelikh.games.GameDisplay;
import com.pelikh.games.Gameloop;
import com.pelikh.games.Utils;
import com.pelikh.games.gamepanel.HealthBar;
import com.pelikh.games.gamepanel.Joystick;
import com.pelikh.games.R;
import com.pelikh.games.graphics.Animator;
import com.pelikh.games.graphics.Sprite;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;

    public static int countKills = 0;
    private final Joystick joystick;
    private final HealthBar healthBar;
    private Animator animator;

    private PlayerState playerState;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Animator animator, int MAX_HEALTH_POINTS) {
        super(
                ContextCompat.getColor(context, R.color.player),
                positionX,
                positionY,
                radius,
                MAX_HEALTH_POINTS
        );
        this.joystick = joystick;

        this.healthBar = new HealthBar(context, this);
        this.animator = animator;
        this.playerState = new PlayerState(this);
    }

   @Override
    public void update() {
        // update velocity
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;

        //update position
        //positionX += velocityX;
        //positionY += velocityY;

        // update direction
       if (velocityX != 0 || velocityY != 0) {
           double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
           directionX = velocityX / distance;
           directionY = velocityY / distance;
       }

       playerState.update();
    }



    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(canvas, gameDisplay, this);

        healthBar.draw(canvas, gameDisplay);
    }



    public PlayerState getPlayerState() {
        return playerState;
    }
}
