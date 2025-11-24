package app;

import model.*;
import util.*;
import exceptions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    private static ArrayList<Pessoa> pessoas = new ArrayList<>();
    private static ArrayList<Loja> lojas = new ArrayList<>();

    public static void main(String[] args) {

        carregarDados();
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
            System.out.println("8 - DEMONSTRAR POLIMORFISMO (ArrayList<Pessoa>)");
            System.out.println("9 - DEMONSTRAR RELACIONAMENTO 1:N (Loja)");
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
                case 8: demonstrarPolimorfismo(); break;
                case 9: demonstrarRelacionamento1N(); break;
                case 0:
                    System.out.println("Salvando dados e saindo...");
                    salvarDados();
                    break;
                default: System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private static void salvarDados() {
        ArquivoUtil.salvar(clientes, "clientes.db");
        ArquivoUtil.salvar(produtos, "produtos.db");
        ArquivoUtil.salvar(pedidos, "pedidos.db");

        ArquivoUtil.salvar(lojas, "lojas.db");
        ArquivoUtil.salvar(pessoas, "pessoas.db");
    }

    private static void carregarDados() {
        try {
            Object c = ArquivoUtil.carregar("clientes.db");
            if (c != null) clientes = (ArrayList<Cliente>) c;

            Object p = ArquivoUtil.carregar("produtos.db");
            if (p != null) produtos = (ArrayList<Produto>) p;

            Object ped = ArquivoUtil.carregar("pedidos.db");
            if (ped != null) pedidos = (ArrayList<Pedido>) ped;

            Object loj = ArquivoUtil.carregar("lojas.db");
            if (loj != null) lojas = (ArrayList<Loja>) loj;

            Object pes = ArquivoUtil.carregar("pessoas.db");
            if (pes != null) pessoas = (ArrayList<Pessoa>) pes;

        } catch (Exception e) {
            System.out.println("Base de dados vazia ou nova.");
        }
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
            pessoas.add(c);

            System.out.println("Cliente cadastrado com sucesso!");

        } catch (CPFInvalidoException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- Cadastro de Produto ---");
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
    }

    private static void criarPedido() {
        System.out.println("\n--- Criar Pedido ---");

        if (clientes.isEmpty() || produtos.isEmpty()) {
            System.out.println("Cadastre pelo menos 1 cliente e 1 produto.");
            return;
        }

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
            System.out.println("ID: " + p.getId() + " | " + p.getNome() + " | R$ " + p.getPreco());
        }
    }

    private static void listarPedidos() {
        System.out.println("\n--- Pedidos ---");
        for (Pedido p : pedidos) {
            System.out.println("ID: " + p.getId() + " | Data: " + p.getData() + " | Cliente: " + p.getCliente().getNome() + " | Total: R$ " + p.calcularTotal());
        }
    }

    private static void demonstrarPolimorfismo() {
        System.out.println("\n========================================");
        System.out.println("DEMONSTRAÇÃO: POLIMORFISMO COM ArrayList<Pessoa>");
        System.out.println("========================================");

        if (pessoas.isEmpty()) {
            System.out.println("\nPopulando ArrayList polimórfico com diferentes tipos de Pessoa...\n");

            for (Cliente c : clientes) {
                if (!pessoas.contains(c)) pessoas.add(c);
            }

            Funcionario f1 = new Funcionario("Carlos Souza", "11111111111", "carlos@empresa.com",
                    "Vendedor", 2500.00);
            Fornecedor forn1 = new Fornecedor("Roberto Lima", "33333333333", "roberto@fornecedor.com",
                    "Distribuidora XYZ", "12345678000199");
            Gerente g1 = new Gerente("Patricia Oliveira", "44444444444", "Gerente de Vendas",
                    5000.00, 12000.00);

            pessoas.add(f1);
            pessoas.add(forn1);
            pessoas.add(g1);


            pessoas.add(f1);
            pessoas.add(forn1);
            pessoas.add(g1);

            System.out.println("✅ ArrayList<Pessoa> populado com " + pessoas.size() + " objetos!");
        }

        System.out.println("\n--- POLIMORFISMO EM AÇÃO ---");
        System.out.println("Chamando o método obterIdentificacao() para cada objeto:\n");

        for (Pessoa p : pessoas) {
            System.out.println("-> " + p.obterIdentificacao());
        }

        System.out.println("\n📌 EXPLICAÇÃO:");
        System.out.println("- A variável é do tipo Pessoa, mas o método executado é o da classe real (Cliente, Funcionario, etc.).");
        System.out.println("========================================\n");
    }

    private static void demonstrarRelacionamento1N() {
        System.out.println("\n========================================");
        System.out.println("DEMONSTRAÇÃO: RELACIONAMENTO 1:N (Loja → Produtos)");
        System.out.println("========================================");

        if (lojas.isEmpty()) {
            System.out.println("\nCriando lojas e adicionando produtos...\n");

            Loja loja1 = new Loja(1, "Loja Centro", "12345678000100");
            Loja loja2 = new Loja(2, "Loja Shopping", "98765432000199");

            if (produtos.size() >= 3) {
                loja1.adicionarProduto(produtos.get(0));
                loja1.adicionarProduto(produtos.get(1));
                loja2.adicionarProduto(produtos.get(2));
            } else {
                System.out.println("⚠️ Cadastre mais produtos para a demonstração 1:N.");
            }

            lojas.add(loja1);
            lojas.add(loja2);

            System.out.println("\n✅ Lojas criadas e produtos adicionados!");
        }

        System.out.println("\n--- RELACIONAMENTO 1:N ---");
        System.out.println("UMA Loja pode ter VÁRIOS Produtos\n");

        for (Loja loja : lojas) {
            System.out.println("📍 " + loja.toString());
            loja.listarProdutos();
            System.out.println("💰 Valor total do estoque: R$ " +
                    String.format("%.2f", loja.calcularValorTotalEstoque()));
            System.out.println();
        }

        System.out.println("📌 EXPLICAÇÃO:");
        System.out.println("- Cada objeto Loja possui um ArrayList<Produto>.");
        System.out.println("- Isso é um relacionamento 1:N (um-para-muitos).");
        System.out.println("========================================\n");
    }


    private static void inicializarDadosTeste() {
        System.out.println("Gerando dados de teste para os últimos 6 meses...");
        Cliente c1 = new Cliente("Ana Souza", "11122233344", "ana@gmail.com", "9999-9999", "Rua A", "C01");
        Cliente c2 = new Cliente("Carlos Lima", "55566677788", "carlos@hotmail.com", "8888-8888", "Rua B", "C02");
        clientes.add(c1);
        clientes.add(c2);

        pessoas.add(c1);
        pessoas.add(c2);

        Produto p1 = new Produto(1, "Smartphone", 1500.00, 20);
        Produto p2 = new Produto(2, "Fone Bluetooth", 200.00, 50);
        Produto p3 = new Produto(3, "Capa Protetora", 50.00, 100);
        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);

        Pedido ped1 = new Pedido(101, c1, LocalDate.now().minusMonths(5));
        ped1.adicionarItem(new ItemPedido(p1, 1));
        pedidos.add(ped1);

        Pedido ped2 = new Pedido(102, c2, LocalDate.now().minusMonths(3));
        ped2.adicionarItem(new ItemPedido(p2, 2));
        ped2.adicionarItem(new ItemPedido(p3, 1));
        pedidos.add(ped2);

        Pedido ped3 = new Pedido(103, c1, LocalDate.now());
        ped3.adicionarItem(new ItemPedido(p3, 5));
        pedidos.add(ped3);
    }
}
