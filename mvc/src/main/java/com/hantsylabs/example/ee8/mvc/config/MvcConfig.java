package com.hantsylabs.example.ee8.mvc.config;

import com.hantsylabs.example.ee8.mvc.web.TaskController;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("mvc")
public class MvcConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes=new HashSet<>();
        classes.add(TaskController.class);
        classes.add(PrimitiveConverterProvider.class);
        classes.add(TaskNotFoundExceptionMapper.class);
        
        return classes;
    }
}
