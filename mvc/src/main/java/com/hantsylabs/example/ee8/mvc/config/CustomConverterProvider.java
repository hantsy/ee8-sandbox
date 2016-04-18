/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.mvc.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomConverterProvider implements ParamConverterProvider {

    final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

        if (rawType.getName().equals(LocalDate.class.getName())) {

            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    return value != null ? (T) LocalDate.parse(value, DATE_FORMAT) : null;
                }

                @Override
                public String toString(T value) {
                    return value != null ? ((LocalDate) value).format(DATE_FORMAT) : "";
                }
            };
        } else if (rawType.getName().equals(boolean.class.getName())) {

            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    return (T) Boolean.valueOf(value != null && value.equals("on"));
                }

                @Override
                public String toString(T value) {
                    return ((Boolean) value) ? "on" : "";
                }
            };

        } else if (rawType.getName().equals(int.class.getName())) {

            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {

                    try {
                        return (T) (Integer) Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                    }

                    return (T) (Integer) 0;
                }

                @Override
                public String toString(T value) {
                    return "" + value;
                }
            };

        } else if (rawType.getName().equals(String.class.getName())) {

            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {

                    if (value == null || value.trim().equals("")) {
                        return null;
                    }
                    return (T) value;
                }

                @Override
                public String toString(T value) {
                    return "" + value;
                }
            };

        } else {
            return null;
        }
    }
}
