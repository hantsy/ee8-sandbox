package com.hantsylabs.example.ee8.mvc.domain;

/**
 *
 * @author hantsy
 */
public class TaskNotFoundException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(String message) {
        super(message);
    }
    
    
}
