/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import Entities.TblNewsHeader;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author thegu
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listOfHeaders"
})
@XmlRootElement(name = "TblNewsHeaders")
public class TblNewsHeaderWrapper implements Serializable {
    
    @XmlElement(name = "TblNewsHeader")
    private List<TblNewsHeader> listOfHeaders;
    
    public TblNewsHeaderWrapper() {
    }

    public TblNewsHeaderWrapper(List<TblNewsHeader> listOfHeaders) {
        this.listOfHeaders = listOfHeaders;
    }

    public List<TblNewsHeader> getListOfHeaders() {
        return listOfHeaders;
    }

    public void setListOfHeaders(List<TblNewsHeader> listOfHeaders) {
        this.listOfHeaders = listOfHeaders;
    }
}
