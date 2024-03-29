package com.example.flyingpotato;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    GameThread gameThread;
    int potato, xpotato, powerup;

    public GameView(Context context, int powerup) {
        super(context);
        Random r = new Random();
        potato = r.nextInt(5) + 1;
        xpotato = r.nextInt(5) + 1;
        this.powerup = powerup;
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(!gameThread.isRunning()){
            gameThread = new GameThread(holder, this, potato, xpotato, powerup);
            gameThread.start();
        }else{
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(gameThread.isRunning()){
            gameThread.setIsRunning(false);
            boolean retry = true;
            while(retry){
                try{
                    gameThread.join();
                    retry = false;
                }catch(InterruptedException e){}
            }
        }
    }

    void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder, this, potato, xpotato, powerup);
    }
}
