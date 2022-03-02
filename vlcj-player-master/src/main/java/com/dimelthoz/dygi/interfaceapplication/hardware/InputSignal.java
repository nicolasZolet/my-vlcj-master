package com.dimelthoz.dygi.interfaceapplication.hardware;

import com.dimelthoz.dygi.interfaceapplication.Application;
import com.dimelthoz.dygi.interfaceapplication.medias.MediasManager;
import com.dimelthoz.dygi.interfaceapplication.timeline.Adds;

import static com.dimelthoz.dygi.interfaceapplication.Application.application;
import static com.dimelthoz.dygi.interfaceapplication.Application.playMedia;
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
//            redSignal.setLevel(false);
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
            Adds.refreshCurrentAdds();
        } else {
            if (redSignal.isOn()) {

                if(Adds.getIndexClientVideo()==0 && !application().isPlaying(pathMedias + Adds.getClientVideo())){
                    playMedia(pathMedias + Adds.getClientVideo());
                    Adds.updateStartTimer();
                }

                else if (Adds.hasVideoEnd()) {
                    Adds.nextClientVideo();
                    playMedia(pathMedias + Adds.getClientVideo());
                    Adds.updateStartTimer();
                }
//                else if (!application().isPlaying(pathMedias + Adds.getClientVideo())) {
//                    playMedia(pathMedias + Adds.getClientVideo());
//                }

            } else if (greenSignal.isOn()) {
                if (!application().isPlaying(mrlCountdown)) {
                    playMedia(mrlCountdown);
                    Application.application().mediaPlayer().controls().skipTime(getSkipTimeGreenSignal());
                    Adds.nextContainer();
                }
            } else if (yellowSignal.isOn()) {
                if (!application().isPlaying(mrlYellowSignal)) {
                    playMedia(mrlYellowSignal);
                }
            }
        }

    }

    private static long getSkipTimeGreenSignal() {
        long videoDuration = 60000;
        long timeGreenOn = greenSignal.getLastTimeOn();
        long skipTimeSeconds;

        if (timeGreenOn <= 1100) {
            skipTimeSeconds = videoDuration - 1100;
        } else if (timeGreenOn >= videoDuration) {
            skipTimeSeconds = 0;
        } else {
            skipTimeSeconds = videoDuration - timeGreenOn;
        }
        return skipTimeSeconds;
    }

    private static boolean hasErrorSignal() {
        return hasMultipleSignal() || hasNoSignal();
    }

    private static final long TIME_MULTIPLE_SIGNAL_ERROR = 200;
    private static long initTimerMultipleSignal = System.currentTimeMillis();

    private static boolean hasMultipleSignal() {
        if (redSignal.isOn() && greenSignal.isOn() || redSignal.isOn() && yellowSignal.isOn() || greenSignal.isOn() && yellowSignal.isOn()) {
            return System.currentTimeMillis() - initTimerMultipleSignal > TIME_MULTIPLE_SIGNAL_ERROR;
        }
        initTimerMultipleSignal = System.currentTimeMillis();
        return false;
    }

    private static final long TIME_NO_SIGNAL_ERROR = 600;
    private static long initTimerNoSignal = System.currentTimeMillis();

    private static boolean hasNoSignal() {
        if (!redSignal.isOn() && !greenSignal.isOn() && !yellowSignal.isOn()) {
            return System.currentTimeMillis() - initTimerNoSignal > TIME_NO_SIGNAL_ERROR;
        }
        initTimerNoSignal = System.currentTimeMillis();
        return false;
    }
}
