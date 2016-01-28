package com.hantsylabs.example.ee8.mvc.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.Viewable;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("tasks")
@Controller
public class TaskController {

    @Inject
    Logger log;

    @Inject
    private Models models;

    @Inject
    private BindingResult validationResult;

    @GET
    @View("tasks.xhtml")
    public void allTasks() {

    }

    @GET
    @Path("{id}")
    public Viewable taskDetails(@PathParam("id") String id) {
        log.log(Level.INFO, "get task by id@{0}", id);

        return new Viewable("details.xhtml");
    }

    @GET
    @Path("/add")
    public Viewable add() {
        log.log(Level.INFO, "add new task");

        return new Viewable("new.xhtml");
    }

    @POST
    public Response save(@Valid @BeanParam TaskForm form) {
        log.log(Level.INFO, "saving new task @{0}", form);

        if (validationResult.isFailed()) {

        }

        return Response.ok("redirect:tasks").build();
    }

    @GET
    @Path("{id}/edit")
    public Viewable edit(@PathParam("id") String id) {
        log.log(Level.INFO, "edit task @{0}", id);

        return new Viewable("edit.xhtml");
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam(value = "id") String id, @Valid @BeanParam TaskForm form) {
        log.log(Level.INFO, "updating existed task@id:{0}, form data:{1}", new Object[]{id, form});

        if (validationResult.isFailed()) {

        }

        return Response.ok("redirect:tasks").build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        log.log(Level.INFO, "edit task @{0}", id);

        return Response.ok("redirect:tasks").build();
    }

    @PostConstruct
    private void init() {
        log.config(() -> this.getClass().getSimpleName() + " created");
    }
}
