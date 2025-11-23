package util;

import java.io.*;

public class ArquivoUtil {

    public static void salvar(Object dados, String nomeArquivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(dados);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static Object carregar(String nomeArquivo) {
        File f = new File(nomeArquivo);
        if (!f.exists()) return null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return in.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public static void escreverRelatorio(String texto, String nomeArquivo) {
        try (FileWriter fw = new FileWriter(nomeArquivo)) {
            fw.write(texto);
            System.out.println("Arquivo gravado: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar relatório.");
        }
    }
}