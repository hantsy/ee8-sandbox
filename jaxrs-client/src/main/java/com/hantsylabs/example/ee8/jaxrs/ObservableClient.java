/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import rx.Observer;

/**
 *
 * @author hantsy
 */
public class ObservableClient {

    public final static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();
        
        client.register(RxObservableInvokerProvider.class);
        WebTarget target = client.target("http://localhost:8080/jaxrs-async/rest/ejb");

        target.request()
                .rx(RxObservableInvoker.class)
                .get(String.class)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:" + e.getMessage());
                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println("onNext:" + t);
                    }
                });

    }
}
