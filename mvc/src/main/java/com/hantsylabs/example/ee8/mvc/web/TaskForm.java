package com.hantsylabs.example.ee8.mvc.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

@RequestScoped
public class TaskForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @FormParam("name")
    private String name;

    @NotNull
    @Size(min = 10, max = 2000)
    @FormParam("description")
    private String description;

    @NotNull
    @FormParam("duedate")
    private LocalDate dueDate;

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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.dueDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskForm other = (TaskForm) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.dueDate, other.dueDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TaskForm{" + "name=" + name + ", description=" + description + ", dueDate=" + dueDate + '}';
    }

}
