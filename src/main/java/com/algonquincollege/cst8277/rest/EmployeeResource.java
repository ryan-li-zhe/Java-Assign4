/***
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 */
package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.MyConstants.ADMIN_ROLE;
import static com.algonquincollege.cst8277.utils.MyConstants.USER_ROLE;
import static com.algonquincollege.cst8277.utils.MyConstants.EMPLOYEE_RESOURCE_NAME;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.soteria.WrappingCallerPrincipal;

import com.algonquincollege.cst8277.ejb.EmployeeBean;
import com.algonquincollege.cst8277.models.AddressPojo;
import com.algonquincollege.cst8277.models.EmployeePojo;
import com.algonquincollege.cst8277.models.SecurityUser;

@Path(EMPLOYEE_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    /** EmployeeBean */
    @EJB
    protected EmployeeBean eBean;
    /** SecurityContext */
    @Inject
    protected SecurityContext sc;

    /**
     * getAllEmployees
     * @return
     */
    @GET
    // @RolesAllowed(ADMIN_ROLE)
    public Response getAllEmployees() {
        return Response.ok(eBean.findAllEmployees()).build();
    }

    /**
     * getByUserId
     * @param userId
     * @return
     */
    // @RolesAllowed({USER_ROLE})
    @GET
    @Path("{userId}")
    public Response getByUserId(@PathParam("userId") int userId) {
//        WrappingCallerPrincipal wCallerPrincipal = (WrappingCallerPrincipal)sc.getCallerPrincipal();
//        SecurityUser sUser = (SecurityUser)wCallerPrincipal.getWrapped();
//        EmployeePojo e = sUser.getEmployee();
//        if (e.getId() != userId) {
//            throw new ForbiddenException("User trying to access resource it does not own (wrong userid)");
//        }
        EmployeePojo e = eBean.findByEmployeeId(userId);
        return Response.ok(e).build();
    }
    
    /**
     * createNewEmployee
     * @param emp
     * @return
     */
    @POST
    public Response createNewEmployee(EmployeePojo emp) {
        EmployeePojo newEmp = eBean.createNewEmployee(emp);
        return Response.ok(newEmp).build();
    }
    
    /**
     * deleteByEmployeeId
     * 
     * @param empId
     * @return
     */
    @DELETE
    @Path("{empId}")
    public Response deleteByEmployeeId(@PathParam("empId") int empId) {
        EmployeePojo deletedEmp = eBean.deleteByEmployeeId(empId);
        return Response.ok(deletedEmp).build();
    }
    
    /**
     * updateByEmployeed
     * @param empId
     * @param employee
     * @return
     */
    @PUT
    @Path("{empId}")
    public Response updateByEmployeed(@PathParam("empId") int empId, EmployeePojo employee) {
        EmployeePojo emp = eBean.findByEmployeeId(empId);
        if(emp!=null) {
            emp.setAddress(employee.getAddress());
            emp.setEmail(employee.getEmail());
            emp.setFirstName(employee.getFirstName());
            emp.setEmployeeTasks(employee.getEmployeeTasks());
            emp.setLastName(employee.getLastName());
            emp.setPhones(employee.getPhones());
            emp.setProjects(employee.getProjects());
            emp.setSalary(employee.getSalary());
            emp.setTitle(employee.getTitle());
            eBean.updateEmployee(emp);
        }
        return Response.ok(emp).build();
    }
    
}
