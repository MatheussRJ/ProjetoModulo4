package main.java.com.study.quarkus;


import main.java.com.study.quarkus.dto.DisciplinaRequest;
import main.java.com.study.quarkus.dto.ErrorResponse;
import main.java.com.study.quarkus.service.DisciplinaService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/disciplinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class DisciplinaResource {

    private final DisciplinaService service;

    @GET
    public Response listaDisciplinas() {
        final var response = service.retornaTodos();

        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    public Response buscaDisciplinaPorId(@PathParam("id") int id) {

        final var response = service.buscaPorId(id);

        return Response.ok(response).build();
    }

    @POST
    public Response salvarDisciplina(final DisciplinaRequest disciplinaRequest) {
        try {
            final var response = service.salvar(disciplinaRequest);

            return Response
                    .status(Response.Status.CREATED)
                    .entity(response)
                    .build();

        } catch(ConstraintViolationException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.createFromValidation(e))
                    .build();
        }
    }

    
    @PUT
    @Path("/{id}/titular/{idProfessor}")
    public Response atualizaTitular(@PathParam("id") int idDisciplina, @PathParam("idProfessor") int idProfessor) {
        final var response = service.atualizaTitular(idDisciplina, idProfessor);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

}

