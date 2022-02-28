package com.dimelthoz.dygi.interfaceapplication.hardware;

public class LedStatus {
    private static final long BLINK_TIME = 500;
    private static boolean lastLevel = false;
    private static long startBlinkTime=System.currentTimeMillis();

    public static void blink(){
        if(System.currentTimeMillis() - startBlinkTime > BLINK_TIME){
            Gpio.digitalWrite(GpioManager.PIN_LED_STATUS, lastLevel);
            lastLevel = !lastLevel;
            startBlinkTime = System.currentTimeMillis();
        }
    }
}
