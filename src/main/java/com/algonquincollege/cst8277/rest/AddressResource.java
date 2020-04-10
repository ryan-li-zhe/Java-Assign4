/***
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 */
package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.MyConstants.ADDRESS_RESOURCE_NAME;

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

import com.algonquincollege.cst8277.ejb.AddressBean;
import com.algonquincollege.cst8277.models.AddressPojo;

@Path(ADDRESS_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {
    /** AddressBean */
    @EJB
    protected AddressBean aBean;
    /** SecurityContext */
    @Inject
    protected SecurityContext sc;

    /**
     * getAllAddresses
     * @return
     */
    @GET
    // @RolesAllowed(USER_ROLE)
    public Response getAllAddresses() {
        return Response.ok(aBean.findAllAddresses()).build();
    }

    /**
     * getByAddressId
     * @param addrId
     * @return
     */
    // @RolesAllowed({USER_ROLE})
    @GET
    @Path("{addressId}")
    public Response getByAddressId(@PathParam("addressId") int addrId) {
        AddressPojo addr = aBean.findByAddressId(addrId);
        return Response.ok(addr).build();
    }

    /**
     * createNewAddress
     * 
     * @param address
     * @return
     */
    @POST
    public Response createNewAddress(AddressPojo address) {
        AddressPojo newAddress = aBean.createNewAddress(address);
        return Response.ok(newAddress).build();
    }
    
    /**
     * deleteByAddressId
     * @param addressId
     * @return
     */
    @DELETE
    @Path("{addressId}")
    public Response deleteByAddressId(@PathParam("addressId") int addressId) {
        AddressPojo deletedAddress = aBean.deleteByAddressId(addressId);
        return Response.ok(deletedAddress).build();
    }
    
    /**
     * updateByAddressId
     * @param addressId
     * @param address
     * @return
     */
    @PUT
    @Path("{addressId}")
    public Response updateByAddressId(@PathParam("addressId") int addressId, AddressPojo address) {
        AddressPojo addr = aBean.findByAddressId(addressId);
        if(addr!=null) {
            addr.setCity(address.getCity());
            addr.setCountry(address.getCountry());
            addr.setPostal(address.getPostal());
            addr.setState(address.getState());
            addr.setStreet(address.getStreet());
            aBean.updateAddress(addr);
        }
        return Response.ok(addr).build();
    }
    
}
