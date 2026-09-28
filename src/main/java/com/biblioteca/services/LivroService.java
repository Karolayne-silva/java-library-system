package com.biblioteca.services;

import com.biblioteca.enums.StatusLivro;
import com.biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroService {
    public List<Livro> livros = new ArrayList<>();

    public Livro cadastrarLivro(long id, String titulo, String autor, String categoria){

        Livro livro = new Livro(id, titulo, autor, categoria);

        livros.add(livro);

        return livro;

    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public Optional<Livro> buscarPorId(long id){

         return livros.stream().filter(livro -> livro.getId() == id).findFirst();
//        for(int i = 0; i < livros.size(); i++){
//            Livro livro = livros.get(i);
//
//            if(livro.getId() == id){
//                return livro;
//            }
//        }
//        return null;
    }

    public Optional<Livro> buscarPorTitulo(String titulo){

        return livros.stream().filter(livro -> livro.getTitulo().equals(titulo)).findFirst();
//        for(int i = 0; i < livros.size(); i++){
//            Livro livro = livros.get(i);
//
//            if(livro.getTitulo().equals(titulo)){
//                return livro;
//            }
//        }
//        return null;
    }

    public List<Livro> ListarLivrosDisponiveis(){
        List<Livro> disponiveis = new ArrayList<>();

        livros.stream().filter(livro -> livro.getStatus() == StatusLivro.DISPONIVEL).forEach(disponiveis::add);
//        for(int i = 0; i < livros.size(); i++){
//            Livro livro = livros.get(i);
//
//            if(livro.getStatus().equals(StatusLivro.DISPONIVEL)){
//                disponiveis.add(livro);
//            }
//        }
        return disponiveis;
    }

    public List<Livro> ListarLivrosEmprestados(){
        List<Livro> emprestados = new ArrayList<>();

        livros.stream().filter(livro -> livro.getStatus() == StatusLivro.EMPRESTADO).forEach(emprestados::add);
//        for(int i = 0; i < livros.size(); i++){
//            Livro livro = livros.get(i);
//
//            if(livro.getStatus().equals(StatusLivro.EMPRESTADO)){
//                emprestados.add(livro);
//            }
//        }

        return emprestados;
    }
}
