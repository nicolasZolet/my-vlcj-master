package com.dimelthoz.dygi.interfaceapplication.hardware;

public class Signal {
    private final boolean ON_LEVEL;
    boolean currentLevel, lastLevel;
    long lastTimeOn = 0, initTimeOn = System.currentTimeMillis();

    public Signal(boolean onLevel){
        this.ON_LEVEL = onLevel;
        currentLevel = !onLevel;
        lastLevel = currentLevel;
    }

    public void setLevel(boolean level){
        this.currentLevel = level;

        if(level==ON_LEVEL){
            initTimeOn = System.currentTimeMillis();
        } else {
            lastTimeOn = System.currentTimeMillis() - lastTimeOn;
        }

    }

    public long getLastTimeOn(){
        return lastTimeOn;
    }

    public boolean haveTurnedOn(){
        boolean hasTurnedOn = currentLevel != lastLevel;
        lastLevel = currentLevel;
        return hasTurnedOn;
    }

    public boolean isOn(){
        return ON_LEVEL == currentLevel;
    }
}
