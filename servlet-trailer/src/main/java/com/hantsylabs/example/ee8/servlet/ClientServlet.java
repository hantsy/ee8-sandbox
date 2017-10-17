/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copied from Glassfish samples
 * 
 * This class is a client for generating and receving HTTP trailer.
 *
 * @author Shing Wai Chan
 */
@WebServlet("")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/plain");
        StringBuilder sb = new StringBuilder();

        String hostStr = req.getServerName();
        int port = req.getServerPort();

        try (
            Socket socket = new Socket(hostStr, port);
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input))
        ) {
            String reqStr = (new StringBuffer("POST /trailer-war/test HTTP/1.1\r\n")).
                append("Host: " + hostStr + "\r\n").
                append("Transfer-encoding: chunked\r\n").
                append("Connection: close\r\n").
                append("trailer: foo\r\n").
                append("\r\n").
                append("5\r\n").
                append("hello\r\n").
                append("0\r\n").
                append("foo: A\r\n").
                append("\r\n").
                toString();

            output.write(reqStr.getBytes(Charset.forName("US-ASCII")));

            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("bar")) {
                    sb.append(line).append("\r\n");
                }
            }
        }

        res.getWriter().write(sb.toString());
    }
}
