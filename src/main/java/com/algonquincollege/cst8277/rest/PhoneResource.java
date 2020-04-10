/***
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 * 
 */
package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.MyConstants.PHONE_RESOURCE_NAME;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.algonquincollege.cst8277.ejb.PhoneBean;
import com.algonquincollege.cst8277.models.HomePhone;
import com.algonquincollege.cst8277.models.MobilePhone;
import com.algonquincollege.cst8277.models.PhonePojo;
import com.algonquincollege.cst8277.models.WorkPhone;


@Path(PHONE_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PhoneResource {
    /**  PhoneBean */
    @EJB
    protected PhoneBean pBean;
    /** SecurityContext  */
    @Inject
    protected SecurityContext sc;

    /**
     * getAllPhones
     * @return
     */
    @GET
    // @RolesAllowed(USER_ROLE)
    public Response getAllPhones() {
        return Response.ok(pBean.findAllPhones()).build();
    }

    /**
     * getByAddressId
     * @param phoneId
     * @return
     */
    // @RolesAllowed({USER_ROLE})
    @GET
    @Path("{phoneId}")
    public Response getByAddressId(@PathParam("phoneId") int phoneId) {
        PhonePojo phone = pBean.findByPhoneId(phoneId);
        return Response.ok(phone).build();
    }

    /**
     * createNewAddress
     * @param phone
     * @return
     */
    @POST
    public Response createNewAddress(PhonePojo phone) {
        PhonePojo newPhone = pBean.createNewPhone(phone);
        return Response.ok(newPhone).build();
    }
    
    /**
     * deleteByPhoneId
     * @param phoneId
     * @return
     */
    @DELETE
    @Path("{phoneId}")
    public Response deleteByPhoneId(@PathParam("phoneId") int phoneId) {
        PhonePojo deletedPhone = pBean.deleteByPhoneId(phoneId);
        return Response.ok(deletedPhone).build();
    }
    
    /**
     * updateByPhoneId
     * @param addressId
     * @param p
     * @return
     */
    @PUT
    @Path("{phoneId}")
    public Response updateByPhoneId(@PathParam("phoneId") int addressId, PhonePojo p) {
        PhonePojo phone = pBean.findByPhoneId(addressId);
        if(phone!=null) {
            phone.setAreaCode(p.getAreaCode());
            phone.setOwningEmployee(p.getOwningEmployee());
            phone.setPhoneNumber(p.getPhoneNumber());
            phone.setPhoneType(p.getPhoneType());
            if("H".equals(p.getPhoneType())) {
                ((HomePhone)phone).setMapCoords(((HomePhone)p).getMapCoords());
            }else if("M".equals(p.getPhoneType())) {
                ((MobilePhone)phone).setProvider(((MobilePhone)p).getProvider());
            }else if("W".equals(p.getPhoneType())) {
                ((WorkPhone)phone).setDepartment(((WorkPhone)p).getDepartment());
            }
            pBean.updatePhone(phone);
        }
        return Response.ok(phone).build();
    }
    
}
