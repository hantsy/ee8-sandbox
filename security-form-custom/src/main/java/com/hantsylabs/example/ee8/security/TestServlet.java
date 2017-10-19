/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.security;

import java.io.IOException;
import javax.annotation.security.DeclareRoles;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copied from soteria test sample codes.
 * 
 * https://github.com/javaee/security-soteria/
 * 
 * @author hantsy
 */
@WebServlet("/servlet")
@DeclareRoles({ "foo", "bar", "kaz" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "foo"))
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String webName = null;
        if (request.getUserPrincipal() != null) {
            webName = request.getUserPrincipal().getName();
        }
        
        response.getWriter().write(
                "<html><body> This is a servlet <br><br>\n" +
        
                    "web username: " + webName + "<br><br>\n" +
                            
                    "web user has role \"foo\": " + request.isUserInRole("foo") + "<br>\n" +
                    "web user has role \"bar\": " + request.isUserInRole("bar") + "<br>\n" +
                    "web user has role \"kaz\": " + request.isUserInRole("kaz") + "<br><br>\n" + 

                        
                    "<form method=\"POST\">" +
                        "<input type=\"hidden\" name=\"logout\" value=\"true\"  >" +
                        "<input type=\"submit\" value=\"Logout\">" +
                    "</form>" +
                "</body></html>");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("true".equals(request.getParameter("logout"))) {
            request.logout();
            request.getSession().invalidate();
        }
        
        doGet(request, response);
    }

}