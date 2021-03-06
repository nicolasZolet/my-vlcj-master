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

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.dimelthoz.dygi.interfaceapplication.Application;
import com.dimelthoz.dygi.interfaceapplication.view.OnDemandMenu;
import com.dimelthoz.dygi.interfaceapplication.view.action.Resource;
import com.dimelthoz.dygi.interfaceapplication.view.action.mediaplayer.ChapterAction;
import uk.co.caprica.vlcj.player.base.ChapterDescription;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

// FIXME there's no reason this couldn't be another radiobutton menu... and show the current chapter - probably more useful that way even if not the same as VLC

final class ChapterMenu extends OnDemandMenu {

    ChapterMenu() {
        super(Resource.resource("menu.playback.item.chapter"), true);
    }

    @Override
    protected void onPrepareMenu(JMenu menu) {
        MediaPlayer mediaPlayer = Application.application().mediaPlayer();
        List<ChapterDescription> chapters = mediaPlayer.chapters().descriptions();
        if (chapters != null && !chapters.isEmpty()) {
            int i = 0;
            for (ChapterDescription chapter : chapters) {
                String name = chapter.name();
                if (name == null) {
                    name = String.format("Chapter %02d", i+1);
                }
                long offset = chapter.offset() / 1000 / 60;
                long duration = chapter.duration() / 1000 / 60;
                String s = String.format("%s %dm (%dm)", name, offset, duration);
                JMenuItem menuItem = new JMenuItem(new ChapterAction(s, i++));
                menu.add(menuItem);
            }
        }
    }
}
