package com.hantsylabs.example.ee8.mvc;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class Resources {

    @Produces
    public Logger getLogger(InjectionPoint p) {
        return Logger.getLogger(p.getMember().getDeclaringClass().getName());
    }

}
