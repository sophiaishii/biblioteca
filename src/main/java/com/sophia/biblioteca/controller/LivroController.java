package com.sophia.biblioteca.controller;

import java.util.Date;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophia.biblioteca.models.Livro;
import com.sophia.biblioteca.models.Titulo;
import com.sophia.biblioteca.repository.ItemEmprestimoRepository;
import com.sophia.biblioteca.repository.LivroRepository;
import com.sophia.biblioteca.repository.TituloRepository;


@RestController
@RequestMapping("/livro")
public class LivroController {

    //Requisições http
    @Autowired
    private LivroRepository repository;

    @Autowired
    private TituloRepository tituloRepository;

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    @PostMapping
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        return ResponseEntity.ok(repository.save(livro));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Livro> buscarPorIsbn(String isbn) {
        return ResponseEntity.ok(repository.findByIsbn(isbn));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable(value = "id")Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }


    // Metodos de negocio
    public Boolean verificaLivro(Long id) throws BadRequestException{
        //Verifica se o livro já está cadastrado
        if (repository.findById(id) == null) {
            return false;
        }
        Livro livro = repository.findById(id);
        //Verifica se o livro está disponivel
        if(livro.getDisponivel() == false){
            // Pega a data de devolução do livro
            Date dataDevolucao = itemEmprestimoRepository.findByLivroId(id).getDataDevolucao();
            throw new BadRequestException("Livro não está disponível, data de devolução: " + dataDevolucao);
        }
        //Verifica se é exemplar
        if(livro.getExemplarBiblioteca() == true){
            throw new BadRequestException("Livro é exemplar da biblioteca");
        }
        return true;
    }

    public Integer verPrazo(String isbn){
        Titulo titulo = tituloRepository.findByIsbn(isbn);
        return titulo.getPrazo();
        
    }

}
