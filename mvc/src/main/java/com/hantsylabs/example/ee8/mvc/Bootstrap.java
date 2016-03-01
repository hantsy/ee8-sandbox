/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.mvc;

import com.hantsylabs.example.ee8.mvc.domain.Task;
import com.hantsylabs.example.ee8.mvc.domain.TaskRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author hantsy
 */
@Stateless
@Startup
@Transactional(Transactional.TxType.REQUIRED)
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
