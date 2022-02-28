package com.dimelthoz.dygi.interfaceapplication.hardware;

import com.dimelthoz.dygi.interfaceapplication.utils.CommandPrompt;

public class Gpio {

    public static final String IN = "in";
    public static final String OUT = "out";

    public static void setup(int pin, String mode){
        String command = "gpio mode " + pin + " " + mode;
        CommandPrompt.execute(command);
    }

    public static void digitalWrite(int pin, boolean level){
        String command = "gpio write " + pin + " " + (level?"1":"0");
        CommandPrompt.execute(command);
    }

    public static boolean digitalRead(int pin) throws GpioException {
        String command = "gpio read " + pin;

        String response = CommandPrompt.execute(command);
        if(response.equals("1") || response.equals("0")){
            return response.equals("1");
        }
        throw new GpioException();
    }
}
