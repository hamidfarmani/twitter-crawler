/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hamid
 */
@Entity
@Table(name = "TTWIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ttwit.findAll", query = "SELECT t FROM Ttwit t"),
    @NamedQuery(name = "Ttwit.findById", query = "SELECT t FROM Ttwit t WHERE t.id = :id"),
    @NamedQuery(name = "Ttwit.findByDescription", query = "SELECT t FROM Ttwit t WHERE t.description = :description"),
    @NamedQuery(name = "Ttwit.findByCreationdate", query = "SELECT t FROM Ttwit t WHERE t.creationdate = :creationdate"),
    @NamedQuery(name = "Ttwit.findByUserid", query = "SELECT t FROM Ttwit t WHERE t.userid.id = :userid")})
public class Ttwit implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 300)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    private Tuser userid;

    public Ttwit() {
    }

    public Ttwit(Integer id) {
        this.id = id;
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

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Tuser getUserid() {
        return userid;
    }

    public void Tuser(Tuser userid) {
        this.userid = userid;
    }

    public void setUserid(Tuser userid) {
        this.userid = userid;
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
        if (!(object instanceof Ttwit)) {
            return false;
        }
        Ttwit other = (Ttwit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Ttwit[ id=" + id + " ]";
    }

}
