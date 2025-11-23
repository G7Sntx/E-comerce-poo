package app;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

            public static void main(String[] args) {

                int opcao;

                do {
                    System.out.println("\n===== MENU PRINCIPAL =====");
                    System.out.println("1 - Cadastrar Cliente");
                    System.out.println("2 - Cadastrar Produto");
                    System.out.println("3 - Criar Pedido");
                    System.out.println("4 - Listar Clientes");
                    System.out.println("5 - Listar Produtos");
                    System.out.println("6 - Listar Pedidos");
                    System.out.println("0 - Sair");
                    System.out.print("Escolha: ");
                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1 -> cadastrarCliente();
                        case 2 -> cadastrarProduto();
                        case 3 -> criarPedido();
                        case 4 -> clientes.forEach(System.out::println);
                        case 5 -> produtos.forEach(System.out::println);
                        case 6 -> pedidos.forEach(System.out::println);
                        case 0 -> System.out.println("Saindo...");
                        default -> System.out.println("Opção inválida!");
                    }

                } while (opcao != 0);
            }

            private static void cadastrarCliente() {
                System.out.println("\n--- Cadastro de Cliente ---");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("CPF (11 dígitos): ");
                String cpf = sc.nextLine();
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
                System.out.println("Cliente cadastrado!");
            }

            private static void cadastrarProduto() {
                System.out.println("\n--- Cadastro de Produto ---");
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Preço: ");
                double preco = sc.nextDouble();
                System.out.print("Estoque: ");
                int est = sc.nextInt();
                sc.nextLine();

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
                int id = sc.nextInt();
                sc.nextLine();

                System.out.println("Escolha o cliente pelo índice:");
                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println(i + " - " + clientes.get(i).getNome());
                }
                int idxCliente = sc.nextInt();
                sc.nextLine();

                Pedido pedido = new Pedido(id, clientes.get(idxCliente));

                int op;
                do {
                    System.out.println("\nAdicionar produtos:");
                    for (int i = 0; i < produtos.size(); i++) {
                        System.out.println(i + " - " + produtos.get(i).getNome());
                    }

                    System.out.print("Escolha o produto (ou -1 para sair): ");
                    op = sc.nextInt();
                    sc.nextLine();

                    if (op >= 0 && op < produtos.size()) {
                        pedido.adicionarProduto(produtos.get(op));
                        System.out.println("Produto adicionado!");
                    }

                } while (op != -1);

                pedidos.add(pedido);
                System.out.println("Pedido criado com sucesso!");
            }
        }
