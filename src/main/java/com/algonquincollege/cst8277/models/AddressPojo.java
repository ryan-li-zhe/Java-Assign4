/***************************************************************************f******************u************zz*******y**
 * File: AddressPojo.java
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
 *
 */
package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.AddressPojo.FIND_ADDRESS_BY_EMP_ID;
import static com.algonquincollege.cst8277.models.AddressPojo.FIND_ADDRESS_BY_ADDR_ID;
import static com.algonquincollege.cst8277.models.AddressPojo.FIND_ALL_ADDRESSES;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Simple Address class
 */

//JPA Annotations here
@Entity(name = "Address")
@Table(name = "ADDRESS")
@AttributeOverride(name = "id", column = @Column(name = "ADDR_ID"))
@NamedQueries({
    @NamedQuery(name = FIND_ADDRESS_BY_EMP_ID, query = "select a from Employee e join e.address a where e.id = :param1"),
    @NamedQuery(name = FIND_ADDRESS_BY_ADDR_ID, query = "select a from Address a where a.id = :param1"),
    @NamedQuery(name = FIND_ALL_ADDRESSES, query = "select a from Address a")
})
public class AddressPojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** FIND_ADDRESS_BY_EMP_ID  */
    public static final String FIND_ADDRESS_BY_EMP_ID = "findAddressByEmpId";
    /**  FIND_ADDRESS_BY_ADDR_ID */
    public static final String FIND_ADDRESS_BY_ADDR_ID = "findAddressByAddrId";
    /** FIND_ALL_ADDRESSES  */
    public static final String FIND_ALL_ADDRESSES = "findAllAddress";
    /** city  */
    protected String city;
    /** country  */
    protected String country;
    /** postal  */
    protected String postal;
    /**  state */
    protected String state;
    /** street  */
    protected String street;

    /**
     * JPA requires each @Entity class have a default constructor
     */
    public AddressPojo() {
        super();
    }

    /**
     * @return the value for city
     */
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the postal
     */
    @Column(name = "POSTAL")
    public String getPostal() {
        return postal;
    }

    /**
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * @return the state
     */
    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the street
     */
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

}