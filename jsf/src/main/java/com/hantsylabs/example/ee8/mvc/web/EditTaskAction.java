package com.hantsylabs.example.ee8.mvc.web;

import com.hantsylabs.example.ee8.mvc.domain.Task;
import com.hantsylabs.example.ee8.mvc.domain.TaskNotFoundException;
import com.hantsylabs.example.ee8.mvc.domain.TaskRepository;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;

/**
 * 
 * @author hantsy
 *
 */
@Named("editTaskAction")
@ViewScoped()
public class EditTaskAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject Logger log;

	@Inject
	private TaskRepository taskRepository;

	private Long taskId;

	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void init() {
		log.log(Level.INFO," get task of id @" + taskId);
		
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (taskId == null) {
				task = new Task();
			} else {
				task = taskRepository.findById(taskId);
				if (task == null) {
					throw new TaskNotFoundException(taskId);
				}
			}
		}
	}

	public String save() {
		log.log(Level.INFO,"saving task@" + task);
		this.task = taskRepository.save(task);
		FacesMessage info= new FacesMessage(FacesMessage.SEVERITY_INFO, "Task is saved successfully!",   "Task is saved successfully!");
		FacesContext.getCurrentInstance().addMessage(null, info);
		
		return "/tasks.xhtml?faces-redirect=true";
	}

}
