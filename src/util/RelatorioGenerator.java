package util;

import model.Pedido;
import java.util.ArrayList;

public class RelatorioGenerator {

    public static void gerarRelatorio(ArrayList<Pedido> lista) {
        String texto = "=== RELATORIO DE VENDAS ===\n\n";
        double totalGeral = 0;

        for (Pedido p : lista) {
            texto += "Data: " + p.getData() + " | Cliente: " + p.getCliente().getNome();
            texto += " | Valor: R$ " + p.calcularTotal() + "\n";
            totalGeral += p.calcularTotal();
        }

        texto += "\nTOTAL GERAL: R$ " + totalGeral;

        ArquivoUtil.escreverRelatorio(texto, "relatorio_vendas.txt");
    }
}