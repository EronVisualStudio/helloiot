//    HelloIoT is a dashboard creator for MQTT
//    Copyright (C) 2017-2018 Adrián Romero Corchado.
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
package com.adr.helloiot.util;

import javafx.scene.text.Font;

/**
 *
 * @author adrian
 */
public class ExternalFonts {

    public static final String SOURCESANSPRO_BLACK;
    public static final String SOURCESANSPRO_BOLD;
    public static final String SOURCESANSPRO_REGULAR;

    static {
        try {
            SOURCESANSPRO_BLACK = Font.loadFont(ExternalFonts.class.getResourceAsStream("/com/adr/helloiot/styles/SourceSansPro-Black.ttf"), 10.0).getName();
            SOURCESANSPRO_BOLD = Font.loadFont(ExternalFonts.class.getResourceAsStream("/com/adr/helloiot/styles/SourceSansPro-Bold.ttf"), 10.0).getName();
            SOURCESANSPRO_REGULAR = Font.loadFont(ExternalFonts.class.getResourceAsStream("/com/adr/helloiot/styles/SourceSansPro-Regular.ttf"), 10.0).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
