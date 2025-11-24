package model;

public class Fornecedor extends Pessoa {
    private String nomeFantasia;
    private String cnpj;

    public Fornecedor() {
        super();
    }

    public Fornecedor(String nome, String cpf, String nomeFantasia, String cnpj) {
        super(nome, cpf);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

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

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Dados do Fornecedor ---");
        System.out.println("Responsável: " + getNome());
        System.out.println("Empresa: " + this.nomeFantasia);
        System.out.println("CNPJ: " + this.cnpj);
    }

    @Override
    public String obterIdentificacao() {
        return "Fornecedor: " + nomeFantasia;
    }
}