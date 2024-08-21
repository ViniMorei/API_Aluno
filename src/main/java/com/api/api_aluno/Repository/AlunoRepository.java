package com.api.api_aluno.Repository;

import com.api.api_aluno.Entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}
