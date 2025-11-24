package model;

public class Fornecedor extends Pessoa {

    private static final long serialVersionUID = 1L;

    private String nomeFantasia;
    private String cnpj;

    public Fornecedor() {
        super();
    }

    public Fornecedor(String nome, String cpf, String email, String nomeFantasia, String cnpj) {
        super(nome, cpf, email);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    @Override
    public String obterIdentificacao() {
        return "Fornecedor: " + nomeFantasia + " (CNPJ: " + cnpj + ")";
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
}