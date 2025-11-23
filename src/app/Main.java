package app;

import model.*;
import util.*;
import exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {

        carregarDados();

        if (produtos.isEmpty()) {
            System.out.println("Lista de produtos vazia. Tentando importar do CSV...");
            produtos = ArquivoUtil.lerProdutosCSV("dados/produtos.csv");
        }

        if (clientes.isEmpty()) {
            inicializarDadosTeste();
        }

        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL (E-COMMERCE) =====");
            System.out.println("1 - Cadastrar Cliente (com Validação)");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("3 - Criar Pedido");
            System.out.println("4 - Listar Clientes");
            System.out.println("5 - Listar Produtos");
            System.out.println("6 - Listar Pedidos");
            System.out.println("7 - Gerar Relatório de Vendas (Arquivo)");
            System.out.println("0 - Sair e Salvar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1: cadastrarCliente(); break;
                case 2: cadastrarProduto(); break;
                case 3: criarPedido(); break;
                case 4: listarClientes(); break;
                case 5: listarProdutos(); break;
                case 6: listarPedidos(); break;
                case 7:
                    RelatorioGenerator.gerarRelatorio(pedidos);
                    break;
                case 0:
                    System.out.println("Salvando dados e saindo...");
                    salvarDados();
                    break;
                default: System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        try {
            System.out.println("\n--- Cadastro de Cliente ---");
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("CPF (11 dígitos): ");
            String cpf = sc.nextLine();

            if (!ValidadorCPF.validar(cpf)) {
                throw new CPFInvalidoException("CPF inválido! Tente novamente.");
            }

            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Telefone: ");
            String tel = sc.nextLine();
            System.out.print("Endereço: ");
            String end = sc.nextLine();
            System.out.print("Código do cliente: ");
            String cod = sc.nextLine();

            Cliente c = new Cliente(nome, cpf, email, tel, end, cod);
            clientes.add(c);
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (CPFInvalidoException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- Cadastro de Produto ---");
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Preço: ");
            double preco = Double.parseDouble(sc.nextLine());
            System.out.print("Estoque: ");
            int est = Integer.parseInt(sc.nextLine());

            Produto p = new Produto(id, nome, preco, est);
            produtos.add(p);
            System.out.println("Produto cadastrado!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite valores numéricos válidos.");
        }
    }

    private static void criarPedido() {
        System.out.println("\n--- Criar Pedido ---");

        if (clientes.isEmpty() || produtos.isEmpty()) {
            System.out.println("ERRO: Cadastre pelo menos 1 cliente e 1 produto antes.");
            return;
        }

        try {
            System.out.print("ID do pedido: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Escolha o cliente pelo índice:");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println(i + " - " + clientes.get(i).getNome());
            }
            int idxCliente = Integer.parseInt(sc.nextLine());

            if (idxCliente < 0 || idxCliente >= clientes.size()) {
                System.out.println("Cliente inválido.");
                return;
            }

            Pedido pedido = new Pedido(id, clientes.get(idxCliente), LocalDate.now());

            int op = -1;
            do {
                System.out.println("\nAdicionar produtos:");
                for (int i = 0; i < produtos.size(); i++) {
                    System.out.println(i + " - " + produtos.get(i).getNome() + " (R$ " + produtos.get(i).getPreco() + ")");
                }

                System.out.print("Escolha o produto (ou -1 para finalizar): ");
                try {
                    op = Integer.parseInt(sc.nextLine());
                } catch(Exception e) { op = -1; }

                if (op >= 0 && op < produtos.size()) {
                    System.out.print("Quantidade: ");
                    int qtd = Integer.parseInt(sc.nextLine());

                    Produto prodSelecionado = produtos.get(op);
                    ItemPedido item = new ItemPedido(prodSelecionado, qtd);
                    pedido.adicionarItem(item);
                    System.out.println("Item adicionado!");
                }

            } while (op != -1);

            pedidos.add(pedido);
            System.out.println("Pedido criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        System.out.println("\n--- Clientes ---");
        for (Cliente c : clientes) {
            System.out.println(c.obterIdentificacao() + " | CPF: " + c.getCpf());
        }
    }

    private static void listarProdutos() {
        System.out.println("\n--- Produtos ---");
        for (Produto p : produtos) {
            System.out.println("ID: " + p.getId() + " | " + p.getNome() + " | R$ " + p.getPreco() + " | Estoque: " + p.getEstoque());
        }
    }

    private static void listarPedidos() {
        System.out.println("\n--- Pedidos ---");
        for (Pedido p : pedidos) {
            System.out.println("ID: " + p.getId() + " | Data: " + p.getData() + " | Cliente: " + p.getCliente().getNome() + " | Total: R$ " + p.calcularTotal());
        }
    }

    private static void salvarDados() {
        ArquivoUtil.salvar(clientes, "dados/clientes.db");
        ArquivoUtil.salvar(produtos, "dados/produtos.db");
        ArquivoUtil.salvar(pedidos, "dados/pedidos.db");
    }

    @SuppressWarnings("unchecked")
    private static void carregarDados() {
        try {
            Object c = ArquivoUtil.carregar("dados/clientes.db");
            if (c != null) clientes = (List<Cliente>) c;

            Object p = ArquivoUtil.carregar("dados/produtos.db");
            if (p != null) produtos = (List<Produto>) p;

            Object ped = ArquivoUtil.carregar("dados/pedidos.db");
            if (ped != null) pedidos = (List<Pedido>) ped;
        } catch (Exception e) {
            System.out.println("Base de dados vazia ou nova.");
        }
    }

    private static void inicializarDadosTeste() {
        System.out.println("Gerando dados de teste (Hardcoded)...");
        Cliente c1 = new Cliente("Ana Souza", "11122233344", "ana@gmail.com", "9999-9999", "Rua A", "C01");
        Cliente c2 = new Cliente("Carlos Lima", "55566677788", "carlos@hotmail.com", "8888-8888", "Rua B", "C02");
        clientes.add(c1);
        clientes.add(c2);

        if (produtos.isEmpty()) {
            Produto p1 = new Produto(1, "Smartphone", 1500.00, 20);
            Produto p2 = new Produto(2, "Fone Bluetooth", 200.00, 50);
            Produto p3 = new Produto(3, "Capa Protetora", 50.00, 100);
            produtos.add(p1);
            produtos.add(p2);
            produtos.add(p3);
        }

        if (!produtos.isEmpty()) {
            Pedido ped1 = new Pedido(101, c1, LocalDate.now().minusMonths(5));
            ped1.adicionarItem(new ItemPedido(produtos.get(0), 1));
            pedidos.add(ped1);

            Pedido ped2 = new Pedido(102, c2, LocalDate.now().minusMonths(3));
            ped2.adicionarItem(new ItemPedido(produtos.get(1), 2));
            pedidos.add(ped2);
        }
    }
}