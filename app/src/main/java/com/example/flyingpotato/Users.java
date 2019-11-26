package com.example.flyingpotato;

public class Users {
    private String name;
    private String password;
    private String id;
    private int powerup1;
    private int powerup2;
    private int powerup3;
    private double cash;
    private int highscore;
    private int highestLevel;

    public Users(String id,String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        powerup1 = 0;
        powerup2 = 0;
        powerup3 =0;
        cash = 0.0;
        highscore = 0;
        highestLevel = 0;
    }

    public Users(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPowerup1() {
        return powerup1;
    }

    public void setPowerup1(int powerup1) {
        this.powerup1 = powerup1;
    }

    public int getPowerup2() {
        return powerup2;
    }

    public void setPowerup2(int powerup2) {
        this.powerup2 = powerup2;
    }

    public int getPowerup3() {
        return powerup3;
    }

    public void setPowerup3(int powerup3) {
        this.powerup3 = powerup3;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getHighestLevel() {
        return highestLevel;
    }

    public void setHighestLevel(int highestLevel) {
        this.highestLevel = highestLevel;
    }
}
