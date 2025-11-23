package util;

import model.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    public static void salvar(Object dados, String nomeArquivo) {

        File arquivo = new File(nomeArquivo);
        if (arquivo.getParentFile() != null) {
            arquivo.getParentFile().mkdirs();
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(dados);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados serializados: " + e.getMessage());
        }
    }

    public static Object carregar(String nomeArquivo) {
        File f = new File(nomeArquivo);
        if (!f.exists()) return null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return in.readObject();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }

    public static void escreverRelatorio(String texto, String nomeArquivo) {
        try (FileWriter fw = new FileWriter(nomeArquivo)) {
            fw.write(texto);
            System.out.println("Relatório gravado com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar relatório: " + e.getMessage());
        }
    }

    public static List<Produto> lerProdutosCSV(String caminho) {
        List<Produto> produtos = new ArrayList<>();
        File f = new File(caminho);

        if (!f.exists()) {
            System.out.println("Arquivo CSV não encontrado: " + caminho);
            return produtos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty() || linha.startsWith("#")) continue;

                String[] dados = linha.split(";");

                if (dados.length >= 4) {
                    int id = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();
                    double preco = Double.parseDouble(dados[2].trim().replace(",", "."));
                    int estoque = Integer.parseInt(dados[3].trim());

                    Produto p = new Produto(id, nome, preco, estoque);
                    produtos.add(p);
                }
            }
            System.out.println("CSV importado com sucesso! Produtos carregados: " + produtos.size());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }
        return produtos;
    }
}