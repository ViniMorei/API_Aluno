package com.api.api_aluno.Factory;
import com.api.api_aluno.Entity.Aluno;

public interface AlunoFactory {
    Aluno createAluno(Long matricula, String nome, int idade, String email);
}
