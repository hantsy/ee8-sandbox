package com.hantsylabs.example.ee8.bv;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

/**
 *
 * @author hantsy
 */
public class Todo implements Serializable {

    @NotNull
    @NotBlank
    private String name;
    
    private String description;
    
    @PastOrPresent
    private LocalDateTime creationDate = LocalDateTime.now();
    
    @Future
    private LocalDateTime dueDate = LocalDateTime.now().plusDays(2);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    

}
