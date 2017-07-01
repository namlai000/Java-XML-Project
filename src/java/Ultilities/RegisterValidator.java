/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultilities;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

/**
 *
 * @author thegu
 */
public class RegisterValidator implements ValidationEventHandler {

    private boolean hasError = false;
    private String error = "";

    @Override
    public boolean handleEvent(ValidationEvent event) {
        if (event.getSeverity() == event.FATAL_ERROR || event.getSeverity() == event.ERROR) {
            error += event.getMessage() + "<br/>";
            hasError = true;
        }
        return true;
    }
    
        
    public boolean getError() {
        return hasError;
    }
    
    public String errorMessage() {
        return error;
    }
}
