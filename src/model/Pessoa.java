package model;

public abstract class Pessoa {

    protected String nome;
    protected String cpf;
    protected String email;
    protected String telefone;
    protected String endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String email, String telefone, String endereco) {
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio.");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Endereço inválido.");
        }
        this.endereco = endereco;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nEmail: " + email +
                "\nTelefone: " + telefone +
                "\nEndereço: " + endereco;
    }
}
