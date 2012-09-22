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

/**
 *
 * @author Pakacoco
 */
@Entity
@Table(name = "COMPLAINT", catalog = "eAdministration", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Complaint.findAll", query = "SELECT c FROM Complaint c"),
    @NamedQuery(name = "Complaint.findByComplaintID", query = "SELECT c FROM Complaint c WHERE c.complaintID = :complaintID"),
    @NamedQuery(name = "Complaint.findByTitle", query = "SELECT c FROM Complaint c WHERE c.title = :title"),
    @NamedQuery(name = "Complaint.findByDescription", query = "SELECT c FROM Complaint c WHERE c.description = :description")})
public class Complaint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "complaintID", nullable = false, length = 15)
    private String complaintID;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 1073741823)
    private String description;
    @JoinColumn(name = "statusID", referencedColumnName = "statusID", nullable = false)
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private Enduser enduser;

    public Complaint() {
    }

    public Complaint(String complaintID) {
        this.complaintID = complaintID;
    }

    public Complaint(String complaintID, String title, String description) {
        this.complaintID = complaintID;
        this.title = title;
        this.description = description;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Enduser getEnduser() {
        return enduser;
    }

    public void setEnduser(Enduser enduser) {
        this.enduser = enduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complaintID != null ? complaintID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complaint)) {
            return false;
        }
        Complaint other = (Complaint) object;
        if ((this.complaintID == null && other.complaintID != null) || (this.complaintID != null && !this.complaintID.equals(other.complaintID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Complaint[complaintID=" + complaintID + "]";
    }

}
