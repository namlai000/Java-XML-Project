/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tblNews")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNews.findAll", query = "SELECT t FROM TblNews t")
    , @NamedQuery(name = "TblNews.findByHeaderID", query = "SELECT t FROM TblNews t WHERE t.headerID = :headerID")
    , @NamedQuery(name = "TblNews.findByContent", query = "SELECT t FROM TblNews t WHERE t.content = :content")})
public class TblNews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HeaderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer headerID;
    @Basic(optional = false)
    @Column(name = "Content")
    private String content;
    @ManyToMany(mappedBy = "tblNewsList", fetch = FetchType.LAZY)
    private List<TblImage> tblImageList;
    @ManyToMany(mappedBy = "tblNewsList", fetch = FetchType.LAZY)
    private List<TblSubCategory> tblSubCategoryList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblNews", fetch = FetchType.LAZY)
    @XmlTransient
    private TblNewsHeader tblNewsHeader;
    @JoinColumn(name = "AuthorID", referencedColumnName = "UserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblUserInfo authorID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsID", fetch = FetchType.LAZY)
    private List<TblComment> tblCommentList;

    public TblNews() {
    }

    public TblNews(Integer headerID) {
        this.headerID = headerID;
    }

    public TblNews(Integer headerID, String content) {
        this.headerID = headerID;
        this.content = content;
    }

    public Integer getHeaderID() {
        return headerID;
    }

    public void setHeaderID(Integer headerID) {
        this.headerID = headerID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlTransient
    public List<TblImage> getTblImageList() {
        return tblImageList;
    }

    public void setTblImageList(List<TblImage> tblImageList) {
        this.tblImageList = tblImageList;
    }

    @XmlTransient
    public List<TblSubCategory> getTblSubCategoryList() {
        return tblSubCategoryList;
    }

    public void setTblSubCategoryList(List<TblSubCategory> tblSubCategoryList) {
        this.tblSubCategoryList = tblSubCategoryList;
    }

    public TblNewsHeader getTblNewsHeader() {
        return tblNewsHeader;
    }

    public void setTblNewsHeader(TblNewsHeader tblNewsHeader) {
        this.tblNewsHeader = tblNewsHeader;
    }

    public TblUserInfo getAuthorID() {
        return authorID;
    }

    public void setAuthorID(TblUserInfo authorID) {
        this.authorID = authorID;
    }

    @XmlTransient
    public List<TblComment> getTblCommentList() {
        return tblCommentList;
    }

    public void setTblCommentList(List<TblComment> tblCommentList) {
        this.tblCommentList = tblCommentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (headerID != null ? headerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNews)) {
            return false;
        }
        TblNews other = (TblNews) object;
        if ((this.headerID == null && other.headerID != null) || (this.headerID != null && !this.headerID.equals(other.headerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblNews[ headerID=" + headerID + " ]";
    }
    
}
