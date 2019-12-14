package com.example.flyingpotato;

public class Users {
    private String name;
    private String password;
    private String id;
    private int goldMulti;
    private int lowSpeed;
    private int lessObs;
    private double cash;
    private int highscore;
    private int highestLevel;

    public Users(String id,String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        goldMulti = 0;
        lowSpeed = 0;
        lessObs =0;
        cash = 0.0;
        highscore = 0;
        highestLevel = 0;
    }

    public Users(Users use){
        this.id = use.getId();
        this.name = use.getName();
        this.password = use.getPassword();
        this.goldMulti = use.getGoldMulti();
        this.lowSpeed = use.getLowSpeed();
        this.lessObs = use.getLessObs();
        this.cash = use.getCash();
        this.highscore = use.getHighscore();
        this.highestLevel = use.getHighestLevel();
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

    public int getGoldMulti() {
        return goldMulti;
    }

    public void setGoldMulti(int goldMulti) {
        this.goldMulti = goldMulti;
    }

    public int getLowSpeed() {
        return lowSpeed;
    }

    public void setLowSpeed(int lowSpeed) {
        this.lowSpeed = lowSpeed;
    }

    public int getLessObs() {
        return lessObs;
    }

    public void setLessObs(int lessObs) {
        this.lessObs = lessObs;
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
