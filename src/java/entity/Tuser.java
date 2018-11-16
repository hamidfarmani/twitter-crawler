/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
@Table(name = "TUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tuser.findAll", query = "SELECT t FROM Tuser t"),
    @NamedQuery(name = "Tuser.findById", query = "SELECT t FROM Tuser t WHERE t.id = :id"),
    @NamedQuery(name = "Tuser.findByFullname", query = "SELECT t FROM Tuser t WHERE t.fullname = :fullname"),
    @NamedQuery(name = "Tuser.findByUsername", query = "SELECT t FROM Tuser t WHERE t.username = :username"),
    @NamedQuery(name = "Tuser.findByTypeid", query = "SELECT t FROM Tuser t WHERE t.typeid = :typeid"),
    @NamedQuery(name = "Tuser.findByDescription", query = "SELECT t FROM Tuser t WHERE t.description = :description"),
    @NamedQuery(name = "Tuser.findByDto", query = "SELECT t FROM Tuser t WHERE t.dto = :dto")})
public class Tuser implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "FULLNAME")
    private String fullname;
    @Size(max = 50)
    @Column(name = "USERNAME")
    private String username;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "TYPEID", referencedColumnName = "ID")
    private Ttype typeid;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dto;

    public Tuser() {
    }

    public Tuser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Ttype getTypeid() {
        return typeid;
    }

    public void setTypeid(Ttype typeid) {
        this.typeid = typeid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDto() {
        return dto;
    }

    public void setDto(Date dto) {
        this.dto = dto;
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
        if (!(object instanceof Tuser)) {
            return false;
        }
        Tuser other = (Tuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Tuser[ id=" + id + " ]";
    }

}
