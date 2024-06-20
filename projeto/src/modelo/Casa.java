package modelo;

public class Casa extends Financiamento{
    private int taxaExtra;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.taxaExtra = 80;
    }

    @Override
    public double calcularPagMensal(){
       return super.calcularPagMensal() + this.taxaExtra;
    }
}
