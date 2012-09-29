/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Pakacoco
 */
@Entity
@Table(name = "LAB", catalog = "eAdministration", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Lab.findAll", query = "SELECT l FROM Lab l"),
    @NamedQuery(name = "Lab.findByLabID", query = "SELECT l FROM Lab l WHERE l.labID = :labID"),
    @NamedQuery(name = "Lab.findByLabName", query = "SELECT l FROM Lab l WHERE l.labName = :labName")})
public class Lab implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "labID", nullable = false, length = 15)
    private String labID;
    @Basic(optional = false)
    @Column(name = "labName", nullable = false, length = 15)
    private String labName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lab")
    private List<Timetable> timetableList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lab")
    private List<Resource> resourceList;

    public Lab() {
    }

    public Lab(String labID) {
        this.labID = labID;
    }

    public Lab(String labID, String labName) {
        this.labID = labID;
        this.labName = labName;
    }

    public String getLabID() {
        return labID;
    }

    public void setLabID(String labID) {
        this.labID = labID;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public List<Timetable> getTimetableList() {
        return timetableList;
    }

    public void setTimetableList(List<Timetable> timetableList) {
        this.timetableList = timetableList;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labID != null ? labID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lab)) {
            return false;
        }
        Lab other = (Lab) object;
        if ((this.labID == null && other.labID != null) || (this.labID != null && !this.labID.equals(other.labID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Lab[labID=" + labID + "]";
    }

}
