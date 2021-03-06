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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thegu
 */
@Entity
@Table(name = "tblNewsHeader")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNewsHeader.findAll", query = "SELECT t FROM TblNewsHeader t")
    , @NamedQuery(name = "TblNewsHeader.findById", query = "SELECT t FROM TblNewsHeader t WHERE t.id = :id")
    , @NamedQuery(name = "TblNewsHeader.findByTittle", query = "SELECT t FROM TblNewsHeader t WHERE t.tittle = :tittle")
    , @NamedQuery(name = "TblNewsHeader.findByDescription", query = "SELECT t FROM TblNewsHeader t WHERE t.description = :description")
    , @NamedQuery(name = "TblNewsHeader.findByDate", query = "SELECT t FROM TblNewsHeader t WHERE t.date = :date")})
public class TblNewsHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Tittle")
    private String tittle;
    @Basic(optional = false)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "Id", referencedColumnName = "HeaderID", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TblNews tblNews;
    @Basic(optional = false)
    @Column(name = "Type")
    private Integer type;
    @Basic(optional = false)
    @Column(name = "Views")
    @XmlElement(defaultValue = "0")
    private int views;

    public TblNewsHeader() {
    }

    public TblNewsHeader(Integer id) {
        this.id = id;
    }

    public TblNewsHeader(Integer id, String tittle, String description, Date date) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TblNews getTblNews() {
        return tblNews;
    }

    public void setTblNews(TblNews tblNews) {
        this.tblNews = tblNews;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        if (!(object instanceof TblNewsHeader)) {
            return false;
        }
        TblNewsHeader other = (TblNewsHeader) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblNewsHeader[ id=" + id + " ]";
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
    
}
