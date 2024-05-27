public class Financiamento {
    // Atributos
    double valorImovel;
    int prazoFinanciamento;
    double taxaJurosAnual;

    // Método construtor
    Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    double calcularPagMensal(){
        return (this.valorImovel / (prazoFinanciamento * 12) * (1+(taxaJurosAnual/12)));
    }

    double calcularTotalPag() {
        return calcularPagMensal() + this.prazoFinanciamento * 12;
    }
}
