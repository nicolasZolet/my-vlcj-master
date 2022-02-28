package com.dimelthoz.dygi.interfaceapplication.hardware;

import javax.print.attribute.standard.Media;

import java.io.File;

import static com.dimelthoz.dygi.interfaceapplication.Application.application;
import static com.dimelthoz.dygi.interfaceapplication.medias.MediasManager.*;

public class InputSignal {

    private static final long TIME_READ_INPUTS = 200;
    private static long timerReadInput = System.currentTimeMillis();

    private static final Signal redSignal = new Signal(false);
    private static final Signal greenSignal = new Signal(false);
    private static final Signal yellowSignal = new Signal(false);
    private static final Signal reserveSignal = new Signal(false);

    public static void read() {
        if (System.currentTimeMillis() - timerReadInput > TIME_READ_INPUTS) {

            try {
                redSignal.setLevel(Gpio.digitalRead(GpioManager.PIN_RED_SIGNAL));
                greenSignal.setLevel(Gpio.digitalRead(GpioManager.PIN_GREEN_SIGNAL));
                yellowSignal.setLevel(Gpio.digitalRead(GpioManager.PIN_YELLOW_SIGNAL));
                reserveSignal.setLevel(Gpio.digitalRead(GpioManager.PIN_RESERVE_SIGNAL));

            } catch (GpioException ignored) {
            }

            treatInputSignal();
            timerReadInput = System.currentTimeMillis();
        }
    }

    private static void treatInputSignal() {
        if ((hasMultipleSignal() || hasNoSignal())) {
            if (!application().isPlaying(mrlMultipleSignal)) {
                System.out.println("hasMultipleSignal | hasNoSignal");
                application().mediaPlayer().media().play(mrlMultipleSignal);
            }
        } else {
            if (redSignal.isOn()) {
                if (!application().isPlaying(mrlClientSample)) {
                    System.out.println("red signal");
                    application().mediaPlayer().media().play(mrlClientSample);
                }
            } else if (greenSignal.isOn()) {
                if (!application().isPlaying(mrlCountdown)) {
                    System.out.println("green signal");
                    long videoDuration = 60000;
                    String skipTime = ":start-time=" + Math.min(0, videoDuration - greenSignal.getLastTimeOn());
                    application().mediaPlayer().media().play(mrlCountdown, skipTime);
                }
            } else if (yellowSignal.isOn()) {
                if (!application().isPlaying(mrlYellowSignal)) {
                    System.out.println("yellow signal");
                    application().mediaPlayer().media().play(mrlYellowSignal);
                }
            }
        }

    }

    private static boolean hasMultipleSignal() {
        return redSignal.isOn() && greenSignal.isOn() || redSignal.isOn() && yellowSignal.isOn() || greenSignal.isOn() && yellowSignal.isOn();
    }

    private static boolean hasNoSignal() {
        return !redSignal.isOn() && !greenSignal.isOn() && !yellowSignal.isOn();
    }
}
