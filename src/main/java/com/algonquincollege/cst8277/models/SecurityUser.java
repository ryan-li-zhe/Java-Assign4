/***************************************************************************f******************u************zz*******y**
 * File: SecurityUser.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * 
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 *
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User class used for (JSR-375) Java EE Security authorization/authentication
 */
@Entity
@Table(name = "SECURITY_USER")
public class SecurityUser implements Serializable, Principal {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    /***  id */
    protected int id;
    /*** username  */
    protected String username;
    /***  pwHash */
    protected String pwHash;
    /*** roles  */
    protected Set<SecurityRole> roles;
    /***  employee */
    protected EmployeePojo employee;

    /**
     * constructor
     */
    public SecurityUser() {
        super();
    }

    /**
     * 
     * @return
     */
    @Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getUsername
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * setUsername
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getPwHash
     * @return
     */
    public String getPwHash() {
        return pwHash;
    }

    /**
     * setPwHash
     * @param pwHash
     */
    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    /**
     * getRoles
     * @return
     */
    @ManyToMany(targetEntity = SecurityRole.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "SECURITY_USER_SECURITY_ROLE",
        joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"))
    public Set<SecurityRole> getRoles() {
        return roles;
    }

    /**
     * setRoles
     * @param roles
     */
    public void setRoles(Set<SecurityRole> roles) {
        this.roles = roles;
    }

    /**
     * getEmployee
     * @return
     */
    @OneToOne
    @JoinColumn(name="EMP_ID")
    public EmployeePojo getEmployee() {
        return employee;
    }

    /**
     * setEmployee
     * 
     * @param employee
     */
    public void setEmployee(EmployeePojo employee) {
        this.employee = employee;
    }

    /**
     * getName
     */
    // Principal
    @Override
    public String getName() {
        return getUsername();
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
        SecurityUser other = (SecurityUser)obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
}