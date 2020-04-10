/***************************************************************************f******************u************zz*******y**
 * File: SomeBean.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * (Modified) @date 2020 03
 * 
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 * 
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.models.PhonePojo.FIND_PHONE_BY_PHONE_ID;
import static com.algonquincollege.cst8277.models.PhonePojo.FIND_PHONE_BY_EMP_ID;
import static com.algonquincollege.cst8277.models.PhonePojo.FIND_ALL_PHONES;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algonquincollege.cst8277.models.PhonePojo;

@Stateless
public class PhoneBean {
    /** EntityManager */
    @PersistenceContext(unitName = "assignment4-PU")
    protected EntityManager em;

    /**
     * findAllPhones
     * @return
     */
    public List<PhonePojo> findAllPhones() {
        return em.createNamedQuery(FIND_ALL_PHONES, PhonePojo.class).getResultList();
    }

    /**
     * findByPhoneId
     * @param phoneId
     * @return
     */
    public PhonePojo findByPhoneId(int phoneId) {
        return em.createNamedQuery(FIND_PHONE_BY_PHONE_ID, PhonePojo.class).setParameter("param1", phoneId)
            .getSingleResult();
    }

    /**
     * createNewPhone
     * @param phone
     * @return
     */
    public PhonePojo createNewPhone(PhonePojo phone) {
        em.persist(phone);
        return phone;
    }

    /**
     * deleteByPhoneId
     * @param phoneId
     * @return
     */
    public PhonePojo deleteByPhoneId(int phoneId) {
        PhonePojo myPhone = em.createNamedQuery(FIND_PHONE_BY_PHONE_ID, PhonePojo.class)
            .setParameter("param1", phoneId).getSingleResult();
        em.remove(myPhone);
        return myPhone;
    }

    /**
     * updatePhone
     * @param phone
     * @return
     */
    public PhonePojo updatePhone(PhonePojo phone) {
        em.merge(phone);
        em.flush();
        PhonePojo upatedPhone = em.createNamedQuery(FIND_PHONE_BY_PHONE_ID, PhonePojo.class)
            .setParameter("param1", phone.getId()).getSingleResult();
        return upatedPhone;
    }
}