package com.study.quarkus;

import javax.annotation.processing.Generated;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//
/* import com.study.quarkus.dto.ProfessorDto;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response; */

@slf4j
@Path("/professor")
public class ProfessorResource {

    private static final Logger log = LoggerFactory.getLogger(ProfessorResource.class);

    //trecho para teste simples
    /* @GET 
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        log.info("Testando Endpoint...");
        return "Hello RestEasy!";
    } */

    @GET
    public Response listProfessors() {
        log.info("Listing professors");
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getProfessor(@PathParam("id") int id) {
        log.info("Getting professor id-{}", id);
        return Response.ok().build();
    }

    @POST
    public Response saveProfessor(final ProfessorDto professor) {
        log.info("Saving professor - {}", professor);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProfessor(@PathParam("id") int id, ProfessorDto professor) {
        log.info("Updating professor id - {}", id);
        return Response
                .ok(professor)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeProfessor(@PathParam("id") int id) {
        log.info("Deleting professor id - {}", id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    }