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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vit
 */
@Entity
@Table(name = "gpu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gpu.findAll", query = "SELECT g FROM Gpu g"),
    @NamedQuery(name = "Gpu.findByIdgpu", query = "SELECT g FROM Gpu g WHERE g.idgpu = :idgpu"),
    @NamedQuery(name = "Gpu.findByName", query = "SELECT g FROM Gpu g WHERE g.name = :name"),
    @NamedQuery(name = "Gpu.findByMemory", query = "SELECT g FROM Gpu g WHERE g.memory = :memory"),
    @NamedQuery(name = "Gpu.findByGpuclock", query = "SELECT g FROM Gpu g WHERE g.gpuclock = :gpuclock"),
    @NamedQuery(name = "Gpu.findByMemoryclock", query = "SELECT g FROM Gpu g WHERE g.memoryclock = :memoryclock"),
    @NamedQuery(name = "Gpu.findByPrice", query = "SELECT g FROM Gpu g WHERE g.price = :price")})
public class Gpu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idgpu")
    private Integer idgpu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "memory")
    private int memory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpuclock")
    private int gpuclock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "memoryclock")
    private int memoryclock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;

    public Gpu() {
    }

    public Gpu(Integer idgpu) {
        this.idgpu = idgpu;
    }

    public Gpu(Integer idgpu, String name, int memory, int gpuclock, int memoryclock, int price) {
        this.idgpu = idgpu;
        this.name = name;
        this.memory = memory;
        this.gpuclock = gpuclock;
        this.memoryclock = memoryclock;
        this.price = price;
    }

    public Integer getIdgpu() {
        return idgpu;
    }

    public void setIdgpu(Integer idgpu) {
        this.idgpu = idgpu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getGpuclock() {
        return gpuclock;
    }

    public void setGpuclock(int gpuclock) {
        this.gpuclock = gpuclock;
    }

    public int getMemoryclock() {
        return memoryclock;
    }

    public void setMemoryclock(int memoryclock) {
        this.memoryclock = memoryclock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgpu != null ? idgpu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gpu)) {
            return false;
        }
        Gpu other = (Gpu) object;
        if ((this.idgpu == null && other.idgpu != null) || (this.idgpu != null && !this.idgpu.equals(other.idgpu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ser.Gpu[ idgpu=" + idgpu + " ]";
    }
    
}
