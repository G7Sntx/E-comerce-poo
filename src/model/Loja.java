package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Loja implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nome;
    private String cnpj;
    private ArrayList<Produto> produtos;
    
    public Loja() {
        this.produtos = new ArrayList<>();
    }
    
    public Loja(int id, String nome, String cnpj) {
        this();
        setId(id);
        setNome(nome);
        setCnpj(cnpj);
    }
    
    public void adicionarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        produtos.add(produto);
        System.out.println("Produto '" + produto.getNome() + "' adicionado à loja " + this.nome);
    }
    
    public void removerProduto(int idProduto) {
        boolean removido = false;
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == idProduto) {
                System.out.println("Produto '" + produtos.get(i).getNome() + "' removido da loja.");
                produtos.remove(i);
                removido = true;
                break;
            }
        }
        if (!removido) {
            System.out.println("Produto com ID " + idProduto + " não encontrado.");
        }
    }
    
    public Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Produto> buscarProdutosPorNome(String nome) {
        ArrayList<Produto> encontrados = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }
    
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("A loja " + nome + " não possui produtos cadastrados.");
            return;
        }
        
        System.out.println("\n=== Produtos da Loja: " + nome + " ===");
        for (Produto p : produtos) {
            System.out.println("ID: " + p.getId() + " | " + p.getNome() + 
                             " | R$ " + p.getPreco() + " | Estoque: " + p.getEstoque());
        }
        System.out.println("Total de produtos: " + produtos.size());
    }
    
    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Produto p : produtos) {
            total += p.getPreco() * p.getEstoque();
        }
        return total;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID da loja deve ser positivo.");
        }
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da loja não pode ser vazio.");
        }
        this.nome = nome;
    }
    
    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve ter 14 dígitos.");
        }
        this.cnpj = cnpj;
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
    
    @Override
    public String toString() {
        return "Loja: " + nome + " (CNPJ: " + cnpj + ") - " + produtos.size() + " produtos";
    }
}
