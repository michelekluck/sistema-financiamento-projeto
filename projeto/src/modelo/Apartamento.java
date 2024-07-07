package modelo;

public class Apartamento extends Financiamento {
    private int vagasGaragem;
    private int numeroAndar;

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int vagasGaragem, int numeroAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.vagasGaragem = vagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    public int getVagasGaragem() {
        return this.vagasGaragem;
    }

    public int getNumeroAndar() {
        return this.numeroAndar;
    }

    @Override
    public double calcularPagMensal() {
        double taxaMensal = taxaJurosAnual / 12;
        int meses = prazoFinanciamento * 12;
        return (valorImovel * Math.pow(1 + taxaMensal, meses)) / (Math.pow(1 + taxaMensal, meses - 1));
    }

    public double calcularTotalPag() {
        return this.calcularPagMensal() * this.prazoFinanciamento * 12;
    }

    public void mostrarDados() {
        System.out.println("Valor do financiamento do apartamento: " + calcularTotalPag());
        System.out.println("Valor do apartamento: " + getValorImovel());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("R$ ").append(this.getValorImovel());
        sb.append(", Prazo: ").append(this.getPrazoFinanciamento());
        sb.append(" anos, Taxa anual: ").append(this.getTaxaJurosAnual()).append("%");
        sb.append(", Andar: ").append(this.getNumeroAndar());
        sb.append(", Nº de vagas na garagem: ").append(this.getVagasGaragem());
        return sb.toString();
    }
}
