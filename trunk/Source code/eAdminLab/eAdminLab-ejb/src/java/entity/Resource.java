/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NOAH
 */
@Entity
@Table(name = "RESOURCE", catalog = "eAdministration", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resource.findAll", query = "SELECT r FROM Resource r"),
    @NamedQuery(name = "Resource.findByResourceID", query = "SELECT r FROM Resource r WHERE r.resourceID = :resourceID"),
    @NamedQuery(name = "Resource.findByResourceName", query = "SELECT r FROM Resource r WHERE r.resourceName = :resourceName"),
    @NamedQuery(name = "Resource.findByQuantity", query = "SELECT r FROM Resource r WHERE r.quantity = :quantity")})
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "resourceID", nullable = false, length = 15)
    private String resourceID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "resourceName", nullable = false, length = 100)
    private String resourceName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @JoinColumn(name = "labID", referencedColumnName = "labID", nullable = false)
    @ManyToOne(optional = false)
    private Lab lab;

    public Resource() {
    }

    public Resource(String resourceID) {
        this.resourceID = resourceID;
    }

    public Resource(String resourceID, String resourceName, int quantity) {
        this.resourceID = resourceID;
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resourceID != null ? resourceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.resourceID == null && other.resourceID != null) || (this.resourceID != null && !this.resourceID.equals(other.resourceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Resource[ resourceID=" + resourceID + " ]";
    }
    
}
