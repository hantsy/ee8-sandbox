package com.hantsylabs.example.ee8.mvc.web;

import com.hantsylabs.example.ee8.mvc.domain.Task;
import com.hantsylabs.example.ee8.mvc.domain.TaskRepository;
import java.util.List;
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
import javax.validation.constraints.NotNull;
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

    @Inject
    TaskRepository taskRepostory;

    @GET
    @View("tasks.jspx")
    public void allTasks() {
        log.log(Level.INFO, "fetching all tasks");

        List<Task> todotasks = taskRepostory.findByStatus(Task.Status.TODO);
        List<Task> doingtasks = taskRepostory.findByStatus(Task.Status.DOING);
        List<Task> donetasks = taskRepostory.findByStatus(Task.Status.DONE);

        log.log(Level.INFO, "got all tasks: todotasks@{0}, doingtasks@{1}, donetasks@{2}", new Object[]{todotasks.size(), doingtasks.size(), donetasks.size()});

        models.put("todotasks", todotasks);
        models.put("doingtasks", doingtasks);
        models.put("donetasks", donetasks);
    }

    @GET
    @Path("{id}")
    public Viewable taskDetails(@PathParam("id") @NotNull String id) {
        log.log(Level.INFO, "get task by id@{0}", id);

        return new Viewable("details.jspx");
    }

    @GET
    @Path("/add")
    public Viewable add() {
        log.log(Level.INFO, "add new task");

        return new Viewable("new.jspx");
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

        return new Viewable("edit.jspx");
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
