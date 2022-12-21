package main.java.com.study.quarkus.service;

import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import javax.transaction.Transactional;
import java.util.Optional;

import main.java.com.study.quarkus.model.Aluno;
import main.java.com.study.quarkus.repository.AlunoRepository;
import main.java.com.study.quarkus.dto.AlunoRequest;
import main.java.com.study.quarkus.dto.AlunoResponse;
import main.java.com.study.quarkus.mapper.AlunoMapper;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public class AlunoService {
    
    private final AlunoMapper mapper;
    private final AlunoRepository repository;

    private static final Logger log = LoggerFactory.getLogger(AlunoService.class);

    public List<AlunoResponse> retornaTodos(){
        log.info("Listando alunos...");
        final List<Aluno> listaDeAlunos = repository.listAll();
        return mapper.toResponse(listaDeAlunos);
    }

    public AlunoResponse buscaPorId(int id){
        log.info("Buscando aluno id--{}", id);

        Aluno aluno = repository.findById(id);
        return mapper.toResponse(aluno);
    }

    @Transactional
    public AlunoResponse salvar(AlunoRequest alunoRequest){

        log.info("Salvando aluno - {}", alunoRequest);

        Aluno entity = 
                Aluno.builder()
                .name(alunoRequest.getName())
                .build();

        repository.persistAndFlush(entity);

        return mapper.toResponse(entity);

    }

    @Transactional
    public AlunoResponse atualiza(int id, AlunoRequest alunoRequest) {

        log.info("Atualizando aluno id - {}, data - {}", id, alunoRequest);

        Optional<Aluno> aluno = repository.findByIdOptional(id);

        if (aluno.isPresent()) {
            var entity = aluno.get();
            entity.setName(alunoRequest.getName());
            repository.persistAndFlush(entity);
            return mapper.toResponse(entity);
        }

        return new AlunoResponse();
    }

    @Transactional
    public void removeAluno(int id) {
        log.info("Deletando aluno id - {}", id);
        repository.findByIdOptional(id).ifPresent(repository::delete);
    }

}
