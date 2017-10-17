/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.concurrent.CompletionStage;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author hantsy
 */
public class CompletionStageClient {

    public final static void main(String[] args) throws Exception {

        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/jaxrs-async/rest/ejb");

        CompletionStage<Void> future = target.request()
                .rx()
                .get(String.class)
                .thenAccept(t -> System.out.println(t));
              

    }
}
