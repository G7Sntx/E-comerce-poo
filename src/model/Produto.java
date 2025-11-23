package model;
import java.io.Serializable;

public class Produto implements Serializable {

    private int id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto() {
    }

    public Produto(int id, String nome, double preco, int estoque) {
        setId(id);
        setNome(nome);
        setPreco(preco);
        setEstoque(estoque);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID inválido.");
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido.");
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) throw new IllegalArgumentException("Preço inválido.");
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0) throw new IllegalArgumentException("Estoque inválido.");
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Produto: " + nome +
                "\nID: " + id +
                "\nPreço: R$" + preco +
                "\nEstoque: " + estoque;
    }
}