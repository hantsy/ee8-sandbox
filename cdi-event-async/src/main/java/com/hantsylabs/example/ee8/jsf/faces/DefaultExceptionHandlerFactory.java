
package com.hantsylabs.example.ee8.jsf.faces;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class DefaultExceptionHandlerFactory extends ExceptionHandlerFactory {

    public DefaultExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        super(parent);
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new DefaultExceptionHandler(this.getWrapped().getExceptionHandler());
    }
}
