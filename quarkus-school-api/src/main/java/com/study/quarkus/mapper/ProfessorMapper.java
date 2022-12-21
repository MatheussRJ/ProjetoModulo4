package main.java.com.study.quarkus.mapper;

import com.study.quarkus.dto.ProfessorRequest;
import com.study.quarkus.dto.ProfessorResponse;
import com.study.quarkus.model.Professor;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@ApplicationScoped
public class ProfessorMapper {

    public List<ProfessorResponse> toResponse(List<Professor> listaDeProfessores) {
        if (Objects.isNull(listaDeProfessores))
            return new ArrayList<>();

        return listaDeProfessores.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Professor toEntity(ProfessorRequest request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return Professor.builder()
                    .name(request.getName())
                    .build();
        }
    }

    public ProfessorResponse toResponse(Professor entity) {

        if (Objects.isNull(entity))
            return null;

        return ProfessorResponse.builder()
                .id(entity.getId())
                .name(entity.getName)
                .build();
    }

}
