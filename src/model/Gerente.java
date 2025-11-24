package model;

public class Gerente extends Funcionario {
    private static final long serialVersionUID = 1L;
    private double bonusAnual;


    public Gerente() {
        super();
    }


    public Gerente(String nome, String cpf, String cargo, double salario, double bonusAnual) {
        super(nome, cpf, cargo, salario);
        this.bonusAnual = bonusAnual;
    }

    public double getBonusAnual() {
        return bonusAnual;
    }

    public void setBonusAnual(double bonusAnual) {
        if (bonusAnual < 0) {
            throw new IllegalArgumentException("Bônus não pode ser negativo");
        }
        this.bonusAnual = bonusAnual;
    }

    @Override
    public String obterIdentificacao() {
        return "Gerente: " + getNome() + " (Cargo: " + getCargo() + ")";
    }

    public double calcularSalarioComBonus() {
        return getSalario() + (bonusAnual / 12);
    }
}