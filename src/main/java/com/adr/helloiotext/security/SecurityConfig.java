/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adr.helloiotext.security;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.helloiot.ApplicationDevicesUnits;
import com.adr.helloiot.device.DeviceSimple;
import com.adr.helloiot.device.TransmitterSimple;
import com.adr.helloiotlib.device.Device;
import com.adr.helloiot.unit.ButtonBase;
import com.adr.helloiot.unit.LabelSection;
import com.adr.helloiot.unit.UnitPage;
import com.adr.helloiotlib.format.MiniVarBoolean;
import com.adr.helloiotlib.unit.Unit;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author adrian
 */
public class SecurityConfig implements ApplicationDevicesUnits {
     
    private final List<Device> devices;
    private final List<Unit> units;
    
    public SecurityConfig() {

        ResourceBundle resources = ResourceBundle.getBundle("com/adr/helloiotext/fxml/security");
        
        DeviceSimple secactions = new DeviceSimple();
        secactions.setId("security");
        secactions.getMessageProperties().put("mqtt.retained", MiniVarBoolean.FALSE);
        secactions.setTopic("securityevents/security/action"); // The separator is already in the SYS_EVENT constant
        
        DeviceSimple secstatus = new DeviceSimple();
        secstatus.getMessageProperties().put("mqtt.retained", MiniVarBoolean.TRUE);
        secstatus.setTopic("securityevents/security/status"); // The separator is already in the SYS_EVENT constant 
        
        TransmitterSimple secalarm = new TransmitterSimple();
        secalarm.setTopic("home/master/alarm");
        
        SecurityManager sec = new SecurityManager(secactions, secstatus, secalarm);
        sec.setDevice(secactions);
        
        // Arming security
        LabelSection arminglabel = new LabelSection();
        UnitPage.setPage(arminglabel, "security_arming");
        UnitPage.setLayout(arminglabel, "StartFull"); 
        arminglabel.setText(resources.getString("label.securityarming"));
   
        ButtonBase cancelbutton = new ButtonBase() {
            @Override protected void doRun(ActionEvent event) {
                secactions.sendStatus("DISARMED");
            }
        };
        UnitPage.setPage(cancelbutton, "security_arming");
        UnitPage.setLayout(cancelbutton, "StartFull"); 
        cancelbutton.setLabel("");
        cancelbutton.setText(resources.getString("button.cancelarming"));
        cancelbutton.setStyle("-fx-background-color: transparent;");        
        cancelbutton.setGraphic(IconBuilder.create(FontAwesome.FA_TIMES, 48.0).color(Color.RED).shine(Color.WHITE).build());
        
        // Armed security
        LabelSection armedlabel = new LabelSection();
        UnitPage.setPage(armedlabel, "security");
        UnitPage.setLayout(armedlabel, "StartFull"); 
        armedlabel.setText(resources.getString("label.securityarmed"));
        
        ButtonBase disarmbutton = new ButtonBase() {
            @Override protected void doRun(ActionEvent event) {
                secactions.sendStatus("DISARMED");
            }
        };
        UnitPage.setPage(disarmbutton, "security");
        UnitPage.setLayout(disarmbutton, "StartFull");  
        disarmbutton.setLabel("");
        disarmbutton.setText(resources.getString("button.disarm"));
        disarmbutton.setStyle("-fx-background-color: transparent;");
        disarmbutton.setSecurityKey("mainsecuritykey");
        disarmbutton.setGraphic(IconBuilder.create(FontAwesome.FA_KEY, 48.0).color(Color.GREEN).shine(Color.WHITE).build());
        
        devices = Arrays.asList(secactions, secstatus, secalarm);
        units = Arrays.asList(sec, 
                arminglabel, cancelbutton,
                armedlabel, disarmbutton);
    }
        
    @Override
    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public List<Unit> getUnits() {
        return units;
    }
}
