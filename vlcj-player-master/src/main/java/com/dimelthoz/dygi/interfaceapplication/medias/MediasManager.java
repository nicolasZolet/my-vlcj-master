package com.dimelthoz.dygi.interfaceapplication.medias;

import com.dimelthoz.dygi.interfaceapplication.utils.FileManager;

import java.io.File;

public class MediasManager {
    public static final String mrlMultipleSignal = FileManager.getAbsolutePath(new File("src/media/error.png"));
    public static final String mrlYellowSignal = FileManager.getAbsolutePath(new File("src/media/yellow.png"));
    public static final String mrlCountdown = FileManager.getAbsolutePath(new File("src/media/countdown.mp4"));
    public static final String mrlClientSample = FileManager.getAbsolutePath(new File("src/media/ForBiggerEscapes.mp4"));
}
