/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thegu
 */
@Entity
@Table(name = "tblImage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblImage.findAll", query = "SELECT t FROM TblImage t")
    , @NamedQuery(name = "TblImage.findById", query = "SELECT t FROM TblImage t WHERE t.id = :id")
    , @NamedQuery(name = "TblImage.findByDescription", query = "SELECT t FROM TblImage t WHERE t.description = :description")
    , @NamedQuery(name = "TblImage.findByOrigin", query = "SELECT t FROM TblImage t WHERE t.origin = :origin")
    , @NamedQuery(name = "TblImage.findByLink", query = "SELECT t FROM TblImage t WHERE t.link = :link")})
public class TblImage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Description")
    private String description;
    @Column(name = "Origin")
    private String origin;
    @Basic(optional = false)
    @Column(name = "Link")
    private String link;
    @JoinTable(name = "tblNewsImage", joinColumns = {
        @JoinColumn(name = "ImageID", referencedColumnName = "Id")}, inverseJoinColumns = {
        @JoinColumn(name = "NewsID", referencedColumnName = "NewsID")})
    @ManyToMany
    private List<TblNews> tblNewsList;

    public TblImage() {
    }

    public TblImage(Integer id) {
        this.id = id;
    }

    public TblImage(Integer id, String link) {
        this.id = id;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @XmlTransient
    public List<TblNews> getTblNewsList() {
        return tblNewsList;
    }

    public void setTblNewsList(List<TblNews> tblNewsList) {
        this.tblNewsList = tblNewsList;
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
        if (!(object instanceof TblImage)) {
            return false;
        }
        TblImage other = (TblImage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblImage[ id=" + id + " ]";
    }
    
}
