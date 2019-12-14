package com.example.flyingpotato;

import androidx.annotation.Nullable;

public class Flying {
    boolean isPotato;
    private int time, initX, finalX, velocity, currY;

    public Flying(int initX, int finalX, int time, boolean slow, boolean isPotato){
        this.initX = initX;
        this.finalX = finalX;
        velocity = (int)Math.ceil(AppConstants.SCREEN_HEIGHT * 0.3);
        if(slow)
            velocity /= 2;
        this.time = time;
        this.isPotato = isPotato;
        currY = AppConstants.SCREEN_HEIGHT;
    }

    public int getInitX() {
        return initX;
    }

    public void setInitX(int initX) {
        this.initX = initX;
    }

    public int getFinalX() {
        return finalX;
    }

    public void setFinalX(int finalX) {
        this.finalX = finalX;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isPotato() {
        return isPotato;
    }

    public void setPotato(boolean potato) {
        isPotato = potato;
    }

    public int getCurrY() {
        return currY;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
//        return super.equals(obj);
        if(Math.abs(this.time - ((Flying)obj).time) <= 100 && Math.abs(this.initX - ((Flying) obj).initX) <= AppConstants.SCREEN_WIDTH/7 && Math.abs(this.finalX - ((Flying) obj).finalX) <= AppConstants.SCREEN_WIDTH/7)
            return true;
        else
            return false;
    }
}
