/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jpa;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author hantsy
 */
@Converter(autoApply = false)
public class TagsConverter implements AttributeConverter<List<String>, String> {

//    private static final Logger LOG = Logger.getLogger(TagsConverter.class.getName());
//    
//     @Override
//    public String convertToDatabaseColumn(List<String> attribute) {
//        if (attribute == null || attribute.isEmpty()) {
//            return "";
//        }
//        return String.join( ",", attribute);
//    }
//
//    @Override
//    public List<String> convertToEntityAttribute(String dbData) {
//        if (dbData == null || dbData.trim().length() == 0) {
//            return new ArrayList<>();
//        }
//
//        String[] data = dbData.split(",");
//        return Arrays.asList(data);
//    }

    @Inject
    Logger LOG;
    
    @Inject
    ConverterUtils utils;
    
    @PostConstruct
    public void postConstruct(){
        LOG.log(Level.INFO, "calling @PostConstruct");
    }
    
    @PreDestroy
    public void preDestroy(){
        LOG.log(Level.INFO, "calling @PreDestroy");
    }

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        LOG.log(Level.FINEST, "utils injected: {0}", utils != null);
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return utils.listToString(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().length() == 0) {
            return Collections.<String>emptyList();
        }
        return utils.stringToList(dbData);
    }

}
