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
@Table(name = "price")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"),
    @NamedQuery(name = "Price.findByIdPrice", query = "SELECT p FROM Price p WHERE p.idPrice = :idPrice"),
    @NamedQuery(name = "Price.findByPrice", query = "SELECT p FROM Price p WHERE p.price = :price")})
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_price")
    private Integer idPrice;
    @Column(name = "price")
    private Integer price;

    public Price() {
    }

    public Price(Integer idPrice) {
        this.idPrice = idPrice;
    }

    public Integer getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(Integer idPrice) {
        this.idPrice = idPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrice != null ? idPrice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Price)) {
            return false;
        }
        Price other = (Price) object;
        if ((this.idPrice == null && other.idPrice != null) || (this.idPrice != null && !this.idPrice.equals(other.idPrice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ser.Price[ idPrice=" + idPrice + " ]";
    }
    
}
