/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import Entities.TblComment;
import Entities.TblCommentCustom;
import Entities.TblNewsHeader;
import Entities.TblUser;
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
@XmlRootElement(name = "TblUsers")
public class TblUserWrapper implements Serializable {
    
    @XmlElement(name = "TblUser")
    private List<TblUser> listUsers;

    public TblUserWrapper() {
    }

    public TblUserWrapper(List<TblUser> listUsers) {
        this.listUsers = listUsers;
    }

    public List<TblUser> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<TblUser> listUsers) {
        this.listUsers = listUsers;
    }
   
}
