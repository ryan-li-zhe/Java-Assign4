/***************************************************************************f******************u************zz*******y**
 * File: SomeBean.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * (Modified) @date 2020 03
 * 
 * @Students: Zhe Li, Kevin, Kevin nghiem & Yan Qu
 * @Group: A4 30
 * 
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.models.EmployeePojo.FIND_EMPLOYEE_BY_EMP_ID;
import static com.algonquincollege.cst8277.models.EmployeePojo.ALL_EMPLOYEES_QUERY_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algonquincollege.cst8277.models.EmployeePojo;


@Stateless
public class EmployeeBean {
    /** EntityManager */
    @PersistenceContext(unitName = "assignment4-PU")
    protected EntityManager em;

    /**
     * findAllEmployees
     * @return
     */
    public List<EmployeePojo> findAllEmployees() {
        return em.createNamedQuery(ALL_EMPLOYEES_QUERY_NAME, EmployeePojo.class).getResultList();
    }

    /**
     * findByEmployeeId
     * @param empId
     * @return
     */
    public EmployeePojo findByEmployeeId(int empId) {
        return em.createNamedQuery(FIND_EMPLOYEE_BY_EMP_ID, EmployeePojo.class).setParameter("param1", empId)
            .getSingleResult();
    }

    /**
     * createNewEmployee
     * @param emp
     * @return
     */
    public EmployeePojo createNewEmployee(EmployeePojo emp) {
        em.persist(emp);
        return emp;
    }

    /**
     * deleteByEmployeeId
     * @param empId
     * @return
     */
    public EmployeePojo deleteByEmployeeId(int empId) {
        EmployeePojo myEmp = em.createNamedQuery(FIND_EMPLOYEE_BY_EMP_ID, EmployeePojo.class)
            .setParameter("param1", empId).getSingleResult();
        em.remove(myEmp);
        return myEmp;
    }

    /**
     * updateEmployee
     * @param address
     * @return
     */
    public EmployeePojo updateEmployee(EmployeePojo address) {
        em.merge(address);
        em.flush();
        EmployeePojo upatedEmp = em.createNamedQuery(FIND_EMPLOYEE_BY_EMP_ID, EmployeePojo.class)
            .setParameter("param1", address.getId()).getSingleResult();
        return upatedEmp;
    }
}