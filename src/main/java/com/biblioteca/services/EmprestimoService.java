package com.biblioteca.services;

import com.biblioteca.enums.StatusEmprestimo;
import com.biblioteca.enums.StatusLivro;
import com.biblioteca.exceptions.EmprestimoNaoEncontradoException;
import com.biblioteca.exceptions.LivroNaoEncontradoException;
import com.biblioteca.exceptions.UsuarioNaoEncontradoException;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    private UsuarioService usuarioService;
    private LivroService livroService;

    private long proximoId = 1;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    //ele recebe as instancias/objeto de usuarioService e livroService que já existe.
    public EmprestimoService(
            UsuarioService usuarioService,
            LivroService livroService
    ) {
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    public Emprestimo realizarEmprestimo(long idUsuario, Long idLivro){
        Usuario usuarioExist = usuarioService.buscarPorId(idUsuario);

        if(usuarioExist == null){
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
        Livro livroExist = livroService.buscarPorId(idLivro);

        if(livroExist == null){
            throw new LivroNaoEncontradoException("Livro não encontrado.");
        }

        if(usuarioExist != null && livroExist != null){

            if(livroExist.getStatus() == StatusLivro.DISPONIVEL){

                Emprestimo emprestimo = new Emprestimo(usuarioExist, proximoId++, livroExist);

                livroExist.setStatus(StatusLivro.EMPRESTADO);

                emprestimos.add(emprestimo);

                return emprestimo;
            }
        }

        return null;
    }

    public Emprestimo buscaPorId(long id){
        for(int i = 0; i < emprestimos.size(); i++){
            Emprestimo emprestimo = emprestimos.get(i);

            if(emprestimo.getId() == id){
                return emprestimo;
            }
        }
        return null;
    }

    public Emprestimo devolverLivro(long id){
        Emprestimo emprestimoExists = buscaPorId(id);

        if(emprestimoExists == null){
            throw new EmprestimoNaoEncontradoException("Emprestimo não encontrado.");
        }

        if(emprestimoExists != null && emprestimoExists.getStatus() == StatusEmprestimo.ATIVO){
            emprestimoExists.setDataDevolucao(LocalDate.now());
            emprestimoExists.setStatus(StatusEmprestimo.DEVOLVIDO);

            Livro livroEmprestado = emprestimoExists.getLivro();
            livroEmprestado.setStatus(StatusLivro.DISPONIVEL);

            return emprestimoExists;
        }

        return null;
    }

    public List<Emprestimo> listarEmprestimos(){
        return emprestimos;
    }

    public List<Emprestimo> listarEmprestimosAtivos(){
        List<Emprestimo> ativados = new ArrayList<>();

        for(int i = 0; i < emprestimos.size(); i++){
            Emprestimo emprestimo = emprestimos.get(i);

            if(emprestimo.getStatus() == StatusEmprestimo.ATIVO){
                ativados.add(emprestimo);
            }
        }
        return ativados;
    }

    public List<Emprestimo> listarEmprestimoUsuario(long idUsuario){
        List<Emprestimo> emprestimosPorUsuario = new ArrayList<>();
        for(int i = 0; i < emprestimos.size(); i++){
            Emprestimo emprestimo = emprestimos.get(i);
            Usuario usuario = emprestimo.getUsuario();

            if(usuario.getId() == idUsuario){
                emprestimosPorUsuario.add(emprestimo);
            }

        }
        return emprestimosPorUsuario;
    }

    public void verificarEmprestimosAtrasados(){
        LocalDate dataAtual = LocalDate.now();
        for(int i = 0; i < emprestimos.size(); i++){
            Emprestimo emprestimo = emprestimos.get(i);

            if(emprestimo.getStatus() == StatusEmprestimo.ATIVO && dataAtual.isAfter(emprestimo.getDataPrevistaDevolucao())){
                emprestimo.setStatus(StatusEmprestimo.ATRASADO);
            }
        }
    }

    public List<Emprestimo> listarEmprestimosAtrasados(){
        List<Emprestimo> atrasados = new ArrayList<>();
        for(int i = 0; i < emprestimos.size(); i++){
            Emprestimo emprestimo = emprestimos.get(i);

            if(emprestimo.getStatus() == StatusEmprestimo.ATRASADO){
                atrasados.add(emprestimo);
            }

        }
        return atrasados;
    }
}
