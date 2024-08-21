package com.api.api_aluno.Controller;

import java.util.List;

import com.api.api_aluno.Repository.AlunoRepository;
import com.api.api_aluno.Entity.Aluno;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@AllArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {
  AlunoRepository alunoRepository;

  

  @PostMapping("inserir")
  public Aluno saveAluno(@RequestBody Aluno aluno) {
      return alunoRepository.save(aluno);
  }

  @GetMapping(value = "listar")
  public List<Aluno> getAllAlunos() {
      return alunoRepository.findAll();
  }
  
  @GetMapping("/obter/{id}")
  public Aluno getAlunoById(@PathVariable Long id) {
      return alunoRepository.findById(id).get();
  }
  
  @PutMapping(value = "alterar")
  public void updateAluno(@RequestBody Aluno aluno) {
      if (aluno.getMatricula() > 0){
        alunoRepository.save(aluno);
      }
  }

  @DeleteMapping("/deletar/{id}")
  public void deleteAluno(@PathVariable Long id) {
    if (id > 0){
      alunoRepository.deleteById(id);
    }
  }
  
}
