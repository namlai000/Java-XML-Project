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
@Table(name = "tblCategory")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TblCategory", propOrder = {
    "id",
    "categoryName",
    "tblNewsList"
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCategory.findAll", query = "SELECT t FROM TblCategory t")
    , @NamedQuery(name = "TblCategory.findById", query = "SELECT t FROM TblCategory t WHERE t.id = :id")
    , @NamedQuery(name = "TblCategory.findByCategoryName", query = "SELECT t FROM TblCategory t WHERE t.categoryName = :categoryName")})
public class TblCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CategoryName")
    private String categoryName;
    @JoinTable(name = "tblNewsCategory", joinColumns = {
        @JoinColumn(name = "CategoryID", referencedColumnName = "Id")}, inverseJoinColumns = {
        @JoinColumn(name = "NewsID", referencedColumnName = "NewsID")})
    @ManyToMany
    @XmlElement(name = "TblNews")
    private List<TblNews> tblNewsList;

    public TblCategory() {
    }

    public TblCategory(Integer id) {
        this.id = id;
    }

    public TblCategory(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
        if (!(object instanceof TblCategory)) {
            return false;
        }
        TblCategory other = (TblCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblCategory[ id=" + id + " ]";
    }
    
}
