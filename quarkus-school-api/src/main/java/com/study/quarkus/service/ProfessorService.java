package main.java.com.study.quarkus.service;


import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import javax.transaction.Transactional;
import java.util.Optional;

import main.java.com.study.quarkus.model.Professor;
import main.java.com.study.quarkus.repository.ProfessorRepository;
import main.java.com.study.quarkus.dto.ProfessorRequest;
import main.java.com.study.quarkus.dto.ProfessorResponse;
import main.java.com.study.quarkus.mapper.ProfessorMapper;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class ProfessorService {
    
    private final ProfessorMapper mapper;
    private final ProfessorRepository repository;

    private static final Logger log = LoggerFactory.getLogger(ProfessorService.class);

    //substituir Professor.metodoDeBAnco por repository
    public List<ProfessorResponse> retornaTodos(){
        log.info("Listando professores...");
        final List<Professor> listaDeProfessores = repository.listAll();
        return mapper.toResponse(listaDeProfessores);
    }

    public ProfessorResponse buscaPorId(int id){
        log.info("Buscando professor id--{}", id);

        Professor professor = repository.findById(id);
        return mapper.toResponse(professor);
    }

    @Transactional
    public ProfessorResponse salvar(ProfessorRequest professorRequest){

        log.info("Salvando professor - {}", professorRequest);

        Professor entity = 
                Professor.builder()
                .name(professorRequest.getName())
                .build();

        repository.persistAndFlush(entity);

        return mapper.toResponse(entity);

    }

    @Transactional
    public ProfessorResponse atualiza(int id, ProfessorRequest professorRequest) {

        log.info("Atualizando professor id - {}, data - {}", id, professorRequest);

        Optional<Professor> professor = repository.findByIdOptional(id);

        if (professor.isPresent()) {
            var entity = professor.get();
            entity.setName(professorRequest.getName());
            repository.persistAndFlush(entity);
            return mapper.toResponse(entity);
        }

        return new ProfessorResponse();
    }

    @Transactional
    public void removeProfessor(int id) {
        log.info("Deletando professor id - {}", id);
        repository.findByIdOptional(id).ifPresent(repository::delete);
    }
}