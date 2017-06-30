/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import Entities.TblComment;
import Entities.TblCommentCustom;
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
@XmlRootElement(name = "TblComments")
public class TblCommentWrapper implements Serializable {
    
    @XmlElement(name = "TblComment")
    private List<TblCommentCustom> listOfComments;

    public TblCommentWrapper() {
    }

    public TblCommentWrapper(List<TblCommentCustom> listOfComments) {
        this.listOfComments = listOfComments;
    }

    public List<TblCommentCustom> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(List<TblCommentCustom> listOfComments) {
        this.listOfComments = listOfComments;
    }
}
