/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ser;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vit
 */
@Entity
@Table(name = "memory_clock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MemoryClock.findAll", query = "SELECT m FROM MemoryClock m"),
    @NamedQuery(name = "MemoryClock.findByIdMemoryClock", query = "SELECT m FROM MemoryClock m WHERE m.idMemoryClock = :idMemoryClock"),
    @NamedQuery(name = "MemoryClock.findByMemoryClock", query = "SELECT m FROM MemoryClock m WHERE m.memoryClock = :memoryClock")})
public class MemoryClock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_memory_clock")
    private Integer idMemoryClock;
    @Column(name = "memory_clock")
    private Integer memoryClock;

    public MemoryClock() {
    }

    public MemoryClock(Integer idMemoryClock) {
        this.idMemoryClock = idMemoryClock;
    }

    public Integer getIdMemoryClock() {
        return idMemoryClock;
    }

    public void setIdMemoryClock(Integer idMemoryClock) {
        this.idMemoryClock = idMemoryClock;
    }

    public Integer getMemoryClock() {
        return memoryClock;
    }

    public void setMemoryClock(Integer memoryClock) {
        this.memoryClock = memoryClock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMemoryClock != null ? idMemoryClock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemoryClock)) {
            return false;
        }
        MemoryClock other = (MemoryClock) object;
        if ((this.idMemoryClock == null && other.idMemoryClock != null) || (this.idMemoryClock != null && !this.idMemoryClock.equals(other.idMemoryClock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ser.MemoryClock[ idMemoryClock=" + idMemoryClock + " ]";
    }
    
}
