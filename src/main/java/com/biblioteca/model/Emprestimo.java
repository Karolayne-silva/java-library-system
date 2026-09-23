package com.biblioteca.model;

import com.biblioteca.enums.StatusEmprestimo;

import java.time.LocalDate;

public class Emprestimo {
    private long id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private StatusEmprestimo status;

    public Emprestimo(Usuario usuario, long id, Livro livro) {
        this.usuario = usuario;
        this.id = id;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = this.dataEmprestimo.plusDays(7);
        this.dataDevolucao = null;
        this.status = StatusEmprestimo.ATIVO;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", livro=" + livro +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevistaDevolucao=" + dataPrevistaDevolucao +
                ", dataDevolucao=" + dataDevolucao +
                ", status=" + status +
                '}';
    }
}
