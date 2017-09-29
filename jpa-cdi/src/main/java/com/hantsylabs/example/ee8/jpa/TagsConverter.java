/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jpa;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author hantsy
 */
@Converter
public class TagsConverter implements AttributeConverter<List<String>, String> {

    @Inject ConverterUtils utils;
    
    @Override
    public String convertToDatabaseColumn(List attribute) {
        return utils.listToString(attribute);
    }

    @Override
    public List convertToEntityAttribute(String dbData) {
        return utils.stringToList(dbData);
    }

}
