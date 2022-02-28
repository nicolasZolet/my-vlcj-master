package com.dimelthoz.dygi.interfaceapplication.utils;

public class ThreadHelper {
    public static void delay(int timeDelay) {
        try{
            Thread.sleep(timeDelay);
        } catch (InterruptedException e){
            System.out.println(LogHelper.TEXT_RED + "Thread sleep error -> " + "InterruptedException: " + e + LogHelper.TEXT_RESET);
        }
    }
}
