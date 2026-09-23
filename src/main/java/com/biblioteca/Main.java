package com.biblioteca;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.services.LivroService;
import com.biblioteca.services.UsuarioService;

import java.util.List;

public class Main {
    public static void main(String[] args){
        LivroService livroService = new LivroService();


        livroService.cadastrarLivro(1L, "Livro 1", "Karol", "romance");
        livroService.cadastrarLivro(2L, "Livro 2", "Karolayne", "romance");

        List<Livro> livros = livroService.listarLivros();

        //crio uma variavel do tipo Livro pra guardar o retorno do service
        Livro livro1 = livroService.buscarPorId(1);
        System.out.println("Livro encontrado: " + livro1);

        Livro livroTitulo = livroService.buscarPorTitulo("Livro 2");
        System.out.println("Livro com o titulo encontrado" + livroTitulo);

        List<Livro> disponiveis = livroService.ListarLivrosDisponiveis();
        System.out.println("Livros disponiveis" + disponiveis);

        List<Livro> emprestados = livroService.ListarLivrosEmprestados();
        System.out.println("Emprestados: "+ emprestados);

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario1 = usuarioService.cadastrarUsuario(1L, "Samantha", "samantha@email.com");
        Usuario usuario2 = usuarioService.cadastrarUsuario(2L, "Edna", "edna@email.com");
        System.out.println(usuario1);
        System.out.println(usuario2);

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        System.out.println("Todos usuários" + usuarios);

        Usuario usuarioId1 = usuarioService.buscarPorId(1);
        System.out.println("Usuario id 1: " + usuarioId1);

        Usuario usuarioEmail = usuarioService.buscarPorEmail("edna@email.com");
        System.out.println("Usuario pelo email: " + usuarioEmail);
    }
}
