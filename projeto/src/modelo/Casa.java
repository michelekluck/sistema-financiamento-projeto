package modelo;

import util.ValorNegativoException;

public class Casa extends Financiamento {
    private int tamanhoArea;
    private int tamanhoTerreno;
    private double taxaJurosMensal;
    private int valorAcrescimo;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int tamanhoArea, int tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoArea = tamanhoArea;
        this.tamanhoTerreno = tamanhoTerreno;
        this.taxaJurosMensal = taxaJurosAnual / 12;
        this.valorAcrescimo = 80;
    }

    public int getTamanhoArea() {
        return this.tamanhoArea;
    }

    public int getTamanhoTerreno() {
        return this.tamanhoTerreno;
    }

    public double CalcularJurosMensal() {
        return (this.valorImovel / (prazoFinanciamento * 12)) * this.taxaJurosMensal;
    }

    @Override
    public double calcularPagMensal() {
        return (this.valorImovel / (prazoFinanciamento * 12)) * (1 + this.taxaJurosMensal) + valorAcrescimo;
    }

    public double calcularTotalPag() {
        return this.calcularPagMensal() * this.prazoFinanciamento * 12;
    }


    public void mostrarDados() {
        System.out.println("Valor do financiamento da casa: " + calcularTotalPag());
        System.out.println("Valor da casa: " + getValorImovel());
    }

    public static void main(String[] args) {
        Casa casa = new Casa(12000, 10, 12, 10, 10);
        System.out.println(casa.CalcularJurosMensal());
        System.out.println(casa.calcularPagMensal());
        System.out.println(casa.calcularTotalPag());
    }
}
