/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.mvc.web;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is modified from the one shipped with Spring framework to support PUT, DELETE methods in form submission.
 * 
 * @author hantsy
 */
@WebFilter(filterName = "HiddenHttpMethodFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class HiddenHttpMethodFilter implements Filter {

    @Inject
    Logger log;

    /**
     * Default method parameter: {@code _method}
     */
    public static final String DEFAULT_METHOD_PARAM = "_method";

    private String methodParam = DEFAULT_METHOD_PARAM;

    /**
     * Set the parameter name to look for HTTP methods.
     *
     * @see #DEFAULT_METHOD_PARAM
     */
    public void setMethodParam(String methodParam) {
        this.methodParam = methodParam;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        log.log(Level.INFO, "entering HttpHiddenFilter...");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String paramValue = request.getParameter(this.methodParam);
        log.log(Level.INFO, "paramValue @" + paramValue);
        log.log(Level.INFO, "request method @" + request.getMethod());

        if ("POST".equals(request.getMethod()) && paramValue != null && paramValue.trim().length() > 0) {
            String method = paramValue.toUpperCase(Locale.ENGLISH);
            HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, method);
            filterChain.doFilter(wrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    /**
     * Simple {@link HttpServletRequest} wrapper that returns the supplied
     * method for {@link HttpServletRequest#getMethod()}.
     */
    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }

}
