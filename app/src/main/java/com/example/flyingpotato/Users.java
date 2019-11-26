package com.example.flyingpotato;

public class Users {
    String name;
    String password;
    String id;
    int powerup1;
    int powerup2;
    int powerup3;
    double cash;

    public Users(String id,String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        powerup1 = 0;
        powerup2 = 0;
        powerup3 =0;
        cash = 0.0;
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
}
