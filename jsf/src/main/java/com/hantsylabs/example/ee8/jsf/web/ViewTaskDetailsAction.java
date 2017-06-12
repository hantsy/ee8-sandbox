package com.hantsylabs.example.ee8.jsf.web;

import com.hantsylabs.example.ee8.jsf.domain.Task;
import com.hantsylabs.example.ee8.jsf.domain.TaskNotFoundException;
import com.hantsylabs.example.ee8.jsf.domain.TaskRepository;
import java.io.Serializable;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import java.util.logging.Logger;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hantsy
 *
 */
@Named("viewTaskAction")
@ViewScoped()
public class ViewTaskDetailsAction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //@Inject
    private static final Logger log = Logger.getLogger(ViewTaskDetailsAction.class.getName());

    @Inject
    private TaskRepository taskRepository;

    @NotNull
    private Long taskId;

    private Task task;

    public void init() {

        log.log(Level.INFO, " get task of id @" + taskId);

        task = taskRepository.findById(taskId);

        if (task == null) {
            throw new TaskNotFoundException(taskId);
        }

    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Task getTask() {
        return task;
    }

}
