/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thegu
 */
@Entity
@Table(name = "tblComment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblComment.findAll", query = "SELECT t FROM TblComment t")
    , @NamedQuery(name = "TblComment.findById", query = "SELECT t FROM TblComment t WHERE t.id = :id")
    , @NamedQuery(name = "TblComment.findByContent", query = "SELECT t FROM TblComment t WHERE t.content = :content")
    , @NamedQuery(name = "TblComment.findByUploadDate", query = "SELECT t FROM TblComment t WHERE t.uploadDate = :uploadDate")})
public class TblComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Content")
    private String content;
    @Column(name = "UploadDate")
    @Temporal(TemporalType.DATE)
    private Date uploadDate;
    @JoinColumn(name = "NewsID", referencedColumnName = "HeaderID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblNews newsID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblUserInfo userID;

    public TblComment() {
    }

    public TblComment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public TblNews getNewsID() {
        return newsID;
    }

    public void setNewsID(TblNews newsID) {
        this.newsID = newsID;
    }

    public TblUserInfo getUserID() {
        return userID;
    }

    public void setUserID(TblUserInfo userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblComment)) {
            return false;
        }
        TblComment other = (TblComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblComment[ id=" + id + " ]";
    }
    
}
