/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author hantsy
 */
@Named
@Path("persons")
public class PersonResource {

    @Context
    UriInfo uriInfo;

    @Inject
    PersonRepository persons;

    @GET()
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("q") String q) {
        List personResult = persons.findAll().stream()
                .filter(p -> p.getFirstName().contains(q))
                .collect(Collectors.toList());
        return Response.ok(personResult).build();
    }

    @GET
    @Path("{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        return Response.ok(persons.findById(id)).build();
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(@Valid Person person) {
        Person saved = persons.save(person);
        return Response.created(uriInfo.getBaseUriBuilder().path("persons/{id}").build(saved.getId())).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id,
            @Valid Person person) {
        Person saved = persons.update(id, person);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        persons.delete(id);
        return Response.noContent().build();
    }
}
