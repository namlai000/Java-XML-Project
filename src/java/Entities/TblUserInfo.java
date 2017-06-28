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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
@XmlType(name = "TblUserInfo", propOrder = {
    "id",
    "firstname",
    "middlename",
    "lastname",
    "address",
    "phone",
    "iDNumber",
    "imageID",
    "birthday",
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUserInfo.findAll", query = "SELECT t FROM TblUserInfo t")
    , @NamedQuery(name = "TblUserInfo.findById", query = "SELECT t FROM TblUserInfo t WHERE t.id = :id")
    , @NamedQuery(name = "TblUserInfo.findByFirstname", query = "SELECT t FROM TblUserInfo t WHERE t.firstname = :firstname")
    , @NamedQuery(name = "TblUserInfo.findByMiddlename", query = "SELECT t FROM TblUserInfo t WHERE t.middlename = :middlename")
    , @NamedQuery(name = "TblUserInfo.findByLastname", query = "SELECT t FROM TblUserInfo t WHERE t.lastname = :lastname")
    , @NamedQuery(name = "TblUserInfo.findByAddress", query = "SELECT t FROM TblUserInfo t WHERE t.address = :address")
    , @NamedQuery(name = "TblUserInfo.findByPhone", query = "SELECT t FROM TblUserInfo t WHERE t.phone = :phone")
    , @NamedQuery(name = "TblUserInfo.findByIDNumber", query = "SELECT t FROM TblUserInfo t WHERE t.iDNumber = :iDNumber")
    , @NamedQuery(name = "TblUserInfo.findByImageID", query = "SELECT t FROM TblUserInfo t WHERE t.imageID = :imageID")
    , @NamedQuery(name = "TblUserInfo.findByBirthday", query = "SELECT t FROM TblUserInfo t WHERE t.birthday = :birthday")})
public class TblUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
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
    @Column(name = "ImageID")
    private Integer imageID;
    @Column(name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @OneToMany(mappedBy = "authorID")
    @XmlTransient
    private List<TblNews> tblNewsList;
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    @XmlTransient
    private TblUser userId;

    public TblUserInfo() {
    }

    public TblUserInfo(Integer id) {
        this.id = id;
    }

    public TblUserInfo(Integer id, String firstname, String lastname, String iDNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.iDNumber = iDNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @XmlTransient
    public List<TblNews> getTblNewsList() {
        return tblNewsList;
    }

    public void setTblNewsList(List<TblNews> tblNewsList) {
        this.tblNewsList = tblNewsList;
    }

    public TblUser getUserId() {
        return userId;
    }

    public void setUserId(TblUser userId) {
        this.userId = userId;
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
        if (!(object instanceof TblUserInfo)) {
            return false;
        }
        TblUserInfo other = (TblUserInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblUserInfo[ id=" + id + " ]";
    }

}
