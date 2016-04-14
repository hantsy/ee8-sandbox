/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import com.hantsylabs.example.ee8.jsf.domain.Task;
import com.hantsylabs.example.ee8.jsf.domain.TaskRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Startup
@Singleton
public class Bootstrap {

    @Inject
    Logger LOG;

    @Inject
    TaskRepository taskRepository;

    @PostConstruct
    public void init() {
        LOG.log(Level.INFO, "bootstraping application...");

        Task task = new Task();
        task.setName("My first task");
        task.setDescription("The description of my first task");
        task.setStatus(Task.Status.TODO);

        task = taskRepository.save(task);

        LOG.log(Level.INFO, "inserted task: {0}", new Object[]{task});
    }
}
