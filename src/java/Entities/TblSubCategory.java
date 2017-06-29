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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tblSubCategory")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSubCategory.findAll", query = "SELECT t FROM TblSubCategory t")
    , @NamedQuery(name = "TblSubCategory.findById", query = "SELECT t FROM TblSubCategory t WHERE t.id = :id")
    , @NamedQuery(name = "TblSubCategory.findBySubCategoryName", query = "SELECT t FROM TblSubCategory t WHERE t.subCategoryName = :subCategoryName")})
public class TblSubCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "SubCategoryName")
    private String subCategoryName;
    @JoinTable(name = "tblNewsCategory", joinColumns = {
        @JoinColumn(name = "CategoryID", referencedColumnName = "Id")}, inverseJoinColumns = {
        @JoinColumn(name = "NewsID", referencedColumnName = "HeaderID")})
    @OneToMany(mappedBy = "catID", fetch = FetchType.LAZY)
    @XmlTransient
    private List<TblNews> tblNewsList;
    @JoinColumn(name = "CategoryId", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblCategory categoryId;

    public TblSubCategory() {
    }

    public TblSubCategory(Integer id) {
        this.id = id;
    }

    public TblSubCategory(Integer id, String subCategoryName) {
        this.id = id;
        this.subCategoryName = subCategoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    @XmlTransient
    public List<TblNews> getTblNewsList() {
        return tblNewsList;
    }

    public void setTblNewsList(List<TblNews> tblNewsList) {
        this.tblNewsList = tblNewsList;
    }

    public TblCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(TblCategory categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof TblSubCategory)) {
            return false;
        }
        TblSubCategory other = (TblSubCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblSubCategory[ id=" + id + " ]";
    }
}
