package modelo;

public class Apartamento extends Financiamento{
    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    @Override
    public double calcularPagMensal(){
        double taxaMensal = taxaJurosAnual / 12;
        int meses = prazoFinanciamento * 12;
        return (valorImovel * Math.pow(1+taxaMensal, meses)) / (Math.pow(1+taxaMensal, meses -1));
    }
}
