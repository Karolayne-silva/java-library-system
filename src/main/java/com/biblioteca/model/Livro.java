package com.biblioteca.model;

import com.biblioteca.enums.StatusLivro;

public class Livro {
    public long id;
    public String titulo;
    public String autor;
    public String categoria;
    public StatusLivro status;

    public Livro( long id, String titulo, String autor, String categoria) {
        this.titulo = titulo;
        this.id = id;
        this.autor = autor;
        this.categoria = categoria;
        this.status = StatusLivro.DISPONIVEL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", categoria='" + categoria + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
