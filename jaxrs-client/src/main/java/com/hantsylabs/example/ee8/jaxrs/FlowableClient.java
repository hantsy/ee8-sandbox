/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.rx.rxjava2.RxFlowableInvoker;
import org.glassfish.jersey.client.rx.rxjava2.RxFlowableInvokerProvider;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 *
 * @author hantsy
 */
public class FlowableClient {

    public final static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();

        client.register(RxFlowableInvokerProvider.class);
        WebTarget target = client.target("http://localhost:8080/jaxrs-async/rest/ejb");

        target.request()
                .rx(RxFlowableInvoker.class)
                .get(String.class)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:" + e.getMessage());
                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println("onNext:" + t);
                    }

                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe:" + s);
                        s.request(1);                    
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

    }
}
