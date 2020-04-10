/***************************************************************************f******************u************zz*******y**
 * File: EmployeeSystemTestSuite.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * 
 * @Students: Zhe Li, Kevin, Kevin nghiem & Yan Qu
 * @Group: A4 30
 * 
 * @date 2020 03
 *
 */
package com.algonquincollege.cst8277;

import static com.algonquincollege.cst8277.utils.MyConstants.ADDRESS_RESOURCE_NAME;
import static com.algonquincollege.cst8277.utils.MyConstants.APPLICATION_API_VERSION;
import static com.algonquincollege.cst8277.utils.MyConstants.EMPLOYEE_RESOURCE_NAME;
import static com.algonquincollege.cst8277.utils.MyConstants.PHONE_RESOURCE_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.invoke.MethodHandles;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * jUnit Test
 * @author Zhe Li,
 *
 */
public class EmployeeSystemTestSuite {
    /** _thisClaz */
    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    /** logger  */
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);
    /** APPLICATION_CONTEXT_ROOT */
    static final String APPLICATION_CONTEXT_ROOT = "rest-employeeSystem";
    /** HTTP_SCHEMA */
    static final String HTTP_SCHEMA = "http";
    /** HOST */
    static final String HOST = "localhost";
    /** PORT */
    static final int PORT = 9090;
    /** DEFAULT_ADMIN_USER */
    static final String DEFAULT_ADMIN_USER = "admin";
    /** DEFAULT_ADMIN_USER_PW */
    static final String DEFAULT_ADMIN_USER_PW = "admin";
    /** DEFAULT_USER */
    static final String DEFAULT_USER = "user1";
    /** DEFAULT_USER_PW */
    static final String DEFAULT_USER_PW = "password";

    /**  uri */
    static URI uri;
    /**  adminAuth */
    static HttpAuthenticationFeature adminAuth;
    /**  userAuth */
    static HttpAuthenticationFeature userAuth;
    /**  webTarget */
    protected WebTarget webTarget;

    /**
     * oneTimeSetUp
     * @throws Exception
     */
    @BeforeClass
    public static void oneTimeSetUp() throws Exception {
        logger.debug("oneTimeSetUp");
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        uri = UriBuilder
            .fromUri(APPLICATION_CONTEXT_ROOT + APPLICATION_API_VERSION)
            .scheme(HTTP_SCHEMA)
            .host(HOST)
            .port(PORT)
            .build();
        adminAuth = HttpAuthenticationFeature.basic(DEFAULT_ADMIN_USER, DEFAULT_ADMIN_USER_PW);
        userAuth = HttpAuthenticationFeature.basic(DEFAULT_USER, DEFAULT_USER_PW);
    }

    /**
     * setUp
     */
    @Before
    public void setUp() {
        Client client = ClientBuilder.newClient(
            new ClientConfig().register(MyObjectMapperProvider.class).register(new LoggingFeature()));
        webTarget = client.target(uri);
    }

    /***
     * get all employees
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @Test
    public void test01_all_employees() throws JsonMappingException, JsonProcessingException {
        Response response = webTarget
            .path(EMPLOYEE_RESOURCE_NAME)
            .request()
            .get();
        assertThat(response.getStatus(), is(200));
    }
    
    /***
     * get all addresses
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @Test
    public void test02_all_addresses() throws JsonMappingException, JsonProcessingException {
        Response response = webTarget
            .path(ADDRESS_RESOURCE_NAME)
            .request()
            .get();
        assertThat(response.getStatus(), is(200));
    }
    
    /***
     * get all phones
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @Test
    public void test03_all_phones() throws JsonMappingException, JsonProcessingException {
        Response response = webTarget
            .path(PHONE_RESOURCE_NAME)
            .request()
            .get();
        assertThat(response.getStatus(), is(200));
    }
    
   
}