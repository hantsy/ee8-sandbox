/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.mvc.domain;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author hantsy
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, java.sql.Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        return attribute != null ? Date.valueOf(attribute) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        return dbData != null ? dbData.toLocalDate() : null;
    }

}
