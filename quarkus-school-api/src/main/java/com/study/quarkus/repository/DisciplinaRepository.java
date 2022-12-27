package main.java.com.study.quarkus.repository;


import main.java.com.study.quarkus.model.Disciplina;
import main.java.com.study.quarkus.model.Professor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisciplinaRepository  implements PanacheRepositoryBase<Disciplina, Integer> {

    //contador de titularidade
    public long contarTitularidadePorProfessor(Professor professor) {

        var query = find("titular", professor);
        return query.count();
    }
}