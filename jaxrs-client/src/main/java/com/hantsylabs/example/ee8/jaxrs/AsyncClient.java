/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.concurrent.Future;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author hantsy
 */
public class AsyncClient {

    public final static void main(String[] args) throws Exception {

        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/jaxrs-async/rest/ejb");

        Future<String> future = target.request()
                .async()
                .get(String.class);

        System.out.println("ejb resource future:" + future.get());

        target.request()
                .async()
                .get(AsyncClient.responseInvocationCallback());
    }

    private static InvocationCallback<Response> responseInvocationCallback() {
        return new InvocationCallback<Response>() {
            @Override
            public void completed(Response res) {
                System.out.println("Status:" + res.getStatusInfo());
                System.out.println("Entity:" + res.getEntity());
                System.out.println("Request success!");
            }

            @Override
            public void failed(Throwable e) {
                System.out.println("Request failed!");
            }

        };
    }

}
