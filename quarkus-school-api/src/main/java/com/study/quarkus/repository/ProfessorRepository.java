package main.java.com.study.quarkus.repository;

import javax.enterprise.context.ApplicationScoped;
import main.java.com.study.quarkus.model.Professor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Integer>{
    
}
