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
@Table(name = "new_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NewTable.findAll", query = "SELECT n FROM NewTable n"),
    @NamedQuery(name = "NewTable.findByIdnewTable", query = "SELECT n FROM NewTable n WHERE n.idnewTable = :idnewTable"),
    @NamedQuery(name = "NewTable.findByNewTablecol", query = "SELECT n FROM NewTable n WHERE n.newTablecol = :newTablecol")})
public class NewTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idnew_table")
    private Integer idnewTable;
    @Size(max = 45)
    @Column(name = "new_tablecol")
    private String newTablecol;

    public NewTable() {
    }

    public NewTable(Integer idnewTable) {
        this.idnewTable = idnewTable;
    }

    public Integer getIdnewTable() {
        return idnewTable;
    }

    public void setIdnewTable(Integer idnewTable) {
        this.idnewTable = idnewTable;
    }

    public String getNewTablecol() {
        return newTablecol;
    }

    public void setNewTablecol(String newTablecol) {
        this.newTablecol = newTablecol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnewTable != null ? idnewTable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewTable)) {
            return false;
        }
        NewTable other = (NewTable) object;
        if ((this.idnewTable == null && other.idnewTable != null) || (this.idnewTable != null && !this.idnewTable.equals(other.idnewTable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ser.NewTable[ idnewTable=" + idnewTable + " ]";
    }
    
}
