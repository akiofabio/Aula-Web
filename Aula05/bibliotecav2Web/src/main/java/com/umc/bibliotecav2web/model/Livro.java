package com.umc.bibliotecav2web.model;

public class Livro {
    private String id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroCopiasDisponiveis;

    // Construtor
    public Livro(String titulo, String autor, int anoPublicacao, int numeroCopiasDisponiveis) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.numeroCopiasDisponiveis = numeroCopiasDisponiveis;
    }

    public Livro() {
    }

    public Livro(String id, String titulo, String autor, int anoPublicacao, int numeroCopiasDisponiveis) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.numeroCopiasDisponiveis = numeroCopiasDisponiveis;
    }

    public Livro(String id) {
        this.id = id;
    }
    
    
    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getNumeroCopiasDisponiveis() {
        return numeroCopiasDisponiveis;
    }

    public void setNumeroCopiasDisponiveis(int numeroCopiasDisponiveis) {
        this.numeroCopiasDisponiveis = numeroCopiasDisponiveis;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
}
