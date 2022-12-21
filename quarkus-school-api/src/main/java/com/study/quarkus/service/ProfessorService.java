package main.java.com.study.quarkus.service;

import com.study.quarkus.Professor;

import main.java.com.study.quarkus.dto.ProfessorRequest;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class ProfessorService {
    
    private final ProfessorMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(ProfessorService.class);

    public List<ProfessorResponse> retornaTodos(){
        log.info("Listando professores...");
        final List<Professor> listaDeProfessores = Professor.listAll();
        return mapper.toResponse(listaDeProfessores);
    }

    public ProfessorResponse buscaPorId(int id){
        log.info("Buscando professor id--{}", id);

        Professor professor = Professor.findById(id);
        return mapper.toResponse(professor);
    }

    @Transactional
    public ProfessorResponse salvar(ProfessorRequest professorRequest){

        log.info("Salvando professor - {}", professorRequest);

        Professor entity = 
                Professor.builder()
                .name(professorRequest.getNome())
                .build();

        entity.persistAndFlush();

        return mapper.toResponse(entity);

    }

    @Transactional
    public ProfessorResponse atualiza(int id, ProfessorRequest professorRequest) {

        log.info("Atualizando professor id - {}, data - {}", id, professorRequest);

        Optional<Professor> professor = Professor.findByIdOptional(id);

        if (professor.isPresent()) {
            var entity = professor.get();
            entity.setName(professorRequest.getName());
            entity.persistAndFlush();
            return mapper.toResponse(entity);
        }

        return new ProfessorResponse();
    }

    @Transactional
    public void removeProfessor(int id) {
        log.info("Deletando professor id - {}", id);
        Professor.findByIdOptional(id).ifPresent(PanacheEntityBase::delete);
    }
}