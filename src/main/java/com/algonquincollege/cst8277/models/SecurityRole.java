/***************************************************************************f******************u************zz*******y**
 * File: SecurityRole.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * 
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 *
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Role class used for (JSR-375) Java EE Security authorization/authentication
 */
@Entity
@Table(name = "SECURITY_ROLE")
public class SecurityRole implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** id */
    protected int id;
    /** roleName */
    protected String roleName;
    /** users */
    protected Set<SecurityUser> users;

    /**
     * constructor
     */
    public SecurityRole() {
        super();
    }

    /**
     * getId
     * @return
     */
    @Column(name = "ROLE_ID")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * setId
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getRoleName
     * @return
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * setRoleName
     * 
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * getUsers
     * @return
     */
    @ManyToMany(mappedBy = "roles")
    public Set<SecurityUser> getUsers() {
        return users;
    }

    /**
     * setUsers
     * @param users
     */
    public void setUsers(Set<SecurityUser> users) {
        this.users = users;
    }

    /**
     * addUserToRole
     * @param user
     */
    public void addUserToRole(SecurityUser user) {
        getUsers().add(user);
    }

    /**
     * hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SecurityRole other = (SecurityRole)obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
}