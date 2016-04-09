package com.hantsylabs.example.ee8.mvc.web;

import com.hantsylabs.example.ee8.mvc.domain.Task;
import com.hantsylabs.example.ee8.mvc.domain.TaskRepository;
import com.hantsylabs.example.ee8.mvc.web.AlertMessage.Type;
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
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

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
    TaskRepository taskRepository;

    @Inject
    AlertMessage flashMessage;

    @GET
    @View("tasks.jspx")
    public void allTasks() {
        log.log(Level.INFO, "fetching all tasks");

        List<Task> todotasks = taskRepository.findByStatus(Task.Status.TODO);
        List<Task> doingtasks = taskRepository.findByStatus(Task.Status.DOING);
        List<Task> donetasks = taskRepository.findByStatus(Task.Status.DONE);

        log.log(Level.INFO, "got all tasks: todotasks@{0}, doingtasks@{1}, donetasks@{2}", new Object[]{todotasks.size(), doingtasks.size(), donetasks.size()});

        models.put("todotasks", todotasks);
        models.put("doingtasks", doingtasks);
        models.put("donetasks", donetasks);

    }

    @GET
    @Path("{id}")
    public Viewable taskDetails(@PathParam("id") @NotNull Long id) {
        log.log(Level.INFO, "get task by id@{0}", id);
        Task task = taskRepository.findById(id);

        models.put("details", task);
        return new Viewable("details.jspx");
    }

    @GET
    @Path("new")
    public Viewable add() {
        log.log(Level.INFO, "add new task");

        return new Viewable("add.jspx");
    }

    @POST
    //@CsrfValid
    @ValidateOnExecution(type = ExecutableType.NONE)
    public Response save(@Valid @BeanParam TaskForm form) {
        log.log(Level.INFO, "saving new task @{0}", form);

        if (validationResult.isFailed()) {
            AlertMessage alert = AlertMessage.danger("Validation voilations!");
            validationResult.getAllViolations()
                    .stream()
                    .forEach((ConstraintViolation t) -> {
                        String path = t.getPropertyPath().toString();
                        alert.addError(path.substring(path.lastIndexOf(".") + 1), "", t.getMessage());
                    });
            models.put("errors", alert);
            return Response.status(BAD_REQUEST).entity("add.jspx").build();
        }

        Task task = new Task();
        task.setName(form.getName());
        task.setDescription(form.getDescription());

        taskRepository.save(task);

        flashMessage.notify(Type.success, "Task was created successfully!");
        //models.put("flashMessage", flashMessage);

        return Response.ok("redirect:tasks").build();
    }

    @GET
    @Path("{id}/edit")
    public Viewable edit(@PathParam("id") Long id) {
        log.log(Level.INFO, "edit task @{0}", id);

        Task task = taskRepository.findById(id);

        models.put("task", task);
        return new Viewable("edit.jspx");
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam(value = "id") Long id, @Valid @BeanParam TaskForm form) {
        log.log(Level.INFO, "updating existed task@id:{0}, form data:{1}", new Object[]{id, form});

        if (validationResult.isFailed()) {
            AlertMessage alert = AlertMessage.danger("Validation voilations!");
            validationResult.getAllViolations()
                    .stream()
                    .forEach((ConstraintViolation t) -> {
                        alert.addError(t.getPropertyPath().toString(), "", t.getMessage());
                    });
            models.put("errors", alert);
            return Response.status(BAD_REQUEST).entity("edit.jsp").build();
        }

        Task task = taskRepository.findById(id);

        task.setName(form.getName());
        task.setDescription(form.getDescription());

        taskRepository.update(task);

        flashMessage.notify(Type.info, "Task was updated successfully!");

        return Response.ok("redirect:tasks").build();
    }

    @PUT
    @Path("{id}/status")
    public Response updateStatus(@PathParam(value = "id") Long id, @NotNull @FormParam(value = "status") String status) {
        log.log(Level.INFO, "updating status of the existed task@id:{0}, status:{1}", new Object[]{id, status});

        Task task = taskRepository.findById(id);

        task.setStatus(Task.Status.valueOf(status));

        taskRepository.update(task);

        flashMessage.notify(Type.info, "Task status was updated successfully!");

        return Response.ok("redirect:tasks").build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        log.log(Level.INFO, "deleting task @{0}", id);
        Task task = taskRepository.findById(id);
        taskRepository.delete(task);

        AlertMessage flashMessage = AlertMessage.danger("Task was deleted!");
        models.put("flashMessage", flashMessage);
        return Response.ok("redirect:tasks").build();
    }

    @PostConstruct
    private void init() {
        log.config(() -> this.getClass().getSimpleName() + " created");
    }
}
