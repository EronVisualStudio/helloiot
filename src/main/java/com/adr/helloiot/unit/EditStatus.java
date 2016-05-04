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

package com.adr.helloiot.unit;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.hellocommon.dialog.MessageUtils;
import com.adr.hellocommon.utils.AbstractController;
import com.adr.helloiot.EventMessage;
import com.adr.helloiot.HelloIoTAppPublic;
import com.adr.helloiot.device.DeviceSimple;
import com.google.common.base.Strings;
import com.google.common.eventbus.Subscribe;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author adrian
 */
public class EditStatus extends VBox implements Unit, AbstractController {
    
    protected ResourceBundle resources = ResourceBundle.getBundle("com/adr/helloiot/fxml/basic");
    
    @FXML private Label field;
    @FXML private TextInputControl statusview;
    @FXML private Button editaction;
    @FXML private TextInputControl statusedit;
    @FXML private Button okaction;
    @FXML private Button cancelaction;  
    @FXML private Pane boxview;
    @FXML private Pane boxedit;
    
    private DeviceSimple device = null;
    
    public EditStatus() {
        loadFXML();
    }   
    
    protected void loadFXML() {        
        this.load("/com/adr/helloiot/fxml/editstatus.fxml");   
    }   
    
    @FXML public void initialize() {
        editaction.setGraphic(IconBuilder.create(FontAwesome.FA_EDIT, 16).build());
        okaction.setGraphic(IconBuilder.create(FontAwesome.FA_CHECK, 16).build());
        cancelaction.setGraphic(IconBuilder.create(FontAwesome.FA_REMOVE, 16).build());
        setDisable(true);
    }
    
    @Subscribe
    public void receivedStatus(EventMessage message) {
        Platform.runLater(() -> updateStatus(message.getMessage()));  
    }

    private void updateStatus(byte[] status) {
        statusview.setText(device.getFormat().format(status));
    }  
    
    @Override
    public void construct(HelloIoTAppPublic app) {
        Unit.super.construct(app);
        device.subscribeStatus(this);
        updateStatus(null);        
    }

    @Override
    public void destroy() {
        Unit.super.destroy();
        device.unsubscribeStatus(this);    
    }    
    
    @Override
    public void start() {
        setDisable(false);
    }

    @Override
    public void stop() {
        setDisable(true);
    }

    @Override
    public Node getNode() {
        return this;
    }
    
    public void setDevice(DeviceSimple device) {
        this.device = device;
        if (Strings.isNullOrEmpty(getLabel())) {
            setLabel(device.getProperties().getProperty("label"));
        }   
    }
    
    public DeviceSimple getDevice() {
        return device;
    }
    
    public void setLabel(String label) {
        field.setText(label);
    }
    
    public String getLabel() {
        return field.getText();
    }
    
    public void setReadOnly(boolean value) {
        editaction.setVisible(!value);
    }
    
    public boolean isReadOnly() {
        return !editaction.isVisible();
    }
    
    @FXML void onCancelEvent(ActionEvent event) {
        boxedit.setVisible(false);
        boxview.setVisible(true);
    }

    @FXML void onEditEvent(ActionEvent event) {
        boxview.setVisible(false);
        boxedit.setVisible(true);
        statusedit.setText(device.getFormat().format(device.readStatus()));
        statusedit.selectAll();
        statusedit.requestFocus();
    }

    @FXML void onOkEvent(ActionEvent event) {
        try{
            boxedit.setVisible(false);
            boxview.setVisible(true);
            device.sendStatus(device.getFormat().parse(statusedit.getText()));
        } catch (IllegalArgumentException ex) {
            MessageUtils.showException(MessageUtils.getRoot(this), resources.getString("label.sendstatus"), resources.getString("message.valueerror"), ex);    
        }    }
    
    @FXML
    void onEnterEvent(ActionEvent event) {
        onOkEvent(event);
    }    
}
