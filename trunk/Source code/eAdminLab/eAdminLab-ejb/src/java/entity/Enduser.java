/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pakacoco
 */
@Entity
@Table(name = "ENDUSER", catalog = "eAdministration", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Enduser.findAll", query = "SELECT e FROM Enduser e"),
    @NamedQuery(name = "Enduser.findByUserID", query = "SELECT e FROM Enduser e WHERE e.userID = :userID"),
    @NamedQuery(name = "Enduser.findByUserName", query = "SELECT e FROM Enduser e WHERE e.userName = :userName"),
    @NamedQuery(name = "Enduser.findByPassword", query = "SELECT e FROM Enduser e WHERE e.password = :password"),
    @NamedQuery(name = "Enduser.findByAddress", query = "SELECT e FROM Enduser e WHERE e.address = :address"),
    @NamedQuery(name = "Enduser.findByEmail", query = "SELECT e FROM Enduser e WHERE e.email = :email"),
    @NamedQuery(name = "Enduser.findByBirthday", query = "SELECT e FROM Enduser e WHERE e.birthday = :birthday")})
public class Enduser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userID", nullable = false, length = 15)
    private String userID;
    @Basic(optional = false)
    @Column(name = "userName", nullable = false, length = 15)
    private String userName;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Basic(optional = false)
    @Column(name = "address", nullable = false, length = 100)
    private String address;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @OneToMany(mappedBy = "enduser")
    private List<Complaint> complaintList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enduser1")
    private List<Complaint> complaintList1;
    @JoinColumn(name = "roleID", referencedColumnName = "roleID", nullable = false)
    @ManyToOne(optional = false)
    private Role role;

    public Enduser() {
    }

    public Enduser(String userID) {
        this.userID = userID;
    }

    public Enduser(String userID, String userName, String password, String address, String email, Date birthday) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.birthday = birthday;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }

    public List<Complaint> getComplaintList1() {
        return complaintList1;
    }

    public void setComplaintList1(List<Complaint> complaintList1) {
        this.complaintList1 = complaintList1;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enduser)) {
            return false;
        }
        Enduser other = (Enduser) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Enduser[userID=" + userID + "]";
    }

}
