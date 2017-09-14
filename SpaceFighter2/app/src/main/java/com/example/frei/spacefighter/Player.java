package com.example.frei.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by frei on 2017/9/14.
 */

public class Player {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 0;

    private boolean boosting;
    private final int GRAVITY = -10;

    //避免出框
    private int maxY;
    private int minY;

    //限制速度
    private final int MIN_SPEED = -1;
    private final int MAX_SPEED = 20;

    public Player(Context context, int screenX, int screenY) {
        x = 75;
        y = 50;
        speed = 1;

        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);

        maxY=screenY-bitmap.getHeight();
        minY=0;

        boosting=false;

    }

    public void setBoosting(){
        boosting = true;
    }

    public void stopBoosting(){
        boosting = false;
    }

    public void update(){
        x++;
        if (boosting){
            speed+=2;
        }else {
            speed-=5;
        }
        if (speed>MAX_SPEED){
            speed=MAX_SPEED;
        }
        if (speed<MIN_SPEED){
            speed=MIN_SPEED;
        }
        y-=speed+GRAVITY;

        if (y<minY){
            y=minY;
        }
        if (y>maxY){
            y=maxY;
        }

    }


    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSpeed(){
        return speed;
    }
}

