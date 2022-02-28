/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2015 Caprica Software Limited.
 */

package com.dimelthoz.dygi.interfaceapplication.view.main;

import java.util.List;

import javax.swing.Action;

import com.dimelthoz.dygi.interfaceapplication.Application;
import com.dimelthoz.dygi.interfaceapplication.view.action.Resource;
import com.dimelthoz.dygi.interfaceapplication.view.action.mediaplayer.VideoTrackAction;
import uk.co.caprica.vlcj.player.base.TrackDescription;

final class VideoTrackMenu extends TrackMenu {

    VideoTrackMenu() {
        super(Resource.resource("menu.video.item.track"));
    }

    @Override
    protected Action createAction(TrackDescription trackDescription) {
        return new VideoTrackAction(trackDescription.description(), trackDescription.id());
    }

    @Override
    protected List<TrackDescription> onGetTrackDescriptions() {
        return Application.application().mediaPlayer().video().trackDescriptions();
    }

    @Override
    protected int onGetSelectedTrack() {
        return Application.application().mediaPlayer().video().track();
    }
}
