/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.mvc.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author hantsy
 */
@RequestScoped
@Named("formatters")
public class Formatters {

    static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;

    public String formatDate(LocalDate data) {
        return DATE_FORMATTER.format(data);
    }

}
