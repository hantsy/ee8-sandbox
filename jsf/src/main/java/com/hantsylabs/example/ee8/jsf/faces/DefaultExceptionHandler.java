package com.hantsylabs.example.ee8.jsf.faces;

import com.hantsylabs.example.ee8.jsf.domain.TaskNotFoundException;
import com.sun.faces.context.FacesFileNotFoundException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class DefaultExceptionHandler extends ExceptionHandlerWrapper {

    //@Inject
    private static final  Logger log= Logger.getLogger(DefaultExceptionHandler.class.getName());

    private ExceptionHandler wrapped;

    public DefaultExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        log.log(Level.INFO, "invoking custom ExceptionHandlder...");
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();
            log.log(Level.INFO, "Exception@" + t);
            log.log(Level.INFO, "ExceptionHandlder began.");
            log.log(Level.INFO, "t instanceof FacesException@" + (t instanceof FacesException));
         //   log.log(Level.INFO, "t instanceof FacesFileNotFoundException@" + (t instanceof FacesFileNotFoundException));
            if (t instanceof ViewExpiredException) {
                try {
                    handleViewExpiredException((ViewExpiredException) t);
                } finally {
                    events.remove();
                }
            }

            if (t instanceof FacesFileNotFoundException || t instanceof TaskNotFoundException) {
                try {
                    handleNotFoundException((Exception) t);
                } finally {
                    events.remove();
                }
            }
            log.log(Level.INFO, "ExceptionHandlder end.");
            getWrapped().handle();
        }

    }

    private void handleViewExpiredException(ViewExpiredException vee) {
        log.log(Level.INFO, " handling viewExpiredException...");
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = vee.getViewId();
        log.log(Level.INFO, "view id @" + viewId);
        NavigationHandler nav
                = context.getApplication().getNavigationHandler();
        nav.handleNavigation(context, null, viewId);
        context.renderResponse();
    }

    private void handleNotFoundException(Exception e) {
        log.log(Level.INFO, "handling exception:...");
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = "/error.xhtml";
        log.log(Level.INFO, "view id @" + viewId);
        NavigationHandler nav
                = context.getApplication().getNavigationHandler();
        nav.handleNavigation(context, null, viewId);
        context.getViewRoot().getViewMap(true).put("ex", e);
        context.renderResponse();
    }
}
