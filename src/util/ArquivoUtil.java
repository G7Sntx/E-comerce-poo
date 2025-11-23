package util;
import java.io.*;

public class ArquivoUtil {

        public static void salvar(Object dados, String nomeArquivo) {
            try {
                FileOutputStream arquivo = new FileOutputStream(nomeArquivo);
                ObjectOutputStream gravador = new ObjectOutputStream(arquivo);

                gravador.writeObject(dados);

                gravador.close();
                arquivo.close();
                System.out.println("Dados salvos com sucesso em: " + nomeArquivo);

            } catch (Exception e) {
                System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        }

        public static Object carregar(String nomeArquivo) {
            try {
                File f = new File(nomeArquivo);
                if (!f.exists()) {
                    return null;
                }

                FileInputStream arquivo = new FileInputStream(f);
                ObjectInputStream leitor = new ObjectInputStream(arquivo);

                Object dados = leitor.readObject();

                leitor.close();
                arquivo.close();

                return dados;

            } catch (Exception e) {
                System.out.println("Erro ao ler arquivo (pode ser a primeira execução): " + e.getMessage());
                return null;
            }
        }

        public static void escreverRelatorio(String texto, String nomeArquivo) {
            try {
                FileWriter arquivo = new FileWriter(nomeArquivo);
                PrintWriter gravador = new PrintWriter(arquivo);

                gravador.print(texto);

                gravador.close();
                arquivo.close();

            } catch (IOException e) {
                System.out.println("Erro ao gerar relatório: " + e.getMessage());
            }
        }
    }
