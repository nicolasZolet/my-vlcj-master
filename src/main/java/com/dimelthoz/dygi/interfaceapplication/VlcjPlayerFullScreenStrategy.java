package com.dimelthoz.dygi.interfaceapplication;

import java.awt.Window;

import com.dimelthoz.dygi.interfaceapplication.event.AfterExitFullScreenEvent;
import com.dimelthoz.dygi.interfaceapplication.event.BeforeEnterFullScreenEvent;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;

final class VlcjPlayerFullScreenStrategy extends AdaptiveFullScreenStrategy {

    VlcjPlayerFullScreenStrategy(Window window) {
        super(window);
    }

    @Override
    protected void onBeforeEnterFullScreen() {
        Application.application().post(BeforeEnterFullScreenEvent.INSTANCE);
    }

    @Override
    protected  void onAfterExitFullScreen() {
        Application.application().post(AfterExitFullScreenEvent.INSTANCE);
    }

}
