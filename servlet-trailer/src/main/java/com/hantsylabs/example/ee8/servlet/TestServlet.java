/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class illustrates HTTP trailer API in Servlet.
 *
 * @author Shing Wai Chan
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/plain");
        res.addHeader("Transfer-encoding", "chunked");
        res.addHeader("TE", "trailers");
        res.addHeader("Trailer", "bar");

        StringBuilder sb = new StringBuilder();

        final InputStream in = req.getInputStream();
        int b;
        while ((b = in.read()) != -1) {
            sb.append((char) b);
        }

        String foo = null;
        int size = -1;

        if (req.isTrailerFieldsReady()) {
            Map<String, String> reqTrailerFields = req.getTrailerFields();
            size = reqTrailerFields.size();
            foo = reqTrailerFields.get("foo");
        }

        final String finalFoo = foo;
        final int finalSize = size;
        res.setTrailerFields(new Supplier<Map<String, String>>() {
            @Override
            public Map<String, String> get() {
                Map<String, String> map = new HashMap<>();
                map.put("bar", finalFoo + finalSize);
                return map;
            }
        });
        res.getWriter().write(sb.toString());
    }
}
