package modelo;

import util.AcrescimoMaiorQueJurosException;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Casa extends Financiamento {
    private final int tamanhoArea;
    private final int tamanhoTerreno;
    private final double taxaJurosMensal;
    private final double valorAcrescimo;

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

    public double getTaxaJurosMensal() {
        return this.taxaJurosMensal;
    }

    public double getValorAcrescimo() {
        return this.valorAcrescimo;
    }

    public double jurosMensalDecimal() {
        double pagMensal = super.calcularPagMensal();
        double jurosMensal = this.taxaJurosMensal;

        return pagMensal * jurosMensal / 100;
    }


    @Override
    public double calcularPagMensal() {
        double valorJuros = this.jurosMensalDecimal();
        double valorAcrescimo = getValorAcrescimo();
        try {
            validarJuros(valorJuros, valorAcrescimo);
        } catch (AcrescimoMaiorQueJurosException e) {
            valorAcrescimo = valorJuros;
        }
        return super.calcularPagMensal() + valorAcrescimo;
    }

    public double calcularTotalPag() {
        return calcularPagMensal() * this.prazoFinanciamento * 12;
    }


    private void validarJuros(double valorJuros, double valorAcrescimo) throws AcrescimoMaiorQueJurosException {
        if (valorAcrescimo > valorJuros) {
            throw new AcrescimoMaiorQueJurosException("O valor do acréscimo não pode ser maior do que o valor do juros mensal.");
        }
    }

    public void mostrarDados() {
        System.out.println("Valor do financiamento da casa: " + calcularTotalPag());
        System.out.println("Valor da casa: " + getValorImovel());
    }


    public static void main(String[] args) {
        Casa casa = new Casa(300000, 30, 12, 10, 10);
        System.out.printf("R$ %.2f\n", casa.calcularPagMensal());
        System.out.printf("R$ %.2f\n", casa.calcularTotalPag());
        System.out.printf("R$ %.2f\n", casa.jurosMensalDecimal());
    }
}
