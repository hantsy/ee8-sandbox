/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.mvc.config;

import com.hantsylabs.example.ee8.mvc.domain.TaskNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author hantsy
 */
@Provider
public class TaskNotFoundExceptionMapper implements ExceptionMapper<TaskNotFoundException>{
    
    @Inject Logger log;
    
    @Inject Models models;

    @Override
    public Response toResponse(TaskNotFoundException exception) {
        log.log(Level.INFO, "handling exception : TaskNotFoundException");
        models.put("error", exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity("error.jspx").build();
    }
    
}
