package com.dimelthoz.dygi.interfaceapplication.hardware;

import static com.dimelthoz.dygi.interfaceapplication.Application.application;
import static com.dimelthoz.dygi.interfaceapplication.medias.MediasManager.*;

public class InputSignal {

    private static final long TIME_TREAT_INPUT = 50;
    private static long timerTreatInput = System.currentTimeMillis();

    private static final Signal redSignal = new Signal(false);
    private static final Signal greenSignal = new Signal(false);
    private static final Signal yellowSignal = new Signal(false);
    private static final Signal reserveSignal = new Signal(false);

    public static void read() {
        if (System.currentTimeMillis() - timerTreatInput > TIME_TREAT_INPUT) {
            try {
                redSignal.setLevel(Gpio.digitalRead(GpioManager.PIN_RED_SIGNAL));
                greenSignal.setLevel(Gpio.digitalRead(GpioManager.PIN_GREEN_SIGNAL));
                yellowSignal.setLevel(Gpio.digitalRead(GpioManager.PIN_YELLOW_SIGNAL));
                reserveSignal.setLevel(Gpio.digitalRead(GpioManager.PIN_RESERVE_SIGNAL));

            } catch (GpioException ignored) {
            }
            treatInputSignal();
            timerTreatInput = System.currentTimeMillis();
        }
    }

    private static void treatInputSignal() {
        if (hasErrorSignal()) {
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
                    application().mediaPlayer().media().play(mrlCountdown, getSkipTimeGreenSignal());
                }
            } else if (yellowSignal.isOn()) {
                if (!application().isPlaying(mrlYellowSignal)) {
                    System.out.println("yellow signal");
                    application().mediaPlayer().media().play(mrlYellowSignal);
                }
            }
        }

    }

    private static String getSkipTimeGreenSignal(){
        long videoDuration = 60;
        long skipTimeSeconds = Math.min(videoDuration,Math.max(1, videoDuration - (greenSignal.getLastTimeOn() / 1000)));
        return ":start-time=" + skipTimeSeconds;
    }

    private static long initialTimerSignalError = System.currentTimeMillis();
    private static final long TIME_SIGNAL_ERROR = 350;

    private static boolean hasErrorSignal() {
        if (hasMultipleSignal() || hasNoSignal()) {
            return System.currentTimeMillis() - initialTimerSignalError > TIME_SIGNAL_ERROR;
        } else {
            initialTimerSignalError = System.currentTimeMillis();
        }
        return false;
    }

    private static boolean hasMultipleSignal() {
        return redSignal.isOn() && greenSignal.isOn() || redSignal.isOn() && yellowSignal.isOn() || greenSignal.isOn() && yellowSignal.isOn();
    }

    private static boolean hasNoSignal() {
        return !redSignal.isOn() && !greenSignal.isOn() && !yellowSignal.isOn();
    }
}
