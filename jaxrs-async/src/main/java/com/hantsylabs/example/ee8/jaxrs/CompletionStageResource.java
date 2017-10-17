/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author hantsy
 */
@Path("cs")
@Stateless
public class CompletionStageResource {

    @Resource
    private ManagedExecutorService executor;

    @Inject
    Logger LOG;

    @GET
    public CompletionStage<String> getAsync() {
        CompletionStage<String> cs = CompletableFuture
                .<String>supplyAsync(
                        () -> {
                            try {
                                LOG.log(Level.INFO, " execute long run task in CompletionStageResource");
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                LOG.log(Level.SEVERE, "error :" +ex.getMessage());
                            }
                            return "return CompletableFuture instead of @Suspended";
                        }
                       // , executor
                );

        return cs;
    }

}
