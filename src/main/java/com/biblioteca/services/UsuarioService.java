package com.biblioteca.services;

import com.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Usuario> buscarPorId(long id){

        //        for(int i = 0; i < usuarios.size(); i++){
//            Usuario usuario = usuarios.get(i);
//
//            if(usuario.getId() == id){
//                return usuario;
//            }
//        }
//        return null;

        return usuarios.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<Usuario> buscarPorEmail(String email){

        return usuarios.stream().filter(user -> user.getEmail().equals(email)).findFirst();
//        for(int i = 0; i < usuarios.size(); i++){
//            Usuario usuario = usuarios.get(i);
//
//            if(usuario.getEmail().equals(email)){
//                return usuario;
//            }
//        }
//        return null;
    }
}
