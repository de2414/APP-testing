package com.example.frei.spacefighter;


import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;


public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting display obj
        Display display = getWindowManager().getDefaultDisplay();
        //getting the screen resolution into point obj
        Point size = new Point();
        display.getSize(size);
        //init game view obj
        //passing the screen size to the GameView constructor
        gameView = new GameView(this,size.x,size.y);
        //adding it to contentview
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
