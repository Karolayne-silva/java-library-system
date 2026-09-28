package com.biblioteca.services;

import com.biblioteca.enums.StatusEmprestimo;
import com.biblioteca.enums.StatusLivro;
import com.biblioteca.exceptions.*;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmprestimoService {
    private final UsuarioService usuarioService;
    private final LivroService livroService;

    private long proximoId = 1;
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    //ele recebe as instancias/objeto de usuarioService e livroService que já existe.
    public EmprestimoService(
            UsuarioService usuarioService,
            LivroService livroService
    ) {
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    public Emprestimo realizarEmprestimo(long idUsuario, Long idLivro){
        Optional<Usuario> usuarioExist = usuarioService.buscarPorId(idUsuario);

        if(usuarioExist.isEmpty()){
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
        Optional<Livro> livroExist = livroService.buscarPorId(idLivro);

        if(livroExist.isEmpty()){
            throw new LivroNaoEncontradoException("Livro não encontrado.");
        }

        if (livroExist.get().getStatus() != StatusLivro.DISPONIVEL) {
            throw new LivroIndisponivelException("Livro não está disponível.");
        }

        long count = countEmprestimosUsuario(idUsuario);
        if(count >= 3){
            throw new LimiteEmprestimoException("Limite de emprestimos excedido");
        }

        Emprestimo emprestimo = new Emprestimo(usuarioExist.get(), proximoId++, livroExist.get());

        livroExist.get().setStatus(StatusLivro.EMPRESTADO);

        emprestimos.add(emprestimo);

        return emprestimo;

    }

    private long countEmprestimosUsuario(long idUsuario){
        return emprestimos.stream().filter(emprestimo -> emprestimo.getUsuario().getId() == idUsuario && emprestimo.getStatus() == StatusEmprestimo.ATIVO).count();
    }

    public Optional<Emprestimo> buscaPorId(long id){

        return emprestimos.stream().filter(emprestimo -> emprestimo.getId() == id).findFirst();
//        for(int i = 0; i < emprestimos.size(); i++){
//            Emprestimo emprestimo = emprestimos.get(i);
//
//            if(emprestimo.getId() == id){
//                return emprestimo;
//            }
//        }
//        return null;
    }

    public Emprestimo devolverLivro(long id){
        Optional<Emprestimo> emprestimoExists = buscaPorId(id);

        if(emprestimoExists.isEmpty()){
            throw new EmprestimoNaoEncontradoException("Emprestimo não encontrado.");
        }

        Emprestimo emprestimo = emprestimoExists.get();

        if(emprestimo.getStatus() == StatusEmprestimo.ATIVO){
            emprestimo.setDataDevolucao(LocalDate.now());
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

            Livro livroEmprestado = emprestimo.getLivro();
            livroEmprestado.setStatus(StatusLivro.DISPONIVEL);

            return emprestimo;
        }

        return null;
    }

    public List<Emprestimo> listarEmprestimos(){
        return emprestimos;
    }

    public List<Emprestimo> listarEmprestimosAtivos(){
        List<Emprestimo> ativados = new ArrayList<>();

        emprestimos.stream().filter(emprestimo -> emprestimo.getStatus() == StatusEmprestimo.ATIVO).forEach(ativados::add);
//        for(int i = 0; i < emprestimos.size(); i++){
//            Emprestimo emprestimo = emprestimos.get(i);
//
//            if(emprestimo.getStatus() == StatusEmprestimo.ATIVO){
//                ativados.add(emprestimo);
//            }
//        }
       return ativados;
    }

    public List<Emprestimo> listarEmprestimoUsuario(long idUsuario){
        List<Emprestimo> emprestimosPorUsuario = new ArrayList<>();

         emprestimos.stream().filter(emprestimo -> emprestimo.getUsuario().getId() == idUsuario).forEach(emprestimosPorUsuario::add);
//        for(int i = 0; i < emprestimos.size(); i++){
//            Emprestimo emprestimo = emprestimos.get(i);
//            Usuario usuario = emprestimo.getUsuario();
//
//            if(usuario.getId() == idUsuario){
//                emprestimosPorUsuario.add(emprestimo);
//            }
//
//        }
        return emprestimosPorUsuario;
    }

    public void verificarEmprestimosAtrasados(){
        LocalDate dataAtual = LocalDate.now();

        emprestimos.stream().filter(emprestimo -> emprestimo.getStatus() == StatusEmprestimo.ATIVO && dataAtual.isAfter(emprestimo.getDataPrevistaDevolucao())).forEach(emprestimo -> emprestimo.setStatus(StatusEmprestimo.ATRASADO));
//        for(int i = 0; i < emprestimos.size(); i++){
//            Emprestimo emprestimo = emprestimos.get(i);
//
//            if(emprestimo.getStatus() == StatusEmprestimo.ATIVO && dataAtual.isAfter(emprestimo.getDataPrevistaDevolucao())){
//                emprestimo.setStatus(StatusEmprestimo.ATRASADO);
//            }
//        }
    }

    public List<Emprestimo> listarEmprestimosAtrasados(){
        List<Emprestimo> atrasados = new ArrayList<>();

        emprestimos.stream().filter(emprestimo -> emprestimo.getStatus() == StatusEmprestimo.ATRASADO).forEach(atrasados::add);
//        for(int i = 0; i < emprestimos.size(); i++){
//            Emprestimo emprestimo = emprestimos.get(i);
//
//            if(emprestimo.getStatus() == StatusEmprestimo.ATRASADO){
//                atrasados.add(emprestimo);
//            }
//
//        }
        return atrasados;
    }
}
