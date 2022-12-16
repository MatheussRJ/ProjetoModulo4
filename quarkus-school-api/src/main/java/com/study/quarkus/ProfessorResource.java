package com.study.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/professor")
public class ProfessorResource {

    private static final Logger log = LoggerFactory.getLogger(ProfessorResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaAlunos() {
        log.info("Listando todos os alunos");
        return Response
                .ok()
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaAlunoPorId(@PathParam ("id") int id) {
        log.info("Busca aluno por id");
        return Response
                .ok()
                .status(Response.Status.OK)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvarAluno(final ProfessorDto professor) {           
            log.info("Novo professor cadastrado {}", professor);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        }
    

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response AtualizaProfessor(@PathParam("id") int id, ProfessorDto professorDto) {
        log.info("Atualizando professor {}", id);
            //professor.put(id, professorDto);
            return Response
                    .ok(professorDto)
                    .status(Response.Status.OK)
                    .build();
        }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeProfessor(@PathParam("id") int id) {
        log.info("Removendo professor {}", id);
            //alunos.remove(id);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
    }