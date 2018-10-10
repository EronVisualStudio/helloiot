/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adr.helloiotext.security;

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
public class SecurityUnitPages implements ApplicationUnitPages {
    
    private final List<UnitPage> unitpages;
    
    public SecurityUnitPages() {
        
        ResourceBundle resources = ResourceBundle.getBundle("com/adr/helloiotext/fxml/security");
        
        UnitPage security = new UnitPage("security", IconBuilder.create(FontAwesome.FA_KEY, 24.0).build(), resources.getString("page.security"));
        security.setEmptyLabel(resources.getString("label.locked"));
        security.setMaxSize(400.0, 150.0);        
        security.setSystem(true);

        UnitPage arming = new UnitPage("security_arming", IconBuilder.create(FontAwesome.FA_KEY, 24.0).build(), resources.getString("page.security"));
        arming.setEmptyLabel(resources.getString("label.locked"));
        arming.setMaxSize(400.0, 150.0);
        arming.setSystem(true);

        UnitPage locked = new UnitPage("security_locked", IconBuilder.create(FontAwesome.FA_LOCK, 24.0).build(), resources.getString("page.securitylocked"));
        locked.setEmptyLabel(resources.getString("label.locked"));
        locked.setSystem(true);

        UnitPage emergency = new UnitPage("emergency", IconBuilder.create(FontAwesome.FA_HEARTBEAT, 24.0).build(), resources.getString("page.emergency"));
        emergency.setEmptyLabel(resources.getString("label.locked"));
        emergency.setMaxSize(400.0, 150.0);        
        emergency.setSystem(true);
        
        unitpages = Arrays.asList(security, arming, locked, emergency);
    }   

    @Override
    public List<UnitPage> getUnitPages() {
        return unitpages;
    }    
}
