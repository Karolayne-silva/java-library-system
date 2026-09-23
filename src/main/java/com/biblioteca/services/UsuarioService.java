package com.biblioteca.services;

import com.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    List<Usuario> usuarios = new ArrayList<>();
    public Usuario cadastrarUsuario(long id, String nome, String email){
        Usuario usuario = new Usuario(id, nome, email);

        usuarios.add(usuario);

        return usuario;
    }

    public List<Usuario> listarUsuarios(){
        return usuarios;
    }

    public Usuario buscarPorId(long id){
        for(int i = 0; i < usuarios.size(); i++){
            Usuario usuario = usuarios.get(i);

            if(usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorEmail(String email){
        for(int i = 0; i < usuarios.size(); i++){
            Usuario usuario = usuarios.get(i);

            if(usuario.getEmail().equals(email)){
                return usuario;
            }
        }
        return null;
    }
}
