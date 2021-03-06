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

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dimelthoz.dygi.interfaceapplication.Application;
import com.dimelthoz.dygi.interfaceapplication.time.Time;
import com.dimelthoz.dygi.interfaceapplication.view.BorderedStandardLabel;
import net.miginfocom.swing.MigLayout;
import com.dimelthoz.dygi.interfaceapplication.event.TickEvent;

import com.google.common.eventbus.Subscribe;

final class StatusBar extends JPanel {

    private final JLabel titleLabel;

    private final JLabel rateLabel;

    private final JLabel timeLabel;

    private long time;

    private long duration;

    StatusBar() {
        titleLabel = new BorderedStandardLabel();
        rateLabel = new BorderedStandardLabel();
        timeLabel = new BorderedStandardLabel();

        setLayout(new MigLayout("fillx, insets 2 n n n", "[grow]16[][]", "[]"));
        add(titleLabel, "grow");
        add(rateLabel);
        add(timeLabel);

        Application.application().subscribe(this);
    }

    void setTitle(String title) {
        titleLabel.setText(title);
    }

    void setRate(String rate) {
        rateLabel.setText(rate);
    }

    void setTime(long time) {
        this.time = time;
    }

    void setDuration(long duration) {
        this.duration = duration;
        refresh();
    }

    void clear() {
        titleLabel.setText(null);
        rateLabel.setText(null);
        timeLabel.setText(null);
    }

    void refresh() {
        timeLabel.setText(String.format("%s/%s", Time.formatTime(time), Time.formatTime(duration)));
    }

    @Subscribe
    public void onTick(TickEvent tick) {
        refresh();
    }
}
