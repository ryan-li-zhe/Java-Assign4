/***************************************************************************f******************u************zz*******y**
 * File: PojoListener.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * 
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 *
 */
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class PojoListener {
    
    /**
     * onPersist, pre persist
     * @param pojo
     */
    @PrePersist
    public void onPersist(PojoBase pojo) {
        LocalDateTime now = LocalDateTime.now();
        pojo.setCretaedDate(now);
        pojo.setUpdatedDate(now);
    }

    /**
     * setUpdatedDate, pre update
     * @param emp
     */
    @PreUpdate
    public void onUpdate(PojoBase pojo) {
        pojo.setUpdatedDate(LocalDateTime.now());
    }
}