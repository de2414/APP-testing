package com.example.frei.spacefighter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by frei on 2017/9/14.
 */

public class GameView extends SurfaceView implements Runnable {

    private Player player;

    //objs will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private ArrayList<Star>stars = new ArrayList<Star>();

    //boolean variable to track if the game is playing or not
    private boolean playing;

    private Thread gameThread = null;


    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player = new Player(context,screenX,screenY);
        surfaceHolder = getHolder();
        paint = new Paint();

        //adding 100 stars
        int starNums = 100;
        for (int i=0;i<starNums;i++){
            Star s = new Star(screenX,screenY);
            stars.add(s);
        }

    }

    @Override
    public void run() {

        while (playing){
            update();
            draw();
            control();
        }

    }

    private void update() {
        player.update();

        //updating the stars with player speed
        for (Star s:stars){
            s.update(player.getSpeed());
        }

    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()){
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing a background color for canvas
            canvas.drawColor(Color.BLACK);

            //setting the paint color to white to draw the stars
            paint.setColor(Color.WHITE);

            for (Star s:stars){
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(),s.getY(),paint);
            }



            //drawing the player
            canvas.drawBitmap(
                    player.getBitmap()
                    ,player.getX()
                    ,player.getY()
                    ,paint);
            //unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
//暫停 沒有玩
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    //重新開始 玩
    public void resume() {

        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()&MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                //when user presses on the screen
                player.setBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                //when user release the screen
                player.stopBoosting();
                break;
            default:
                break;
        }
        return true;
    }
}
