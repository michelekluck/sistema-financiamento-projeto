package modelo;

public abstract class Financiamento {
    // Atributos
    protected double valorImovel;
    protected int prazoFinanciamento;
    protected double taxaJurosAnual;

    // Método construtor
    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public double getValorImovel(){
        return this.valorImovel;
    }

    public double getTaxaJurosAnual() {
        return this.taxaJurosAnual;
    }

    public abstract double calcularPagMensal();

    public abstract double calcularTotalPag();

    public abstract void mostrarDados();
}

//        System.out.println("Valor do financiamento: " + calcularTotalPag());
//        System.out.println("Valor do imovel: " + getValorImovel());