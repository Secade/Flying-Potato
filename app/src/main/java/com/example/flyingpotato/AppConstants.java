package com.example.flyingpotato;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {

    static BitmapBank bitmapBank; // Bitmap object reference
    static GameEngine gameEngine; // GameEngine object reference
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int gapBetweenTopAndBottomTubes;
    static int numberOfTubes;
    static int tubeVelocity;
    static int minTubeOffsetY;
    static int maxTubeOffsetY;
    static int distanceBetweenTubes;
//    static SoundBank soundBank;
    static Context gameActivityContext;
    static int randomOffset;
    static int TEXT_SIZE;

    public static void initialization(Context context){
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine = new GameEngine();
//        soundBank = new SoundBank(context);
    }

//    public static SoundBank getSoundBank(){
//        return soundBank;
//    }

    public static void setGameConstants(){
        AppConstants.numberOfTubes = 2;
        AppConstants.gapBetweenTopAndBottomTubes = (int) (AppConstants.SCREEN_HEIGHT / 3.0); //598 for SCREEN_HEIGHT:1794
        AppConstants.tubeVelocity = (int)(Math.floor(AppConstants.SCREEN_WIDTH * 0.01) * 2.0); // 10, for SCREEN_WIDTH: 1080
        AppConstants.VELOCITY_WHEN_JUMPED = (int)(-Math.floor(AppConstants.gapBetweenTopAndBottomTubes * 0.067) / 1.25); //-40
        AppConstants.gravity = (int)Math.ceil(AppConstants.gapBetweenTopAndBottomTubes * 0.005); //3
        AppConstants.minTubeOffsetY = (int) (AppConstants.gapBetweenTopAndBottomTubes / 2.0);
        AppConstants.maxTubeOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minTubeOffsetY - AppConstants.gapBetweenTopAndBottomTubes;
        AppConstants.distanceBetweenTubes = AppConstants.SCREEN_WIDTH * 3 / 4;
        AppConstants.TEXT_SIZE = (int)Math.ceil(AppConstants.gapBetweenTopAndBottomTubes * .1672); //100
        AppConstants.randomOffset = 239;
    }

    // Return BitmapBank instance
    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    // Return GameEngine instance
    public static GameEngine getGameEngine(){
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }
}
