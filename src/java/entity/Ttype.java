/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hamid
 */
@Entity
@Table(name = "TTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ttype.findAll", query = "SELECT t FROM Ttype t"),
    @NamedQuery(name = "Ttype.findById", query = "SELECT t FROM Ttype t WHERE t.id = :id"),
    @NamedQuery(name = "Ttype.findByName", query = "SELECT t FROM Ttype t WHERE t.name = :name"),
    @NamedQuery(name = "Ttype.findByDescription", query = "SELECT t FROM Ttype t WHERE t.description = :description")})
public class Ttype implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;

    public Ttype() {
    }

    public Ttype(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Ttype)) {
            return false;
        }
        Ttype other = (Ttype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Ttype[ id=" + id + " ]";
    }
    
}
