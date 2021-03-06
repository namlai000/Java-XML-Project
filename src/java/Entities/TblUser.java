/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "tblUser")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "id",
    "username",
    "password",
    "role"
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUser.findAll", query = "SELECT t FROM TblUser t")
    , @NamedQuery(name = "TblUser.findById", query = "SELECT t FROM TblUser t WHERE t.id = :id")
    , @NamedQuery(name = "TblUser.findByUsername", query = "SELECT t FROM TblUser t WHERE t.username = :username")
    , @NamedQuery(name = "TblUser.findByPassword", query = "SELECT t FROM TblUser t WHERE t.password = :password")})
public class TblUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @JoinColumn(name = "Role", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblRole role;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblUser", fetch = FetchType.LAZY)
    @XmlTransient
    private TblUserInfo tblUserInfo;

    public TblUser() {
    }

    public TblUser(Integer id) {
        this.id = id;
    }

    public TblUser(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TblRole getRole() {
        return role;
    }

    public void setRole(TblRole role) {
        this.role = role;
    }

    public TblUserInfo getTblUserInfo() {
        return tblUserInfo;
    }

    public void setTblUserInfo(TblUserInfo tblUserInfo) {
        this.tblUserInfo = tblUserInfo;
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
        if (!(object instanceof TblUser)) {
            return false;
        }
        TblUser other = (TblUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblUser[ id=" + id + " ]";
    }
    
}
