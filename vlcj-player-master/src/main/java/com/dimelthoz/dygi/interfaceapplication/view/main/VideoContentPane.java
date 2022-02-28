package com.dimelthoz.dygi.interfaceapplication.view.main;

import java.awt.CardLayout;

import javax.swing.*;

import com.dimelthoz.dygi.interfaceapplication.Application;
import com.dimelthoz.dygi.interfaceapplication.view.image.ImagePane;

final class VideoContentPane extends JPanel {

    private static final String NAME_DEFAULT = "default";

    private static final String NAME_VIDEO = "video";

    private static final String NAME_CALLBACK_VIDEO = "callback-video";

    private final CardLayout cardLayout;

    VideoContentPane() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        add(new ImagePane(ImagePane.Mode.CENTER, getClass().getResource("/vlcj-logo.png"), 0.3f), NAME_DEFAULT);
        add(Application.application().mediaPlayerComponent(), NAME_VIDEO);
        add(Application.application().callbackMediaPlayerComponent(), NAME_CALLBACK_VIDEO);
    }

    public void showDefault() {
        cardLayout.show(this, NAME_DEFAULT);
    }

    public void showVideo() {
        switch (Application.application().videoOutput()) {
            case EMBEDDED:
                cardLayout.show(this, NAME_VIDEO);
                break;
            case CALLBACK:
                cardLayout.show(VideoContentPane.this, NAME_CALLBACK_VIDEO);
                break;
            default:
                throw new IllegalStateException();
        }
    }

}
