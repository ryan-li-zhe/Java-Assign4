/***************************************************************************f******************u************zz*******y**
 * File: MyObjectMapperProvider.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * 
 * @Students: Zhe Li, Kevin, Kevin Nghiem & Yan Qu
 * @Group: A4 30
 *
 * @date 2020 04
 *
 */
package com.algonquincollege.cst8277;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {
    /** ObjectMapper */
    final ObjectMapper defaultObjectMapper;

    /**
     * constructor
     */
    public MyObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    /**
     * getContext
     */
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    /**
     * createDefaultMapper
     * 
     * @return
     */
    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}