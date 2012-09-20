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
@Table(name = "STATUS", catalog = "eAdministration", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s"),
    @NamedQuery(name = "Status.findByStatusID", query = "SELECT s FROM Status s WHERE s.statusID = :statusID"),
    @NamedQuery(name = "Status.findByStatusName", query = "SELECT s FROM Status s WHERE s.statusName = :statusName")})
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "statusID", nullable = false)
    private Short statusID;
    @Basic(optional = false)
    @Column(name = "statusName", nullable = false, length = 15)
    private String statusName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<Request> requestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<Complaint> complaintList;

    public Status() {
    }

    public Status(Short statusID) {
        this.statusID = statusID;
    }

    public Status(Short statusID, String statusName) {
        this.statusID = statusID;
        this.statusName = statusName;
    }

    public Short getStatusID() {
        return statusID;
    }

    public void setStatusID(Short statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusID != null ? statusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.statusID == null && other.statusID != null) || (this.statusID != null && !this.statusID.equals(other.statusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Status[statusID=" + statusID + "]";
    }

}
