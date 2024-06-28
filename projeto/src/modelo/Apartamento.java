package modelo;

public class Apartamento extends Financiamento{
    protected int vagasGaragem;
    protected int numeroAndar;

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int vagasGaragem, int numeroAndar){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.vagasGaragem = vagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    public int getVagasGaragem(){
        return this.vagasGaragem;
    }

    public int getNumeroAndar(){
        return this.numeroAndar;
    }

    @Override
    public double calcularPagMensal(){
        double taxaMensal = taxaJurosAnual / 12;
        int meses = prazoFinanciamento * 12;
        return (valorImovel * Math.pow(1+taxaMensal, meses)) / (Math.pow(1+taxaMensal, meses -1));
    }

    public double calcularTotalPag(){
        return this.calcularPagMensal() * this.prazoFinanciamento * 12;
    }

    public void mostrarDados(){
        System.out.println("Valor do financiamento do apartamento: " + calcularTotalPag());
        System.out.println("Valor do apartamento: " + getValorImovel());
    }
}
