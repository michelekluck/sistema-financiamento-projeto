package modelo;

// apartamento É um financiamento
public class Apartamento extends Financiamento {
    // atributos exclusivos da classe apartamento
    private int vagasGaragem;
    private int numeroAndar;

    // construtor
    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int vagasGaragem, int numeroAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual); // chama o construtor da classe financiamento
        this.vagasGaragem = vagasGaragem; // this -> atributo = vagasGaragem -> parametro do construtor
        this.numeroAndar = numeroAndar; // this -> atributo = numeroAndar -> parametro do construtor
    }

    // getters
    public int getVagasGaragem() {
        return this.vagasGaragem;
    }

    public int getNumeroAndar() {
        return this.numeroAndar;
    }

    // override do método calcularpagmensal da classe financiamento
    @Override
    public double calcularPagMensal() {
        double taxaMensal = taxaJurosAnual / 12; // armazenamos o calculo da taxa de jurol anual dividido por 12 na variavel taxaMensal
        int meses = prazoFinanciamento * 12; // armazenamos o calculo do prazo de financiamento vezes 12 na variavel meses
        return (valorImovel * Math.pow(1 + taxaMensal, meses)) / (Math.pow(1 + taxaMensal, meses - 1)); // calculo do pagamento mensal para apartamento
        // esse métod retorna o pagamento mensal do financiamento
    }

    // override do método calculartotalpag da classe financiamento
    @Override
    public double calcularTotalPag() {
        return this.calcularPagMensal() * this.prazoFinanciamento * 12; // calculo para descobrir o pagamento total do financiamento
        // esse método retorna o total do pagamento
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
