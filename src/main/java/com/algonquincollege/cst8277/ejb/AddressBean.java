/***************************************************************************f******************u************zz*******y**
 * File: SomeBean.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * 
 * @Students: Zhe Li, Kevin, Kevin nghiem & Yan Qu
 * @Group: A4 30
 * 
 * (Modified) @date 2020 03
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.models.AddressPojo.FIND_ADDRESS_BY_ADDR_ID;
import static com.algonquincollege.cst8277.models.AddressPojo.FIND_ALL_ADDRESSES;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algonquincollege.cst8277.models.AddressPojo;

@Stateless
public class AddressBean {
    /** EntityManager */
    @PersistenceContext(unitName = "assignment4-PU")
    protected EntityManager em;

    /**
     * find All Addresses
     * @return
     */
    public List<AddressPojo> findAllAddresses() {
        return em.createNamedQuery(FIND_ALL_ADDRESSES, AddressPojo.class).getResultList();
    }

    /**
     * findByAddressId
     * @param addrId
     * @return
     */
    public AddressPojo findByAddressId(int addrId) {
        return em.createNamedQuery(FIND_ADDRESS_BY_ADDR_ID, AddressPojo.class).setParameter("param1", addrId)
            .getSingleResult();
    }

    /**
     * createNewAddress
     * @param address
     * @return
     */
    public AddressPojo createNewAddress(AddressPojo address) {
        em.persist(address);
        return address;
    }

    /**
     * deleteByAddressId
     * @param addrId
     * @return
     */
    public AddressPojo deleteByAddressId(int addrId) {
        AddressPojo myAddr = em.createNamedQuery(FIND_ADDRESS_BY_ADDR_ID, AddressPojo.class)
            .setParameter("param1", addrId).getSingleResult();
        em.remove(myAddr);
        return myAddr;
    }

    /**
     * updateAddress
     * @param address
     * @return
     */
    public AddressPojo updateAddress(AddressPojo address) {
        em.merge(address);
        em.flush();
        AddressPojo upatedAddress = em.createNamedQuery(FIND_ADDRESS_BY_ADDR_ID, AddressPojo.class)
            .setParameter("param1", address.getId()).getSingleResult();
        return upatedAddress;
    }
}