package com.sophia.biblioteca.controller;

import java.util.ArrayList;
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

import com.sophia.biblioteca.models.Emprestimo;
import com.sophia.biblioteca.models.ItemEmprestimo;
import com.sophia.biblioteca.models.Livro;
import com.sophia.biblioteca.repository.EmprestimoRepository;
import com.sophia.biblioteca.repository.LivroRepository;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private AlunoController alunoController;

    @Autowired
    private LivroController livroController;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ItemEmprestimoController itemEmprestimoController;

    //Requisições http
    @PostMapping
    public ResponseEntity<Emprestimo> cadastrarEmprestimo(@RequestBody Emprestimo emprestimo) {
        return ResponseEntity.ok(repository.save(emprestimo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping("aluno/{matricula}")
    public ResponseEntity<List<Emprestimo>> buscarPorMatricula(@PathVariable(value = "matricula") int matricula) {
        return ResponseEntity.ok(repository.findByMatricula(matricula));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() throws BadRequestException{
        // Teste
        // List<Long> idLivro = new ArrayList<Long>();
        // idLivro.add(3L);

        // System.out.println(emprestarLivro(987, idLivro));
        return ResponseEntity.ok(repository.findAll());
    }
    
    //Métodos de negócio
    public boolean emprestarLivro(int matricula, List<Long> livrosId) throws BadRequestException{
        Date dataEmprestimo = new Date();
        Date dataPrevista = new Date();
        //Verifica se o aluno está cadastrado
        if(!alunoController.verificaAluno(matricula)){
            throw new BadRequestException("Aluno não cadastrado");
        }
        
        //Verifica se o aluno tem débito
        if(!alunoController.verificaDebito(matricula)){
            throw new BadRequestException("Aluno com débito");
            
        }
        //Cria um emprestimo
        Emprestimo emprestimo = new Emprestimo(dataEmprestimo, null, 50.0, alunoController.buscarPorId(matricula).getBody());

        List<Livro> livros  = new ArrayList<Livro>();
        if(livrosId.size()<1){
            throw new BadRequestException("Nenhum livro selecionado");
        }

        
        //Para cada livro
        for (Long livro : livrosId) {
            //Verifica se o livro pode ser emprestado
            if(livroController.verificaLivro(livro)){
                Livro livroAux = livroController.buscarPorId(livro).getBody();
                livros.add(livroAux);
            }
            else{
                //Caso o livro não esteja disponível
                throw new BadRequestException("Livro não existe ou não está disponível");
            }

        }


        //Para cada livro
        List<ItemEmprestimo> itensEmprestimo = new ArrayList<ItemEmprestimo>();
        for (Livro livro : livros) {
                //Adiciona o livro ao emprestimo
                ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
                itemEmprestimo.setLivro(livro);
                itensEmprestimo.add(itemEmprestimo);
                dataPrevista = calculaDataDevolucao(itensEmprestimo);
                livro.setDisponivel(false);
                
        }
        emprestimo.setDataPrevista(dataPrevista);
        //Cadastra o emprestimo
        cadastrarEmprestimo(emprestimo);

        
        //Cadastra os itens do emprestimo
        for (ItemEmprestimo itemEmprestimo : itensEmprestimo) {
            itemEmprestimo.setEmprestimo(emprestimo);
            itemEmprestimo.setDataPrevista(dataPrevista);
            itemEmprestimoController.cadastrarItemEmprestimo(itemEmprestimo);
        }
        //Teste
        // System.out.println("Emprestimo ID:" + emprestimo.getIdEmprestimo());
        // for (ItemEmprestimo itemEmprestimo : itensEmprestimo) {
        //     System.out.println("ID:" + itemEmprestimo.getIdItemEmp());
        //     System.out.println("Data Devolução:" + itemEmprestimo.getDataDevolucao());
        //     System.out.println("Data Prevista:" + itemEmprestimo.getDataPrevista());
        //     System.out.println("ID Livro:" + itemEmprestimo.getLivro().getIdLivro());
        //     System.out.println("ID Emprestimo:" + itemEmprestimo.getEmprestimo().getIdEmprestimo());
        // }

        // Indisponibiliza os livros
        for (Livro livro : livros) {
            livro.setDisponivel(false);
            livroRepository.update(livro);
        }

        return true;
    
    }
        

    public Date calculaDataDevolucao(List<ItemEmprestimo> itensEmprestimo){
        Date dataEmprestimo = new Date();
        Date data_aux = itemEmprestimoController.calculaDataDevolucaoItem(dataEmprestimo, itensEmprestimo.get(0));
        Date data_atual;
        for (ItemEmprestimo item: itensEmprestimo) {
            data_atual = itemEmprestimoController.calculaDataDevolucaoItem(dataEmprestimo, item);
            if(data_atual.after(data_aux)){
                data_aux = data_atual;
            }
        }
        
        return data_aux;

    }
}
