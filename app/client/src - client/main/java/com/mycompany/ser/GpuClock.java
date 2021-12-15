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
@Table(name = "gpu_clock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GpuClock.findAll", query = "SELECT g FROM GpuClock g"),
    @NamedQuery(name = "GpuClock.findByIdGpuClock", query = "SELECT g FROM GpuClock g WHERE g.idGpuClock = :idGpuClock"),
    @NamedQuery(name = "GpuClock.findByGpuClock", query = "SELECT g FROM GpuClock g WHERE g.gpuClock = :gpuClock")})
public class GpuClock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_gpu_clock")
    private Integer idGpuClock;
    @Column(name = "gpu_clock")
    private Integer gpuClock;

    public GpuClock() {
    }

    public GpuClock(Integer idGpuClock) {
        this.idGpuClock = idGpuClock;
    }

    public Integer getIdGpuClock() {
        return idGpuClock;
    }

    public void setIdGpuClock(Integer idGpuClock) {
        this.idGpuClock = idGpuClock;
    }

    public Integer getGpuClock() {
        return gpuClock;
    }

    public void setGpuClock(Integer gpuClock) {
        this.gpuClock = gpuClock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGpuClock != null ? idGpuClock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GpuClock)) {
            return false;
        }
        GpuClock other = (GpuClock) object;
        if ((this.idGpuClock == null && other.idGpuClock != null) || (this.idGpuClock != null && !this.idGpuClock.equals(other.idGpuClock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ser.GpuClock[ idGpuClock=" + idGpuClock + " ]";
    }
    
}
