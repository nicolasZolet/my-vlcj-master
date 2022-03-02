package com.dimelthoz.dygi.interfaceapplication.hardware;

public class GpioManager {

    public static final int PIN_LED_STATUS = 6;
    public static final int PIN_RED_SIGNAL = 22;
    public static final int PIN_YELLOW_SIGNAL = 23;
    public static final int PIN_GREEN_SIGNAL = 24;
    public static final int PIN_RESERVE_SIGNAL = 25;

    public static void start() {
        setup();
        new Thread(runGpios).start();
    }

    private static void setup() {
        Gpio.setup(PIN_LED_STATUS, Gpio.OUT);
        Gpio.setup(PIN_RED_SIGNAL, Gpio.IN);
        Gpio.setup(PIN_YELLOW_SIGNAL, Gpio.IN);
        Gpio.setup(PIN_GREEN_SIGNAL, Gpio.IN);
        Gpio.setup(PIN_RESERVE_SIGNAL, Gpio.IN);
    }

    private static void main() {
        LedStatus.blink();
        InputSignal.read();
    }

    static Runnable runGpios = new Runnable() {
        @Override
        public void run() {
            while (true) {
                main();
            }
        }
    };
}
