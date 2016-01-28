package com.hantsylabs.example.ee8.mvc;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.mvc.event.BeforeControllerEvent;
import javax.mvc.event.BeforeProcessViewEvent;

@ApplicationScoped
public class MvcEventListener {

    @Inject Logger LOGGER;

    private void onControllerMatched(@Observes BeforeControllerEvent event) {
        LOGGER.info(() -> "Controller matched for " + event.getUriInfo().getRequestUri());
    }

    private void onViewEngineSelected(@Observes BeforeProcessViewEvent event) {
        LOGGER.info(() -> "View engine: " + event.getEngine());
    }

    @PostConstruct
    private void init() {
        LOGGER.config(() -> this.getClass().getSimpleName() + " created");
    }
}
