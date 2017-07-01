/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author thegu
 */
@Entity
@Table(name = "tblUserInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "userId",
    "firstname",
    "middlename",
    "lastname",
    "address",
    "phone",
    "iDNumber",
    "birthday",
    "createDate",
    "imageID",
    "tblUser"
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUserInfo.findAll", query = "SELECT t FROM TblUserInfo t")
    , @NamedQuery(name = "TblUserInfo.findByUserId", query = "SELECT t FROM TblUserInfo t WHERE t.userId = :userId")
    , @NamedQuery(name = "TblUserInfo.findByFirstname", query = "SELECT t FROM TblUserInfo t WHERE t.firstname = :firstname")
    , @NamedQuery(name = "TblUserInfo.findByMiddlename", query = "SELECT t FROM TblUserInfo t WHERE t.middlename = :middlename")
    , @NamedQuery(name = "TblUserInfo.findByLastname", query = "SELECT t FROM TblUserInfo t WHERE t.lastname = :lastname")
    , @NamedQuery(name = "TblUserInfo.findByAddress", query = "SELECT t FROM TblUserInfo t WHERE t.address = :address")
    , @NamedQuery(name = "TblUserInfo.findByPhone", query = "SELECT t FROM TblUserInfo t WHERE t.phone = :phone")
    , @NamedQuery(name = "TblUserInfo.findByIDNumber", query = "SELECT t FROM TblUserInfo t WHERE t.iDNumber = :iDNumber")
    , @NamedQuery(name = "TblUserInfo.findByBirthday", query = "SELECT t FROM TblUserInfo t WHERE t.birthday = :birthday")
    , @NamedQuery(name = "TblUserInfo.findByCreateDate", query = "SELECT t FROM TblUserInfo t WHERE t.createDate = :createDate")})
public class TblUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "Firstname")
    private String firstname;
    @Column(name = "Middlename")
    private String middlename;
    @Basic(optional = false)
    @Column(name = "Lastname")
    private String lastname;
    @Column(name = "Address")
    private String address;
    @Column(name = "Phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "IDNumber")
    private String iDNumber;
    @Column(name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @OneToMany(mappedBy = "authorID", fetch = FetchType.LAZY)
    @XmlTransient
    private List<TblNews> tblNewsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID", fetch = FetchType.LAZY)
    @XmlTransient
    private List<TblComment> tblCommentList;
    @JoinColumn(name = "ImageID", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblImage imageID;
    @JoinColumn(name = "UserId", referencedColumnName = "Id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TblUser tblUser;

    public TblUserInfo() {
    }

    public TblUserInfo(Integer userId) {
        this.userId = userId;
    }

    public TblUserInfo(Integer userId, String firstname, String lastname, String iDNumber) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.iDNumber = iDNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIDNumber() {
        return iDNumber;
    }

    public void setIDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @XmlTransient
    public List<TblNews> getTblNewsList() {
        return tblNewsList;
    }

    public void setTblNewsList(List<TblNews> tblNewsList) {
        this.tblNewsList = tblNewsList;
    }

    @XmlTransient
    public List<TblComment> getTblCommentList() {
        return tblCommentList;
    }

    public void setTblCommentList(List<TblComment> tblCommentList) {
        this.tblCommentList = tblCommentList;
    }

    public TblImage getImageID() {
        return imageID;
    }

    public void setImageID(TblImage imageID) {
        this.imageID = imageID;
    }

    public TblUser getTblUser() {
        return tblUser;
    }

    public void setTblUser(TblUser tblUser) {
        this.tblUser = tblUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUserInfo)) {
            return false;
        }
        TblUserInfo other = (TblUserInfo) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblUserInfo[ userId=" + userId + " ]";
    }
    
}
