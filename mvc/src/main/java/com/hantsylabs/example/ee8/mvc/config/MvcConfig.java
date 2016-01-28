package com.hantsylabs.example.ee8.mvc.config;

import com.hantsylabs.example.ee8.mvc.web.TaskController;
import java.util.Collections;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("mvc")
public class MvcConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Collections.singleton(TaskController.class);
    }
}
