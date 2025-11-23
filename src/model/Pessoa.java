package model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {


    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;


    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String email) {
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
    }

    public Pessoa(String nome, String cpf, String email, String telefone, String endereco) {
        this(nome, cpf, email);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    public abstract String obterIdentificacao();


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio.");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos numéricos.");
        }
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido (precisa ter @).");
        }
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | CPF: " + cpf;
    }
}