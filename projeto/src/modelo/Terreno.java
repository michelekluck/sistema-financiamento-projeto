package modelo;

public class Terreno extends Financiamento {
    // atributo especifico da classe terreno
    private String tipoZona;

    // construtor
    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual); // chama o construtor da classe financiamento
        this.tipoZona = tipoZona; // this -> atributo = tipoZona -> parametro do construtor
    }

    // getter
    protected String getTipoZona() {
        return this.tipoZona = tipoZona;
    }

    // sobrescrita do método calcular pag mensal da classe financiamento
    @Override
    public double calcularPagMensal() {
        return (this.valorImovel / (prazoFinanciamento * 12)) * (1 + (taxaJurosAnual / 12)) * 1.02; // calculo feito para desconbrir o pagamento mensal
    } // retorna o pagamento mensal

    // override do método abstrato da classe financiamento
    @Override
    public double calcularTotalPag() {
        return this.calcularPagMensal() * this.prazoFinanciamento * 12;
    } // retorna o pagamento total do financiamento

    public String toString() {
        StringBuilder sb = new StringBuilder(); // cria uma instancia de 'StringBuilder' ->  é usada para criar e manipular sequencias de caracteres
        sb.append("R$ ").append(this.getValorImovel());
        sb.append(", Prazo: ").append(this.getPrazoFinanciamento());
        sb.append(" anos, Taxa anual: ").append(this.getTaxaJurosAnual()).append("%");
        sb.append(", Zona: ").append(this.getTipoZona());
        return sb.toString(); // converte o conteudo do 'StringBuilder' (sb) para string e a retorna
    }
}
