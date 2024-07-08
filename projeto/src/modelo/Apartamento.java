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

    // todos toString() são override
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // cria uma instancia de 'StringBuilder' ->  é usada para criar e manipular sequencias de caracteres
        sb.append("R$ ").append(this.getValorImovel()); // adiciona 'R$' e o valor do imovel ao stringbuilder
        sb.append(", Prazo: ").append(this.getPrazoFinanciamento()); // adiciona   ', prazo' e o prazo de financiamento ao stringbuilder
        sb.append(" anos, Taxa anual: ").append(this.getTaxaJurosAnual()).append("%"); // adiciona ' anos, taxa anual' e a taxa de juros anual
        sb.append(", Andar: ").append(this.getNumeroAndar()); // adiciona ', andar' e o numero do andar do apartamento
        sb.append(", Nº de vagas na garagem: ").append(this.getVagasGaragem()); // adiciona ', n de vagas na garagem' e quantas vagas de garagem o ap tem
        return sb.toString(); // converte o conteudo do 'StringBuilder' (sb) para string e a retorna
    }
}
