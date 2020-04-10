/***************************************************************************f******************u************zz*******y**
 * File: EmployeePojo.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * (Modified) @date 2020 02
 * 
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 *
 * Copyright (c) 1998, 2009 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Original @authors dclarke, mbraeuer
 */
package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.AddressPojo.FIND_ADDRESS_BY_ADDR_ID;
import static com.algonquincollege.cst8277.models.AddressPojo.FIND_ADDRESS_BY_EMP_ID;
import static com.algonquincollege.cst8277.models.EmployeePojo.ALL_EMPLOYEES_QUERY_NAME;
import static com.algonquincollege.cst8277.models.EmployeePojo.FIND_EMPLOYEE_BY_EMP_ID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The Employee class demonstrates several JPA features:
 * <ul>
 * <li>OneToOne relationship
 * <li>OneToMany relationship
 * <li>ManyToMany relationship
 * </ul>
 */
@Entity(name = "Employee")
@Table(name = "EMPLOYEE")
@AttributeOverride(name = "id", column = @Column(name = "EMP_ID"))
@NamedQueries({
    @NamedQuery(name = FIND_EMPLOYEE_BY_EMP_ID, query = "select e from Employee e where e.id = :param1"),
    @NamedQuery(name = ALL_EMPLOYEES_QUERY_NAME, query = "select e from Employee e")
})
public class EmployeePojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** FIND_EMPLOYEE_BY_EMP_ID  */
    public static final String FIND_EMPLOYEE_BY_EMP_ID = "findEmployeeByEmpId";
    /** ALL_EMPLOYEES_QUERY_NAME  */
    public static final String ALL_EMPLOYEES_QUERY_NAME = "allEmployees";
    /**  firstName */
    protected String firstName;
    /** lastName  */
    protected String lastName;
    /** email  */
    protected String email;
    /**  title */
    protected String title;
    /**  salary */
    protected Double salary;
    /**  address */
    protected AddressPojo address;
    /** phones  */
    protected List<PhonePojo> phones = new ArrayList<>();
    /** projects  */
    protected List<ProjectPojo> projects;
    /**  employeeTasks */
    protected List<EmployeeTask> employeeTasks = new ArrayList<>();

    /**
     * JPA requires each @Entity class have a default constructor
     * */
    public EmployeePojo() {
        super();
    }

    /**
     * @return the value for firstName
     */
    @Column(name = "FNAME")
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName new value for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the value for lastName
     */
    @Column(name = "LNAME")
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName new value for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the value for email
     */
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    /**
     * @param email new value for email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the value for title
     */
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    /**
     * @param title new value for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the value for salary
     */
    @Column(name = "SALARY")
    public Double getSalary() {
        return salary;
    }

    /**
     * @param salary new value for salary
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * @return the address
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDR_ID")
    public AddressPojo getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(AddressPojo address) {
        this.address = address;
    }

    /**
     * @return the phones
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "owningEmployee", cascade = CascadeType.ALL)
    public List<PhonePojo> getPhones() {
        return phones;
    }

    /**
     * @param phones the phones to set
     */
    public void setPhones(List<PhonePojo> phones) {
        this.phones = phones;
    }

    /**
     * @return the projects
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID"), inverseJoinColumns = @JoinColumn(name = "PROJ_ID", referencedColumnName = "PROJ_ID"))
    public List<ProjectPojo> getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(List<ProjectPojo> projects) {
        this.projects = projects;
    }

    /**
     * @return the employeeTasks
     */
    @ElementCollection
    @CollectionTable(name = "EMPLOYEE_TASKS", joinColumns = @JoinColumn(name = "OWNER_EMP_ID"))
    public List<EmployeeTask> getEmployeeTasks() {
        return employeeTasks;
    }

    /**
     * @param employeeTasks the employeeTasks to set
     */
    public void setEmployeeTasks(List<EmployeeTask> employeeTasks) {
        this.employeeTasks = employeeTasks;
    }

    /**
     * @param PhonePojo
     */
    public void addPhone(PhonePojo p) {
        getPhones().add(p);
        p.setOwningEmployee(this);
    }

    /**
     * @param employeeTask
     */
    public void addEmployeeTask(EmployeeTask employeeTask) {
        this.employeeTasks.add(employeeTask);
    }
}