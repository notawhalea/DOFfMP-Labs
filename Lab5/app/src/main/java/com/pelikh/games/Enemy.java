package com.pelikh.games;

import android.content.Context;

import androidx.core.content.ContextCompat;

public class Enemy extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = Gameloop.MAX_UPS / SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    //Represents a player in matchmaking. When starting a matchmaking request, a player has a player ID, attributes, and may have latency data. 
    //Team information is added after a match has been successfully completed.
    private final Player player;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius) {
        super(
                ContextCompat.getColor(context, R.color.enemy),
                positionX,
                positionY,
                radius
        );

        this.player = player;
    }

    public Enemy(Context context, Player player) {
        super(
                ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 1000,
                Math.random() * 1000,
                30
        );
        this.player = player;
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
        positionX += velocityX;
        positionY += velocityY;
    }
}
