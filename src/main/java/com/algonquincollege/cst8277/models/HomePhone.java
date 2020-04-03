/***************************************************************************f******************u************zz*******y**
 * File: HomePhone.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * @date 2020 02
 *
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "H")
public class HomePhone extends PhonePojo implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String mapCoords;

    /**
     * default constructor
     */
    public HomePhone() {
        super();
    }

    /**
     * @return the mapCoords
     */
    @Column(name = "MAP_COORDS")
    public String getMapCoords() {
        return mapCoords;
    }

    /**
     * @param mapCoords the mapCoords to set
     */
    public void setMapCoords(String mapCoords) {
        this.mapCoords = mapCoords;
    }

}