//    HelloIoT is a dashboard creator for MQTT
//    Copyright (C) 2017 Adrián Romero Corchado.
//
//    This file is part of HelloIot.
//
//    HelloIot is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    HelloIot is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with HelloIot.  If not, see <http://www.gnu.org/licenses/>.
//
package com.adr.helloiot.graphic;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.fonticon.IconFont;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 *
 * @author adrian
 */
public class Power extends IconSwitch {

    private IconFont icon;
    private Color color;

    public Power(IconFont icon, Color color) {
        this.icon = icon;
        this.color = color;
    }

    public Power(IconFont icon) {
        this(icon, Color.LIME);
    }

    public Power() {
        this(FontAwesome.FA_POWER_OFF, Color.LIME);
    }

    @Override
    protected Node buildIconOn() {
        return IconBuilder.create(icon, 48.0).color(color).build();
    }

    @Override
    protected Node buildIconOff() {
        return IconBuilder.create(icon, 48.0).color(Color.DARKGRAY).build();
    }
}
