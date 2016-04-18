package com.hantsylabs.example.ee8.mvc.config;

import com.hantsylabs.example.ee8.mvc.web.TaskController;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.mvc.engine.ViewEngine;
import javax.mvc.security.Csrf;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("mvc")
public class MvcConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(TaskController.class);
        classes.add(CustomConverterProvider.class);
        classes.add(CustomExceptionMapper.class);

        return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();

        props.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.EXPLICIT);

        //view folder
        //props.put(ViewEngine.DEFAULT_VIEW_FOLDER, ViewEngine.VIEW_FOLDER);
        return super.getProperties();
    }

}
