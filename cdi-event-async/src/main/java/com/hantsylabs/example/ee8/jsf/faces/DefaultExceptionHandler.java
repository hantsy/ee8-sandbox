package com.hantsylabs.example.ee8.jsf.faces;

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

    private static final Logger LOG = Logger.getLogger(DefaultExceptionHandler.class.getName());

    public DefaultExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() throws FacesException {
        LOG.log(Level.INFO, "invoking custom ExceptionHandlder...");
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();
            LOG.log(Level.INFO, "Exception@" + t.getClass().getName());
            LOG.log(Level.INFO, "ExceptionHandlder began.");
            LOG.log(Level.INFO, "t instanceof FacesException@" + (t instanceof FacesException));
            //   log.log(Level.INFO, "t instanceof FacesFileNotFoundException@" + (t instanceof FacesFileNotFoundException));
            t.printStackTrace();
            if (t instanceof ViewExpiredException) {
                try {
                    handleViewExpiredException((ViewExpiredException) t);
                } finally {
                    events.remove();
                }
            } else if (t instanceof FacesFileNotFoundException) {
                try {
                    handleNotFoundException((Exception) t);
                } finally {
                    events.remove();
                }
            } else {

                getWrapped().handle();
            }
            LOG.log(Level.INFO, "ExceptionHandlder end.");
        }

    }

    private void handleViewExpiredException(ViewExpiredException vee) {
        LOG.log(Level.INFO, " handling viewExpiredException...");
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = vee.getViewId();
        LOG.log(Level.INFO, "view id @" + viewId);
        NavigationHandler nav
                = context.getApplication().getNavigationHandler();
        nav.handleNavigation(context, null, viewId);
        context.renderResponse();
    }

    private void handleNotFoundException(Exception e) {
        LOG.log(Level.INFO, "handling exception:...");
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = "/error.xhtml";
        LOG.log(Level.INFO, "view id @" + viewId);
        NavigationHandler nav
                = context.getApplication().getNavigationHandler();
        nav.handleNavigation(context, null, viewId);
        context.getViewRoot().getViewMap(true).put("ex", e);
        context.renderResponse();
    }
}
