package com.dimelthoz.dygi.interfaceapplication.hardware;

public class Signal {
    private static final long TIME_ON_LEVEL = 70;
    private final boolean ON_LEVEL;
    boolean currentLevel, lastLevel;
    long lastTimeOn = 0, initTimeOn = System.currentTimeMillis();

    public Signal(boolean onLevel) {
        ON_LEVEL = onLevel;
        currentLevel = !onLevel;
        lastLevel = currentLevel;
    }

    public void setLevel(boolean level) {

        if (level == ON_LEVEL) {
            if (lastLevel != ON_LEVEL) {
                initTimeOn = System.currentTimeMillis();
            } else if (currentLevel != ON_LEVEL && System.currentTimeMillis() - initTimeOn > TIME_ON_LEVEL) {
                currentLevel = ON_LEVEL;
            }
        }
        
        if (level != ON_LEVEL) {
            if (lastLevel == ON_LEVEL && System.currentTimeMillis() - initTimeOn > TIME_ON_LEVEL) {
                lastTimeOn = System.currentTimeMillis() - initTimeOn;
            }
            currentLevel = !ON_LEVEL;
            initTimeOn = System.currentTimeMillis();
        }

        lastLevel = level;
    }

    public long getLastTimeOn() {
        return lastTimeOn;
    }


    public boolean isOn() {
        return currentLevel == ON_LEVEL;
    }
}
