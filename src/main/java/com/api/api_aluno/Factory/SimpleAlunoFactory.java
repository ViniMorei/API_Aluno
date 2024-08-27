package com.api.api_aluno.Factory;

import com.api.api_aluno.Entity.Aluno;
import org.springframework.stereotype.Component;

@Component
public class SimpleAlunoFactory implements AlunoFactory {
    
    @Override
    public Aluno createAluno(Long matricula, String nome, int idade, String email){
        return new Aluno(matricula, nome, idade, email);
    }
}
