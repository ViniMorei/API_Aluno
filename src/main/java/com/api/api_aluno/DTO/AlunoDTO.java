package com.api.api_aluno.DTO;

import com.api.api_aluno.Entity.Aluno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    private Long matricula;
    private String nome;
    private int idade;
    private String email;

    public AlunoDTO(Aluno aluno) {
        this.matricula = aluno.getMatricula();
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.email = aluno.getEmail();
    }
}
