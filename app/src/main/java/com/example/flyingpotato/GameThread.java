package com.example.flyingpotato;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameThread extends Thread {

    SurfaceHolder surfaceHolder; //Surfaceholder object reference
    SurfaceView surfaceView;
    boolean isRunning; // Flag to detect whether the thread is running or not
    long startTime, loopTime; // Loop start time and loop duration
    long DELAY = 33; // Delay in milliseconds between screen refreshes
    SharedPreferences pref2;

    int potato, xpotato;

    public GameThread(SurfaceHolder surfaceHolder, SurfaceView surfaceView, int potato, int xpotato, SharedPreferences pref){
        this.surfaceHolder = surfaceHolder;
        this.surfaceView = surfaceView;
        isRunning = true;
        this.potato = potato;
        this.xpotato = xpotato;
        pref2 = pref;
    }

    @Override
    public void run() {
        // Looping until the boolean is false
        while(isRunning){
            startTime = SystemClock.uptimeMillis();

            // This is where we draw things into the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);

            if(canvas != null){
                synchronized (surfaceHolder) {
//                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
//                    AppConstants.getGameEngine().updateAndDrawBird(canvas);
//                    AppConstants.getGameEngine().updateAndDrawTubes(canvas);
                    drawBack(canvas);

                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            //loop time
            loopTime = SystemClock.uptimeMillis() - startTime;
            // Pausing here to make sure we update the right amount per second
            if(loopTime < DELAY){
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch(InterruptedException e){
                    Log.e("Interrupted","Interrupted while sleeping");
                }
            }
        }
    }

    // Return whether the thread is running
    public boolean isRunning(){
        return isRunning;
    }

    // Sets the thread state, false = stopped, true = running
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
}

