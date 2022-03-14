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

package com.dimelthoz.dygi.interfaceapplication.view.action.mediaplayer;

import com.dimelthoz.dygi.interfaceapplication.Application;
import uk.co.caprica.vlcj.player.renderer.RendererItem;
import com.dimelthoz.dygi.interfaceapplication.view.action.StandardAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

public final class RendererAction extends StandardAction {

    private final RendererItem renderer;

    public RendererAction(String name, RendererItem renderer) {
        super(name);
        this.renderer = renderer;
        try {
            putValue(Action.SMALL_ICON, new ImageIcon(ImageIO.read(new URL(renderer.iconUri()))));
        }
        catch (IOException e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Application.application().setRenderer(renderer);
    }

}
