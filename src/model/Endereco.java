package model;

import java.io.Serializable;

public class Endereco implements Serializable {

    private String logradouro; // Rua, Avenida...
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;

    // 1. Construtor Vazio (Obrigatório)
    public Endereco() {
    }

    // 2. Construtor Completo (Obrigatório)
    public Endereco(String logradouro, String numero, String bairro, String cidade, String cep) {
        setLogradouro(logradouro);
        setNumero(numero);
        setBairro(bairro);
        setCidade(cidade);
        setCep(cep);
    }

    // --- Getters e Setters com Validações ---

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        if (logradouro == null || logradouro.isBlank()) throw new IllegalArgumentException("Rua inválida.");
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero == null || numero.isBlank()) throw new IllegalArgumentException("Número inválido.");
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if (bairro == null || bairro.isBlank()) throw new IllegalArgumentException("Bairro inválido.");
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (cidade == null || cidade.isBlank()) throw new IllegalArgumentException("Cidade inválida.");
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        // Validação simples de CEP (8 dígitos)
        if (cep == null || cep.length() != 8) throw new IllegalArgumentException("CEP deve ter 8 números.");
        this.cep = cep;
    }

    @Override
    public String toString() {
        return logradouro + ", " + numero + " - " + bairro + " (" + cidade + ")";
    }
}