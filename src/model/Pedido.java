package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pedido implements Serializable {

    private int id;
    private Cliente cliente;
    private LocalDate data;
    private ArrayList<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(int id, Cliente cliente, LocalDate data) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido i : itens) {
            total += i.calcularSubtotal();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        String dataFormatada = (data != null) ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Sem data";

        return "Pedido #" + id +
                "\nData: " + dataFormatada +
                "\nCliente: " + cliente.getNome() +
                "\nQtd Itens: " + itens.size() +
                "\nTotal: R$ " + String.format("%.2f", calcularTotal());
    }
}