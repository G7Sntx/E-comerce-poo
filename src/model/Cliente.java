package model;
import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {

    private String codigoCliente;

    public Cliente() {
        super();
    }

    public Cliente(String nome, String cpf, String email, String telefone, String endereco, String codigoCliente) {
        super(nome, cpf, email, telefone, endereco);
        setCodigoCliente(codigoCliente);
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        if (codigoCliente == null || codigoCliente.isBlank()) {
            throw new IllegalArgumentException("Código inválido.");
        }
        this.codigoCliente = codigoCliente;
    }


    @Override
    public String obterIdentificacao() {
        return getTipo() + ": " + getNome() + " (Cód: " + codigoCliente + ")";
    }
    public void exibirDetalhes() {
        System.out.println("Sou um Cliente VIP: " + getNome());
    }
    public String getTipo() {
        return "Cliente";
    }
}