package modelo;
import util.InterfaceUsuario;
import main.Main;

public class Financiamento {
    // Atributos
    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    // Método construtor
    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public double getValorImovel(){
        return this.valorImovel;
    }

    public int getPrazoFinanciamento() {
        return this.prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return this.taxaJurosAnual;
    }

    public double calcularPagMensal(){
        return (this.valorImovel / (prazoFinanciamento * 12) * (1+(taxaJurosAnual/12)));
    }

    public double calcularTotalPag() {
        return calcularPagMensal() + this.prazoFinanciamento * 12;
    }

    public void mostrarDados(){
        System.out.println("Valor do financiamento: " + calcularTotalPag());
        System.out.println("Valor do imovel: " + getValorImovel());
    }

}
