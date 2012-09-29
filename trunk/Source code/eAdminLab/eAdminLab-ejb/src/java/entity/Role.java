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
@Table(name = "ROLE", catalog = "eAdministration", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByRoleID", query = "SELECT r FROM Role r WHERE r.roleID = :roleID"),
    @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName")})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "roleID", nullable = false)
    private Short roleID;
    @Basic(optional = false)
    @Column(name = "roleName", nullable = false, length = 10)
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<Enduser> enduserList;

    public Role() {
    }

    public Role(Short roleID) {
        this.roleID = roleID;
    }

    public Role(Short roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public Short getRoleID() {
        return roleID;
    }

    public void setRoleID(Short roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Enduser> getEnduserList() {
        return enduserList;
    }

    public void setEnduserList(List<Enduser> enduserList) {
        this.enduserList = enduserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleID != null ? roleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Role[roleID=" + roleID + "]";
    }

}
