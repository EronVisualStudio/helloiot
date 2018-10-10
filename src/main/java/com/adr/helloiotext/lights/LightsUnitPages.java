/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adr.helloiotext.lights;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.helloiot.ApplicationUnitPages;
import com.adr.helloiot.unit.UnitPage;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author adrian
 */
public class LightsUnitPages implements ApplicationUnitPages {
    
    private final List<UnitPage> unitpages;
    
    public LightsUnitPages() {
        
        ResourceBundle resources = ResourceBundle.getBundle("com/adr/helloiotext/fxml/light");
        
        unitpages = Arrays.asList(
                new UnitPage("light", IconBuilder.create(FontAwesome.FA_LIGHTBULB_O, 24.0).build(), resources.getString("page.light")));
    } 

    @Override
    public List<UnitPage> getUnitPages() {
        return unitpages;
    }
}
