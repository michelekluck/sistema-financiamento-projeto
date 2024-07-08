package modelo;

import util.AcrescimoMaiorQueJurosException;

public class Casa extends Financiamento {
    // atributos especificos de casa
    private final int tamanhoArea;
    private final int tamanhoTerreno;
    private final double taxaJurosMensal;
    private final double valorAcrescimo;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int tamanhoArea, int tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual); // chama o construtor da classe financiamento
        this.tamanhoArea = tamanhoArea; // this -> atribuito = tamanhoArea -> parametro
        this.tamanhoTerreno = tamanhoTerreno; // this -> atribuito = tamanhoTerreno -> parametro
        this.taxaJurosMensal = taxaJurosAnual / 12; // taxa de juros mensal armazena o resultado de taxa de juros anual dividivo por 12
        this.valorAcrescimo = 80; // valor do acrescimo sempre começará sendo 80
    }

    // getters
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

    // método que calcula o valor do juros mensal de forma decimal e retorna esse valor
    public double jurosMensalDecimal() {
        double pagMensal = super.calcularPagMensal(); // armazena o retorno do método calcularpagmensal dentro da variavel pagmensal
        double jurosMensal = this.taxaJurosMensal; // armazena a taxa de juros mensal dentro da variavel jurosmensal

        return pagMensal * jurosMensal / 100; // calculamos o valor de pag mensal vezes o juros mensal dividido por 100
        // esse método retorna o resultado do calculo
    }

    // sobrescrevemos o método calcularpagmensal da classe financiamento
    @Override
    public double calcularPagMensal() {
        double valorJuros = this.jurosMensalDecimal(); // armazenamos o retorno de juros mensal decimal (feito acima) dentro da variavel valor juros
        double valorAcrescimo = getValorAcrescimo(); // armazenamos o valor do crescimo dentro da variavel valorAcrescimo
        try { // bloco try tenta executar p método validarJuros (feito abaixo)
            validarJuros(valorJuros, valorAcrescimo); // damos como parametros para esse método as variaveis criadas acima
        } catch (
                AcrescimoMaiorQueJurosException e) { // se a exceção "AcrescimoMaiorQueJurosException" (instanciada no método validarJuros) for lançada:
            valorAcrescimo = valorJuros; // igualamos o valor do acrescimo ao valor do juros. Agora, o acrescimo tem o mesmo valor do juros.
        }
        return super.calcularPagMensal() + valorAcrescimo; // esse método retorna o pagamento mensal + valor do acrescimo, que será o pagamento mensal do financiamento
    }

    // sobrescrevemos o método calculartotalpag da classe financiamento
    @Override
    public double calcularTotalPag() {
        return calcularPagMensal() * this.prazoFinanciamento * 12; // esse método retorna o pagamento mensal (calculado no método acima) vezes o prazo do financiamento vezes 12, que será o pagamento total do financiamento
    }

    // método que faz validação sobre o valor do juros e do acrescimo
    private void validarJuros(double valorJuros, double valorAcrescimo) throws AcrescimoMaiorQueJurosException {
        // esse método aceita dois parametros -> valorJuros e ValorAcrescimo
        // throws -> método pode lançar uma exceção do tipo "AcrescimoMaioQueJurosExceptio"
        if (valorAcrescimo > valorJuros) { // se o valor do acrescimo (que até então era 80, pois isso foi decretado no construtor) for maior do que o valor do juros:
            throw new AcrescimoMaiorQueJurosException("O valor do acréscimo não pode ser maior do que o valor do juros mensal."); // a exceção é lançada com a mensagem de erro.
        }
    }

    @Override
    public String toString() { // cria uma instancia de 'StringBuilder' ->  é usada para criar e manipular sequencias de caracteres
        StringBuilder sb = new StringBuilder();
        sb.append("R$ ").append(this.getValorImovel());
        sb.append(", Prazo: ").append(this.getPrazoFinanciamento());
        sb.append(" anos, Taxa anual: ").append(this.getTaxaJurosAnual()).append("%");
        sb.append(", Área: ").append(this.getTamanhoArea()).append("m²");
        sb.append(", Terreno: ").append(this.getTamanhoTerreno()).append("m²");
        return sb.toString(); // converte o conteudo do 'StringBuilder' (sb) para string e a retorna
    }
}
