/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import com.google.common.util.concurrent.ListenableFuture;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.rx.guava.RxListenableFutureInvoker;
import org.glassfish.jersey.client.rx.guava.RxListenableFutureInvokerProvider;

/**
 *
 * @author hantsy
 */
public class ListenableFutureClient {

    public final static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();

        client.register(RxListenableFutureInvokerProvider.class);
        WebTarget target = client.target("http://localhost:8080/jaxrs-async/rest/ejb");

        ListenableFuture<String> future = target.request()
                .rx(RxListenableFutureInvoker.class)
                .get(String.class);

        System.out.println("ListenableFuture:" + future.get());

    }
}
