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
@Table(name = "memory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memory.findAll", query = "SELECT m FROM Memory m"),
    @NamedQuery(name = "Memory.findByIdMemory", query = "SELECT m FROM Memory m WHERE m.idMemory = :idMemory"),
    @NamedQuery(name = "Memory.findByMemory", query = "SELECT m FROM Memory m WHERE m.memory = :memory")})
public class Memory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_memory")
    private Integer idMemory;
    @Column(name = "memory")
    private Integer memory;

    public Memory() {
    }

    public Memory(Integer idMemory) {
        this.idMemory = idMemory;
    }

    public Integer getIdMemory() {
        return idMemory;
    }

    public void setIdMemory(Integer idMemory) {
        this.idMemory = idMemory;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMemory != null ? idMemory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memory)) {
            return false;
        }
        Memory other = (Memory) object;
        if ((this.idMemory == null && other.idMemory != null) || (this.idMemory != null && !this.idMemory.equals(other.idMemory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ser.Memory[ idMemory=" + idMemory + " ]";
    }
    
}
