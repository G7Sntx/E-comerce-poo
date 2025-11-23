package model;

import java.io.Serializable;

public class Categoria implements Serializable {

    private int id;
    private String nome;


    public Categoria() {
    }

    public Categoria(int id, String nome) {
        setId(id);
        setNome(nome);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID da categoria deve ser positivo.");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio.");
        }
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria: " + nome + " (ID: " + id + ")";
    }
}