package com.pelikh.games;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.pelikh.games.gamepanel.GameOver;
import com.pelikh.games.gamepanel.Joystick;
import com.pelikh.games.gamepanel.Performance;
import com.pelikh.games.graphics.Animator;
import com.pelikh.games.graphics.SpriteBitmap;
import com.pelikh.games.map.BlockType;
import com.pelikh.games.map.DynamicMapObject;

import com.pelikh.games.object.Circle;
import com.pelikh.games.object.Bat;
import com.pelikh.games.object.Mag;
import com.pelikh.games.object.Player;
import com.pelikh.games.object.Spell;
import com.pelikh.games.map.Tilemap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Game manages all objects in the game and is responsible for updating all states and render all
 * objects to the screen
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {

    public static int countEnemies;

    private MediaPlayer hollow;
    private MediaPlayer mediaBox;
    private MediaPlayer mediaBat;
    private MediaPlayer mediaThrow;
    private final Joystick joystick;
    private final Tilemap tileMap;
    //private final Enemy enemy;
    private List<Circle> enemyList = new ArrayList<>();
    private List<Spell> spellList = new ArrayList<>();
    private Gameloop gameLoop;
    private Player player;

    private DynamicMapObject dynamicMapObject;
    private int joystickPointerId = 0;
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private Performance performance;
    private GameDisplay gameDisplay;

    private Context context;

    private Animator animator;

    private Rect buttonGameOver;

    public static DisplayMetrics displayMetrics;

    public Game(Context context) {
        super(context);

        mediaBox = MediaPlayer.create(context, R.raw.box);
        mediaBat = MediaPlayer.create(context, R.raw.bat);
        mediaThrow = MediaPlayer.create(context, R.raw.mthrow);
        hollow = MediaPlayer.create(context, R.raw.hollow);

        hollow.setLooping(true);
        hollow.start();


        hollow.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        this.context = context;

        // Get surface holder and callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new Gameloop(this, surfaceHolder);

        // game panel
        gameOver = new GameOver(context);
        performance = new Performance(context, gameLoop);
        joystick = new Joystick(275, 900, 150, 120);

        // Game objects
        SpriteBitmap spriteBitmap = new SpriteBitmap(context);
        animator = new Animator(spriteBitmap.getPlayerSpriteArray());
        player = new Player(context, joystick, 1000, 800, 32, animator, 10);

        // initialize game display and center it around the player
        displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        buttonGameOver = new Rect(
                displayMetrics.widthPixels / 2 - 250,
                displayMetrics.heightPixels / 2 - 150,
                displayMetrics.widthPixels / 2 + 250,
                displayMetrics.heightPixels / 2 + 150
        );

        tileMap = new Tilemap(spriteBitmap);

        dynamicMapObject = new DynamicMapObject(context);

        setFocusable(true);
    }

    private void resumeGame() {

        // clear all Lists
        enemyList.clear();
        spellList.clear();
        dynamicMapObject.dynamicObjects.clear();

        enemyList = new ArrayList<>();
        spellList = new ArrayList<>();

        player = new Player(context, joystick, 1000, 800, 32, animator, 10);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        dynamicMapObject = new DynamicMapObject(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Handle touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (player.getHealthPoints() <=0)
                {
                    float x = event.getX();
                    float y = event.getY();

                    if (x >= buttonGameOver.left && x <= buttonGameOver.right && y>= buttonGameOver.top && y<= buttonGameOver.bottom) {
                        resumeGame();
                    }
                    numberOfSpellsToCast--;
                }
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.getIsPressed()) {
                    // joystick was pressed before this event -> cast spell
                    numberOfSpellsToCast++;
                } else if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    // joystick is pressed in this event
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    // joystick was not previously, and not pressed in this event -> cast spell
                    numberOfSpellsToCast++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                // joystick was pressed previously and is now moved
                if (joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // joystick was let go of
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }

                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated");

        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            gameLoop = new Gameloop(this, holder);
        }

        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.d("Game.java", "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceDestroyed");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        tileMap.draw(canvas, gameDisplay);

        for (Circle dynMap : dynamicMapObject.dynamicObjects) {
            dynMap.draw(canvas, gameDisplay);
        }

        player.draw(canvas, gameDisplay);

        for (Circle enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);
        }

        for (Spell spell : spellList) {
            spell.draw(canvas, gameDisplay);
        }

        // game panels
        joystick.draw(canvas);
        performance.draw(canvas);

        // draw game over
        if (player.getHealthPoints() <= 0) {
            //gameOver.draw(canvas);
            gameOver(canvas);
        }
    }

    public void update() {

        // stop updating the game if the player is dead
        if (player.getHealthPoints() <= 0) {
            return;
        }


        joystick.update();

        player.update();

        player.setPositionX(player.getPositionX() + player.getVelocityX());
        for (Circle dynObject : dynamicMapObject.dynamicObjects) {
            if (Circle.isColliding(dynObject, player)) {
                if (player.getDirectionX() > 0)
                    player.setPositionX(player.getPositionX() + Circle.lengthCollision * (-1));
                else player.setPositionX(player.getPositionX() + Circle.lengthCollision);
                //break;
            }
        }

        player.setPositionY(player.getPositionY() + player.getVelocityY());
        for (Circle dynObject : dynamicMapObject.dynamicObjects) {
            if (Circle.isColliding(dynObject, player)) {
                if (player.getDirectionY() > 0)
                    player.setPositionY(player.getPositionY() + Circle.lengthCollision * (-1));
                else player.setPositionY(player.getPositionY() + Circle.lengthCollision);
                //break;
            }
        }


        if (player.getPositionX() <= 0)
            player.setPositionX(0);
        else if (player.getPositionX() >= 3840)
            player.setPositionX(3840);

        if (player.getPositionY() <= 0)
            player.setPositionY(0);
        else if (player.getPositionY() >= 3840)
            player.setPositionY(3840);

        // Spawn Enemy
        if (Bat.ReadyToSpawn()) {
            Random random = new Random();
            int randomInt = random.nextInt(15) + 1;
            if (randomInt == 15)
                enemyList.add(new Mag(getContext(), player, 2, spellList));
            else
                enemyList.add(new Bat(getContext(), player, 2));
        }

        // Update Enemy
        for (Circle enemy : enemyList) {
            enemy.update();

            enemy.setPositionX(enemy.getPositionX() + enemy.getVelocityX());
            if (enemy instanceof Mag) {
                for (Circle dynObject : dynamicMapObject.dynamicObjects) {
                    if (Circle.isColliding(dynObject, enemy)) {
                        if (enemy.getDirectionX() > 0)
                            enemy.setPositionX(enemy.getPositionX() + Circle.lengthCollision * (-1));
                        else enemy.setPositionX(enemy.getPositionX() + Circle.lengthCollision);
                        //break;
                    }
                }
            }

            enemy.setPositionY(enemy.getPositionY() + enemy.getVelocityY());
            if (enemy instanceof Mag) {
                for (Circle dynObject : dynamicMapObject.dynamicObjects) {
                    if (Circle.isColliding(dynObject, player)) {
                        if (enemy.getDirectionY() > 0)
                            enemy.setPositionY(enemy.getPositionY() + Circle.lengthCollision * (-1));
                        else enemy.setPositionY(enemy.getPositionY() + Circle.lengthCollision);
                        //break;
                    }
                }
            }
        }


        // Update Spell
        while (numberOfSpellsToCast > 0) {
            spellList.add(new Spell(getContext(), player));
            numberOfSpellsToCast--;
            mediaThrow.start();
        }
        for (Spell spell : spellList) {
            spell.update();
        }

        Iterator<Spell> iteratorSpel = spellList.iterator();
        while (iteratorSpel.hasNext()){
            Circle spell = iteratorSpel.next();

            Iterator<Circle> iteratorDynamicObject = dynamicMapObject.dynamicObjects.iterator();
            while (iteratorDynamicObject.hasNext()) {
                Circle dynamicObject = iteratorDynamicObject.next();

                if (Circle.isColliding(spell, dynamicObject)) {
                    if (dynamicObject.blockType == BlockType.BOX) {
                        iteratorDynamicObject.remove();
                        mediaBox.start();
                    }
                    iteratorSpel.remove();
                    break;
                }
            }
        }


        // Collision between enemy and player and spells
        Iterator<Circle> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            Circle enemy = iteratorEnemy.next();

            // Enemy and Player
            if (Circle.isColliding(enemy, player)) {
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints() - 1);
                Player.countKills++;
                continue;
            }

            Iterator<Spell> iteratorSpell = spellList.iterator();
            while (iteratorSpell.hasNext()) {
                // Spell and Enemy
                Circle spell = iteratorSpell.next();
                if (Circle.isColliding(spell, enemy)) {
                    enemy.setHealthPoints(enemy.getHealthPoints() - 1);
                    if (enemy.getHealthPoints() <= 0) {
                        iteratorEnemy.remove();
                        Player.countKills++;
                        mediaBat.start();
                    }
                    iteratorSpell.remove();
                    break;
                }

                if (Circle.isColliding(spell, player)) {
                    player.setHealthPoints(player.getHealthPoints() - 2);
                    iteratorSpell.remove();
                    break;
                }

                // garlic and boarder
                if (spell.getPositionX() >= 3840 || spell.getPositionX() <= 0 ||
                        spell.getPositionY() <= 0 || spell.getPositionY() >= 3840) {
                    iteratorSpell.remove();
                    break;
                }
            }
        }
        gameDisplay.update();

        countEnemies = enemyList.size();
    }

    public void pause() {
        gameLoop.stopLoop();
    }

    public void gameOver(Canvas canvas) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        Bitmap enemyBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.button, bitmapOptions);

        canvas.drawBitmap(
                enemyBitmap,
                new Rect(0,0,500,300),
                buttonGameOver,
                null
        );
    }
}
