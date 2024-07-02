package modelo;

import util.ValorNegativoException;

public class Casa extends Financiamento{
    private int tamanhoArea;
    private int tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int tamanhoArea, int tamanhoTerreno){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoArea = tamanhoArea;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public int getTamanhoArea(){
        return this.tamanhoArea;
    }

    public int getTamanhoTerreno(){
        return this.tamanhoTerreno;
    }

    @Override
    public double calcularPagMensal(){
       return (this.valorImovel / (prazoFinanciamento * 12)) * (1+(taxaJurosAnual/12)) + 80;
    }

    public double calcularTotalPag(){
        return this.calcularPagMensal() * this.prazoFinanciamento * 12;
    }

    private void ValidarValorNegativo(double valor) throws ValorNegativoException{
        throw new ValorNegativoException("Valor " + valor + "é inválido, pois o valor do imovel deve ser positivo.");
    }

    public void mostrarDados(){
        System.out.println("Valor do financiamento da casa: " + calcularTotalPag());
        System.out.println("Valor da casa: " + getValorImovel());
    }
}
