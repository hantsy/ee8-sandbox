package com.hantsylabs.example.ee8.mvc.domain;

public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException(Long taskId) {
		super(String.format("task id:%s not found!", taskId));
	}

}
