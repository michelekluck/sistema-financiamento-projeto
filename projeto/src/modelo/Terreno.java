package modelo;

public class Terreno extends Financiamento{
    private String tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    protected String getTipoZona(){
        return this.tipoZona = tipoZona;
    }

    @Override
    public double calcularPagMensal(){
       return (this.valorImovel / (prazoFinanciamento * 12)) * (1+(taxaJurosAnual/12)) * 1.02;
    }

    public double calcularTotalPag(){
        return this.calcularPagMensal() * this.prazoFinanciamento * 12;
    }

    public void mostrarDados(){
        System.out.println("Valor do financiamento do terreno: " + calcularTotalPag());
        System.out.println("Valor do terreno: " + getValorImovel());
    }
}
