package com.example.flyingpotato;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameThread extends Thread {

    SurfaceHolder surfaceHolder;
    SurfaceView surfaceView;
    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 33;
    SharedPreferences pref2;

    int potato, xpotato, powerup;
    ArrayList<Flying> list;

    public GameThread(SurfaceHolder surfaceHolder, SurfaceView surfaceView, int potato, int xpotato, int powerup){
        this.surfaceHolder = surfaceHolder;
        this.surfaceView = surfaceView;
        isRunning = true;
        this.potato = potato;
        this.xpotato = xpotato;
        this.powerup = powerup;
        boolean flag = false;
        if(powerup == 1)
            flag = true;

        Random r = new Random();
        list = new ArrayList<>();

        while(list.size() != this.potato){
            Flying f  = new Flying(r.nextInt(AppConstants.SCREEN_WIDTH / 10 * 9), r.nextInt(AppConstants.SCREEN_WIDTH / 10 * 9),
                    r.nextInt(4500), flag, true);
            if(!list.contains(f))
                list.add(f);
        }

        if(powerup != 3){
            while(list.size() != this.xpotato + this.potato){
                Flying f  = new Flying(r.nextInt(AppConstants.SCREEN_WIDTH / 3 * 2), r.nextInt(AppConstants.SCREEN_WIDTH / 3 * 2),
                        r.nextInt(4500), flag, false);
                if(!list.contains(f))
                    list.add(f);
            }
        }

        Collections.shuffle(list);
    }

    @Override
    public void run() {
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(surfaceView.getContext(), AnswerActivity.class);
                intent.putExtra("potato", potato);
                surfaceView.getContext().startActivity(intent);
                ((Activity) surfaceView.getContext()).finish();
                isRunning = false;
                t.cancel();
            }
        };
        t.schedule(tt, 5000);
        while(isRunning){
            startTime = SystemClock.uptimeMillis();

            Canvas canvas = surfaceHolder.lockCanvas(null);

            if(canvas != null){
                synchronized (surfaceHolder) {
                    drawBack(canvas);

                    for(int i = 0; i < list.size(); i++)
                        drawPotato(canvas, list.get(i), 5000/list.size(), i);

                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            loopTime = SystemClock.uptimeMillis() - startTime;
            if(loopTime < DELAY){
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch(InterruptedException e){
                    Log.e("Interrupted","Interrupted while sleeping");
                }
            }
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void setIsRunning(boolean state){
        isRunning = state;
    }

    public void drawBack(Canvas canvas){

        Bitmap background = BitmapFactory.decodeResource(surfaceView.getResources(), R.drawable.back);
        int newWidth = AppConstants.SCREEN_WIDTH;
        int newHeight = AppConstants.SCREEN_HEIGHT;
        Bitmap scaled = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);

        canvas.drawBitmap(scaled,0,0,null);

    }

    public void drawPotato(Canvas canvas, Flying f, long time, int i){

        if(SystemClock.currentThreadTimeMillis() / time >= i) {

            Bitmap thing;

            if (f.isPotato)
                thing = BitmapFactory.decodeResource(surfaceView.getResources(), R.drawable.logo);
            else
                thing = BitmapFactory.decodeResource(surfaceView.getResources(), R.drawable.xpotato);
            int newWidth = AppConstants.SCREEN_WIDTH / 3;
            int newHeight = AppConstants.SCREEN_WIDTH / 3;
            Bitmap scaled = Bitmap.createScaledBitmap(thing, newWidth, newHeight, true);

            f.setInitX(f.getInitX() + (f.getFinalX() - f.getInitX())/5);
            f.setCurrY(f.getCurrY() - f.getVelocity());

            canvas.drawBitmap(scaled,f.getInitX(), f.getCurrY(),null);
        }

    }
}