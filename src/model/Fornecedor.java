package model;

public class Fornecedor extends Pessoa {

    private String nomeFantasia;
    private String cnpj;

    // Construtor vazio
    public Fornecedor() {
        super();
    }

    // Construtor completo
    public Fornecedor(String nome, String cpf, String email, String nomeFantasia, String cnpj) {
        // Nota: Como Pessoa exige CPF, no caso do fornecedor podemos passar o CPF do responsável
        // ou adaptar a modelagem. Para simplificar, passamos os dados básicos para o pai.
        super(nome, cpf, email);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    // Implementação OBRIGATÓRIA do método abstrato
    @Override
    public String obterIdentificacao() {
        return "Fornecedor: " + nomeFantasia + " (CNPJ: " + cnpj + ")";
    }

    // Getters e Setters
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}