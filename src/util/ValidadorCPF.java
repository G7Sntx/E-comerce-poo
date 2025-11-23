package util;

public class ValidadorCPF {

        public static boolean validar(String cpf) {

            if (cpf == null) {
                return false;
            }

            String apenasNumeros = cpf.replace(".", "").replace("-", "");

            if (apenasNumeros.length() != 11) {
                return false;
            }
            return true;
        }
    }

