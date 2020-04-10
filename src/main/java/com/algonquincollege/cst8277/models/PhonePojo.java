/***************************************************************************f******************u************zz*******y**
 * File: PhonePojo.java
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

import java.io.Serializable;

import static com.algonquincollege.cst8277.models.PhonePojo.FIND_PHONE_BY_PHONE_ID;
import static com.algonquincollege.cst8277.models.PhonePojo.FIND_PHONE_BY_EMP_ID;
import static com.algonquincollege.cst8277.models.PhonePojo.FIND_ALL_PHONES;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Phone class
 * 
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "phoneType")
@JsonSubTypes({
    @Type(value = HomePhone.class, name = "H"),
    @Type(value = MobilePhone.class, name = "M"),
    @Type(value = WorkPhone.class, name = "W")})
@MappedSuperclass
@Entity(name = "Phone")
@Table(name = "PHONE")
@AttributeOverride(name = "id", column = @Column(name = "PHONE_ID"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PHONE_TYPE", length = 1)
@NamedQueries({
    @NamedQuery(name = FIND_PHONE_BY_EMP_ID, query = "select p from Employee e join e.phones p where e.id = :param1"),
    @NamedQuery(name = FIND_PHONE_BY_PHONE_ID, query = "select p from Phone p where p.id = :param1"),
    @NamedQuery(name = FIND_ALL_PHONES, query = "select p from Phone p")
})
public abstract class PhonePojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    /*** FIND_PHONE_BY_EMP_ID */
    public static final String FIND_PHONE_BY_EMP_ID = "findPhoneByEmpId";
    /*** FIND_PHONE_BY_PHONE_ID */
    public static final String FIND_PHONE_BY_PHONE_ID = "findPhoneByPhonerId";
    /*** FIND_ALL_PHONES */
    public static final String FIND_ALL_PHONES = "findAllPhones";
    /*** areaCode */
    protected String areaCode;
    /*** phoneNumber */
    protected String phoneNumber;
    /*** owningEmployee */
    protected EmployeePojo owningEmployee;
    /*** phoneType  */
    protected String phoneType;

    /**
     *  JPA requires each @Entity class have a default constructor
     */
    public PhonePojo() {
        super();
    }

    /**
     * @return the areaCode
     */
    @Column(name = "AREACODE")
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode
     *            the areaCode to set
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return the phoneNumber
     */
    @Column(name = "PHONENUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     *            the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the phoneType
     */
    @Column(name = "PHONE_TYPE")
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * @param phoneType
     *            the phoneType to set
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    /**
     * @return the owningEmployee
     */
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNING_EMP_ID") // Db details: FK column
    public EmployeePojo getOwningEmployee() {
        return owningEmployee;
    }

    /**
     * @param owningEmployee
     *            the owningEmployee to set
     */
    public void setOwningEmployee(EmployeePojo owningEmployee) {
        this.owningEmployee = owningEmployee;
    }
}