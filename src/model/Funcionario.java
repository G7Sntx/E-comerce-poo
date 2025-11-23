package model;

public class Funcionario extends Pessoa implements Serializable {

        private String matricula;
        private String cargo;

        public Funcionario() {
            super();
        }

        public Funcionario(String nome, String cpf, String email, String telefone, String endereco,
                           String matricula, String cargo) {
            super(nome, cpf, email, telefone, endereco);
            setMatricula(matricula);
            setCargo(cargo);
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            if (matricula == null || matricula.isBlank()) {
                throw new IllegalArgumentException("Matrícula inválida.");
            }
            this.matricula = matricula;
        }

        public String getCargo() {
            return cargo;
        }

        public void setCargo(String cargo) {
            if (cargo == null || cargo.isBlank()) {
                throw new IllegalArgumentException("Cargo inválido.");
            }
            this.cargo = cargo;
        }

        @Override
        public String getTipo() {
            return "Funcionario";
        }

        @Override
        public String toString() {
            return super.toString() +
                    "\nMatrícula: " + matricula +
                    "\nCargo: " + cargo;
        }
    }


