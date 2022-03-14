package com.dimelthoz.dygi.interfaceapplication.hardware;

public class LedStatus {
    private static final long BLINK_TIME = 500;
    private static boolean lastLevel = false;
    private static long initTimerBlink = System.currentTimeMillis();

    public static void blink(){
        if(System.currentTimeMillis() - initTimerBlink > BLINK_TIME){
            lastLevel = !lastLevel;
            Gpio.digitalWrite(GpioManager.PIN_LED_STATUS, lastLevel);
            initTimerBlink = System.currentTimeMillis();
        }
    }
}
