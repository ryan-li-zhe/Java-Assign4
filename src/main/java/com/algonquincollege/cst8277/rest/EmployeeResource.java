package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.MyConstants.ADMIN_ROLE;
import static com.algonquincollege.cst8277.utils.MyConstants.USER_ROLE;
import static com.algonquincollege.cst8277.utils.MyConstants.EMPLOYEE_RESOURCE_NAME;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.soteria.WrappingCallerPrincipal;

import com.algonquincollege.cst8277.ejb.EmployeeBean;
import com.algonquincollege.cst8277.models.EmployeePojo;
import com.algonquincollege.cst8277.models.SecurityUser;

@Path(EMPLOYEE_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    
    @EJB
    protected EmployeeBean eBean;
    
    @Inject
    protected SecurityContext sc;
    
    @GET
    @RolesAllowed(ADMIN_ROLE)
    public Response getAllEmployees() {
        return Response.ok(eBean.findAllEmployees()).build();
    }

    @RolesAllowed({USER_ROLE})
    @GET
    @Path("{userId}")
    public Response getByUserId(@PathParam("userId") int userId) {
        Response response = null;
        WrappingCallerPrincipal wCallerPrincipal = (WrappingCallerPrincipal)sc.getCallerPrincipal();
        SecurityUser sUser = (SecurityUser)wCallerPrincipal.getWrapped();
        EmployeePojo e = sUser.getEmployee();
        if (e.getId() != userId) {
            throw new ForbiddenException("User trying to access resource it does not own (wrong userid)");
        }
        // TODO
        return response;
    }
}
