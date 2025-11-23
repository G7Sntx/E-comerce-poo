package model;

import java.util.ArrayList;

public class Pedido {

    private int idPedido;
    private Cliente cliente;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private double valorTotal;

    public Pedido() {
    }

    public Pedido(int idPedido, Cliente cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto p) {
        produtos.add(p);
        valorTotal += p.getPreco();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido #" + idPedido +
                "\nCliente: " + cliente.getNome() +
                "\nProdutos: " + produtos.size() +
                "\nTotal: R$ " + valorTotal;
    }
}
