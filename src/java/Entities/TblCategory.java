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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thegu
 */
@Entity
@Table(name = "tblCategory")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCategory.findAll", query = "SELECT t FROM TblCategory t")
    , @NamedQuery(name = "TblCategory.findById", query = "SELECT t FROM TblCategory t WHERE t.id = :id")
    , @NamedQuery(name = "TblCategory.findByMainCategoryName", query = "SELECT t FROM TblCategory t WHERE t.mainCategoryName = :mainCategoryName")})
public class TblCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "MainCategoryName")
    private String mainCategoryName;
    @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
    @XmlTransient
    private List<TblSubCategory> tblSubCategoryList;

    public TblCategory() {
    }

    public TblCategory(Integer id) {
        this.id = id;
    }

    public TblCategory(Integer id, String mainCategoryName) {
        this.id = id;
        this.mainCategoryName = mainCategoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    @XmlTransient
    public List<TblSubCategory> getTblSubCategoryList() {
        return tblSubCategoryList;
    }

    public void setTblSubCategoryList(List<TblSubCategory> tblSubCategoryList) {
        this.tblSubCategoryList = tblSubCategoryList;
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
