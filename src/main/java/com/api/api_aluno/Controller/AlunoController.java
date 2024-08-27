package com.api.api_aluno.Controller;

import com.api.api_aluno.Entity.Aluno;
import com.api.api_aluno.Repository.AlunoRepository;
import com.api.api_aluno.DTO.AlunoDTO;
import com.api.api_aluno.Factory.AlunoFactory;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;



import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;




@RestController
@AllArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {
  private final AlunoRepository alunoRepository;
  private final AlunoFactory alunoFactory;


  @PostMapping("inserir")
  public ResponseEntity<AlunoDTO> saveAluno(@RequestBody AlunoDTO alunoDTO){
    Aluno aluno = alunoFactory.createAluno(alunoDTO.getMatricula(), alunoDTO.getNome(),
                             alunoDTO.getIdade(), alunoDTO.getEmail());
    aluno = alunoRepository.save(aluno);
    return ResponseEntity.ok(new AlunoDTO(aluno));
  }


  @GetMapping("listar")
  public List<AlunoDTO> getAllAlunos(){
    return alunoRepository.findAll().stream()
                .map(aluno -> new AlunoDTO(aluno.getMatricula(), aluno.getNome(), aluno.getIdade(), aluno.getEmail()))
                .collect(Collectors.toList());
  }
  

  @GetMapping("obter/{id}")
  public ResponseEntity<AlunoDTO> getAlunoById(@PathVariable Long id) {
    return alunoRepository.findById(id)
            .map(aluno -> ResponseEntity.ok(new AlunoDTO(aluno.getMatricula(), aluno.getNome(), aluno.getIdade(), aluno.getEmail())))
            .orElse(ResponseEntity.notFound().build());
  }
  
  @PutMapping("/alterar")
    public ResponseEntity<AlunoDTO> updateAluno(@RequestBody AlunoDTO alunoDTO) {
        if (alunoRepository.existsById(alunoDTO.getMatricula())) {
            Aluno aluno = alunoFactory.createAluno(alunoDTO.getMatricula(), alunoDTO.getNome(), alunoDTO.getIdade(), alunoDTO.getEmail());
            alunoRepository.save(aluno);
            return ResponseEntity.ok(alunoDTO);
        }
        return ResponseEntity.notFound().build();
    }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
      if (alunoRepository.existsById(id)) {
          alunoRepository.deleteById(id);
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.notFound().build();
  
  }
}