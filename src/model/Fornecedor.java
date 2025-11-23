package model;

public class Fornecedor extends Pessoa implements Serializable {

        private String cnpj;
        private String nomeEmpresa;

        public Fornecedor() {
            super();
        }

        public Fornecedor(String nome, String cpf, String email, String telefone, String endereco,
                          String cnpj, String nomeEmpresa) {
            super(nome, cpf, email, telefone, endereco);
            setCnpj(cnpj);
            setNomeEmpresa(nomeEmpresa);
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            if (cnpj == null || cnpj.length() != 14) {
                throw new IllegalArgumentException("CNPJ inválido.");
            }
            this.cnpj = cnpj;
        }

        public String getNomeEmpresa() {
            return nomeEmpresa;
        }

        public void setNomeEmpresa(String nomeEmpresa) {
            if (nomeEmpresa == null || nomeEmpresa.isBlank()) {
                throw new IllegalArgumentException("Nome da empresa inválido.");
            }
            this.nomeEmpresa = nomeEmpresa;
        }

        @Override
        public String getTipo() {
            return "Fornecedor";
        }

        @Override
        public String toString() {
            return super.toString() +
                    "\nCNPJ: " + cnpj +
                    "\nEmpresa: " + nomeEmpresa;
        }
    }

