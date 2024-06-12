package main;

import modelo.Financiamento;
import util.InterfaceUsuario;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        // criação de um novo objeto da classe InterfaceUsuario
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        // criação de uma lista do tipo financimaneto chamado listaFinanciamento
        List<Financiamento> listaFinanciamento = new ArrayList<Financiamento>();

        // criamos um loop for para que seja pedido 4 valores de imoveis, 4 taxas de juros e 4 prazos de financiamento
        for(int i = 0; i < 4; i++) { // i < 4 diz que o loop vai rodar enquanto i for menor que 4
            double valorImovel = InterfaceUsuario.pedirValorImovel(); // chamamos o método e armazenamos seu retorno dentro da variavel
            double taxaJuros = InterfaceUsuario.pedirTaxaJuros();
            int prazoFinanciamento = InterfaceUsuario.pedirPrazoFinanciamento();
            listaFinanciamento.add(new modelo.Financiamento(valorImovel, prazoFinanciamento, taxaJuros));
            // adicionamos um novo objeto na lista
        }

        double valorTotalImoveis = 0; // criamos uma variavel valorTotalImoveis que começa com o valor 0
        double valorTotalFinanciamentos = 0; // criamos uma variavel valorTotalFinanciamentos que começa com o valor 0
        for(int i = 0; i < 4; i++) { // começamos um loop for que vai rodar enquanto i for menor que 4
            double valorImovel = listaFinanciamento.get(i).getValorImovel(); // criamos uma variavel valorImovel e nela armazenamos o retorno do valorImovel do indice i da lista de financiamento
            double valorFinanciamento = listaFinanciamento.get(i).calcularTotalPag(); // criaos uma variavel totalFinanciamento e nela armazenamos o retorno do calculo do pagamento total do financiamento do indice i da lista de financiamento
            System.out.printf("Financiamento %d - Valor do imovel: R$ %.2f", i+1, valorImovel); // printamos na tela o valor de cada imovel da lista, chamando a variavel declarada acima - valorImovel
            System.out.printf(" Valor financiamento: R$ %.2f\n", valorFinanciamento); // printamos na tela o valor total de financiamento de cada imovel da lista, chamando a variavel declarada acima - totalFinanciamento
            valorTotalImoveis += valorImovel; // somamos a variavel valorTotalImoveis - que começou om 0 - com o valorImovel - o imovel atual em que estamos
            valorTotalFinanciamentos += valorFinanciamento; // somamos a variavel valorFinanciamentos - que começou com 0 - com o valorFinanciamento
            // ou seja: a cada iteração do loop ele vai armazenar o valor do imovel em valorImovel e depois somar em valorTotalImoveis
            // se por exemplo o valor do imovel for 1000 reais, somamos 1000 + 0 e armazenamos esse resultado em valorTotalImoveis
            // na proxima iteração que o valor do imovel for 2000, somamos o valorTotalImoveis - que é 1000 - mais os 2000 que é o valorImovel, resultando em 3000 e armazenandno esse resultado em valorTotalImoveis
            // o mesmo acontece com o valorTotalFinanciamentos.
        }

        // fazemos esses prints fora do loop porque so queremos que eles apareçam uma vez
        System.out.printf("Total de todos os imóveis: R$ %.2f", valorTotalImoveis); // printamos na tela o valor total dos imoveis chamando a variavel valorTotalImoveis
        System.out.printf(" total de todos os financiamentos: R$ %.2f\n", valorTotalFinanciamentos); // printamos na tela o valor total dos financiamentos chamando a variavel valorTotslFinanciamentos
    }
}
