package com.example.frei.spacefighter;

import java.util.Random;

/**
 * Created by frei on 2017/9/14.
 */

public class Star {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Star(int screenX,int screenY){
        maxX=screenX;
        maxY=screenY;
        minX=0;
        minY=0;
        Random random = new Random();
        speed=random.nextInt(10);

        x=random.nextInt(maxX);
        y=random.nextInt(maxY);
    }

    public void update(int playerSpeed){
        x-=playerSpeed;
        x-=speed;
        if (x<0){
            x=maxX;
            Random random = new Random();
            y=random.nextInt(maxY);
            speed=random.nextInt(15);
        }
    }

    public float getStarWidth(){
        float minX=1.0f;
        float maxX=4.0f;
        Random random = new Random();
        float finalX = random.nextFloat()*(maxX-minX)+minX;
        return finalX;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
