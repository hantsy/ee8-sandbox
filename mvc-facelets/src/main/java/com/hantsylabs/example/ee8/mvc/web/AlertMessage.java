package com.hantsylabs.example.ee8.mvc.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.mvc.RedirectScoped;

/**
 * Used to transport messages back to the client.
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@RedirectScoped
@Named("flashMessage")
public class AlertMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Type {
        success, warning, danger, info;
    }

    private Type type = Type.info;
    private String text;
    private String code;

    public AlertMessage() {
    }

    public AlertMessage(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public AlertMessage(Type type, String code, String message) {
        this.type = type;
        this.code = code;
        this.text = message;
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public void notify(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public static AlertMessage success(String text) {
        return new AlertMessage(Type.success, text);
    }

    public static AlertMessage warning(String text) {
        return new AlertMessage(Type.warning, text);
    }

    public static AlertMessage danger(String text) {
        return new AlertMessage(Type.danger, text);
    }

    public static AlertMessage info(String text) {
        return new AlertMessage(Type.info, text);
    }

    private List<Error> errors = new ArrayList<>();

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void addError(String field, String code, String message) {
        this.errors.add(new Error(field, code, message));
    }

    public static class Error {

        private String code;
        private String message;
        private String field;
        
        public Error(){}

        private Error(String field, String code, String message) {
            this.field = field;
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

    }

}
