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
import Entities.TblUserInfo;
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
@XmlRootElement(name = "TblUserInfos")
public class TblUserInfoWrapper implements Serializable {
    
    @XmlElement(name = "TblUserInfo")
    private List<TblUserInfo> listUsers;

    public TblUserInfoWrapper() {
    }

    public TblUserInfoWrapper(List<TblUserInfo> listUsers) {
        this.listUsers = listUsers;
    }

    public List<TblUserInfo> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<TblUserInfo> listUsers) {
        this.listUsers = listUsers;
    }
   
}
