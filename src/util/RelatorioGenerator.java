package util;
import model.Pedido;
import model.ItemPedido;
import java.util.ArrayList;

public class RelatorioGenerator {

        public static void gerarRelatorio(ArrayList<Pedido> listaDePedidos) {
            String conteudo = "=== RELATÓRIO DE VENDAS ===\n\n";
            double totalGeral = 0;

            // Loop simples para percorrer os pedidos
            for (Pedido p : listaDePedidos) {
                conteudo += "Pedido ID: " + p.getId() + "\n";
                conteudo += "Data: " + p.getData() + "\n"; // Usa o toString da data
                conteudo += "Cliente: " + p.getCliente().getNome() + "\n";
                conteudo += "Valor do Pedido: R$ " + p.getValorTotal() + "\n";
                conteudo += "---------------------------\n";

                totalGeral += p.getValorTotal();
            }

            conteudo += "\nFATURAMENTO TOTAL: R$ " + totalGeral;

            // Chama o ArquivoUtil para salvar no disco
            ArquivoUtil.escreverRelatorio(conteudo, "relatorio_vendas.txt");
            System.out.println("Relatório gerado! Verifique o arquivo 'relatorio_vendas.txt'.");
        }
    }
