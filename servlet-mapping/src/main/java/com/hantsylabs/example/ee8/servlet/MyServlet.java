/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * refer: http://arjan-tijms.omnifaces.org/2016/04/servlet-40s-mapping-api-previewed-in.html
 * 
 * @author hantsy
 */
@WebServlet(name = "MyServlet",
        urlPatterns = {
            "/MyServlet",
            "/",
            "/path/*",
            "*.extension"
        }
)
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
          
        HttpServletMapping mapping = request.getHttpServletMapping();
         
        response.getWriter()
                .append("Mapping match:")
                .append(mapping.getMappingMatch().name())
                .append("\n")
                .append("Match value:")
                .append(mapping.getMatchValue())
                .append("\n")
                .append("Pattern:")
                .append(mapping.getPattern());
    }
}
