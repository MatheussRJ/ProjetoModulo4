package main.java.com.study.quarkus.repository;
import javax.enterprise.context.ApplicationScoped;
import main.java.com.study.quarkus.model.Aluno;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class AlunoRepository implements PanacheRepositoryBase<Aluno, Integer>{

    
}
