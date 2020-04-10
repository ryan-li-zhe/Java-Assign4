/**************************************************************************************************
 * File: CustomIdentityStoreJPAHelper.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 *
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 *
 */
package com.algonquincollege.cst8277.security;

import static com.algonquincollege.cst8277.utils.MyConstants.PU_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollege.cst8277.models.SecurityRole;
import com.algonquincollege.cst8277.models.SecurityUser;

@Singleton
public class CustomIdentityStoreJPAHelper {
    /** EntityManager */
    @PersistenceContext(name = PU_NAME)
    protected EntityManager em;
    /** Pbkdf2PasswordHash */
    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;
    
    /***
     * findUserByName
     * @param username
     * @return
     */
    public SecurityUser findUserByName(String username) {
        SecurityUser securityUser = null;
        try {
            TypedQuery<SecurityUser> query = em
                .createQuery("select su from SecurityUser su where su.username = :param1", SecurityUser.class)
                .setParameter("param1", username);
            securityUser = query.getSingleResult();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return securityUser;
    }

    /**
     * findRoleNamesForUser
     * @param username
     * @return
     */
    public Set<String> findRoleNamesForUser(String username) {
        Set<String> rolenames = new HashSet<>();
        try {
            List<SecurityRole> roleList = em.createQuery(
                "SELECT sr FROM SecurityUser su join su.roles sr WHERE su.username = :param1", SecurityRole.class)
                .setParameter("param1", username)
                .getResultList();
            for(SecurityRole role: roleList) {
                rolenames.add(role.getRoleName());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return rolenames;
    }

    /**
     * saveSecurityUser
     * @param user
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveSecurityUser(SecurityUser user) {
        em.persist(user);
    }

    /**
     * saveSecurityRole
     * @param role
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveSecurityRole(SecurityRole role) {
        em.persist(role);
    }
}