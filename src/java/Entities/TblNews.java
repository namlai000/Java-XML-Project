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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tblNews")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TblNews", propOrder = {
    "newsID",
    "content",
    "tblImageList",
    "tblCategoryList",
    "headerID",
    "authorID"
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNews.findAll", query = "SELECT t FROM TblNews t")
    , @NamedQuery(name = "TblNews.findByNewsID", query = "SELECT t FROM TblNews t WHERE t.newsID = :newsID")})
public class TblNews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NewsID")
    private Integer newsID;
    @Basic(optional = false)
    @Lob
    @Column(name = "Content")
    private String content;
    @ManyToMany(mappedBy = "tblNewsList")
    @XmlElement(name = "TblImage")
    private List<TblImage> tblImageList;
    @ManyToMany(mappedBy = "tblNewsList")
    @XmlElement(name = "TblCategory")
    private List<TblCategory> tblCategoryList;
    @JoinColumn(name = "HeaderID", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private TblNewsHeader headerID;
    @JoinColumn(name = "AuthorID", referencedColumnName = "Id")
    @ManyToOne
    private TblUserInfo authorID;

    public TblNews() {
    }

    public TblNews(Integer newsID) {
        this.newsID = newsID;
    }

    public TblNews(Integer newsID, String content) {
        this.newsID = newsID;
        this.content = content;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
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
    public List<TblCategory> getTblCategoryList() {
        return tblCategoryList;
    }

    public void setTblCategoryList(List<TblCategory> tblCategoryList) {
        this.tblCategoryList = tblCategoryList;
    }

    public TblNewsHeader getHeaderID() {
        return headerID;
    }

    public void setHeaderID(TblNewsHeader headerID) {
        this.headerID = headerID;
    }

    public TblUserInfo getAuthorID() {
        return authorID;
    }

    public void setAuthorID(TblUserInfo authorID) {
        this.authorID = authorID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newsID != null ? newsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNews)) {
            return false;
        }
        TblNews other = (TblNews) object;
        if ((this.newsID == null && other.newsID != null) || (this.newsID != null && !this.newsID.equals(other.newsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblNews[ newsID=" + newsID + " ]";
    }

}
