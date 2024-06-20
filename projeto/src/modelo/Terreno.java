package modelo;

public class Terreno extends Financiamento{

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    @Override
    public double calcularPagMensal(){
       return super.calcularPagMensal() * 1.02;
    }
}
