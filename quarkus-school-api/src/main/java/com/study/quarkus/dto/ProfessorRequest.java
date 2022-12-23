package main.java.com.study.quarkus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=false) 

public class ProfessorRequest {
    
    @Size(min=2, message="Tamanho do nome não pode ser menor que 2")
    @NotBlank(message="Nome não pode ser vazio ou nulo")
    private String name;

}
