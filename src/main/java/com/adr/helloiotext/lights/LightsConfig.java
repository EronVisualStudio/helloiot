/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adr.helloiotext.lights;

import com.adr.helloiot.ApplicationDevicesUnits;
import com.adr.helloiot.device.DeviceSimple;
import com.adr.helloiotlib.device.Device;
import com.adr.helloiot.device.DeviceSubscribe;
import com.adr.helloiot.device.MessageStatus;
import com.adr.helloiot.device.TreePublish;
import com.adr.helloiotlib.format.MiniVarBoolean;
import com.adr.helloiotlib.unit.Unit;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author adrian
 */
public class LightsConfig implements ApplicationDevicesUnits {
    
    private final List<Device> devices;
    private final List<Unit> units;
    
    public LightsConfig() {
        
        TreePublish lightactions = new TreePublish();
        lightactions.setId("lights");
        lightactions.setTopic("lightevents/devicesmanager");
        lightactions.getMessageProperties().put("mqtt.retained", MiniVarBoolean.FALSE);
        
        DeviceSubscribe lightevents = new MessageStatus();
        lightevents.setId("lightevents");
        lightevents.getMessageProperties().put("mqtt.retained", MiniVarBoolean.FALSE);
        lightevents.setTopic("lightevents/devicesmanager/#"); // The separator is already in the SYS_EVENT constant
        
        DeviceSimple secstatus = new DeviceSimple();
        secstatus.getMessageProperties().put("mqtt.retained", MiniVarBoolean.TRUE);
        secstatus.setTopic("securityevents/security/status"); // The separator is already in the SYS_EVENT constant         
        
        DeviceSimple secactions = new DeviceSimple();
        secactions.getMessageProperties().put("mqtt.retained", MiniVarBoolean.FALSE);
        secactions.setTopic("securityevents/security/action"); // The separator is already in the SYS_EVENT constant
         
        DevicesManager devsmng = new DevicesManager(secactions, secstatus);
        devsmng.setDevice(lightevents);   
        
        devices = Arrays.asList(lightactions, lightevents, secactions, secstatus);
        units = Arrays.asList(devsmng);
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
