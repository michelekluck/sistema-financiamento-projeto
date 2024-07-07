package main;

import modelo.Casa;
import modelo.Financiamento;
import util.InterfaceUsuario;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // criação de uma lista do tipo financimaneto chamado listaFinanciamento
        List<Financiamento> listaFinanciamento = new ArrayList<Financiamento>();

        // perguntamos apenas uma vez cada uma das perguntas
        double valorImovel = InterfaceUsuario.pedirValorImovel(); // chamamos o método e armazenamos seu retorno dentro da variavel
        double taxaJuros = InterfaceUsuario.pedirTaxaJuros();
        int prazoFinanciamento = InterfaceUsuario.pedirPrazoFinanciamento();
        int tamanhoArea = 20;
        int tamanhoTerreno = 10;
        Financiamento casa = new modelo.Casa(valorImovel, prazoFinanciamento, taxaJuros, tamanhoArea, tamanhoTerreno);
        listaFinanciamento.add(casa);
        
        Financiamento casa2 = new modelo.Casa(60000, 30, 5, 10, 20);
        listaFinanciamento.add(casa2);

        Financiamento apartamento = new modelo.Apartamento(100000, 15, 12, 2, 7);
        listaFinanciamento.add(apartamento);

        Financiamento apartamento2 = new modelo.Apartamento(340000, 15, 6, 1, 8);
        listaFinanciamento.add(apartamento2);

        Financiamento terreno = new modelo.Terreno(800000, 50, 10, "Residencial");
        listaFinanciamento.add(terreno);

        // CRIANDO ARQUIVO FINANCIAMENTO
        FileWriter escritor = null;

        String caminhoArqFinanciamentoTxt = "/tmp/financiamento.txt";
        String caminhoArqFinanciamentoDados = "/tmp/financiamento.dados";

        try {
            escritor = new FileWriter(caminhoArqFinanciamentoTxt);

            for (int i = 0; i < listaFinanciamento.size(); i++) {
                escritor.write(listaFinanciamento.get(i).toString() + "\n");
            }
            escritor.flush();
            escritor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // LENDO ARQUIVO FINANCIAMENTO
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArqFinanciamentoTxt))) {
            String linha;
            System.out.println("----------- Lista financiamento ---------------");
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // SALVANDO ARQUIVO SERIALIZADOS
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(caminhoArqFinanciamentoDados));
            for (int i = 0; i < listaFinanciamento.size(); i++) {
                outputStream.writeObject(listaFinanciamento.get(i));
            }
        } catch (IOException e) {
            System.out.println("Impossivel criar arquivo!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // LENDO OS DADOS NA MEMORIA E DESSERIALIZANDO
        ArrayList<Financiamento> financiamentosTemporario = new ArrayList<Financiamento>();

        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(caminhoArqFinanciamentoDados));
            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Financiamento) {
                    financiamentosTemporario.add((Financiamento) obj);
                } else {
                    System.out.println("AAA");
                }
            }

        } catch (EOFException e) {
            System.out.println("\nArquivo lido:\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo com financiamentos não existe.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        // LENDO LISTA FINANCIAMENTO TEMPORARIO
        System.out.println("------- Financiamentos da lista de objetos serializados ---------");
        for (int i = 0; i < financiamentosTemporario.size(); i++) {
            double valorDoImovelTemp = financiamentosTemporario.get(i).getValorImovel();
            double valorFinanciamentoTemp = financiamentosTemporario.get(i).calcularTotalPag();
            System.out.printf("Financiamento %d - Valor do imovel: R$ %.2f", i + 1, valorDoImovelTemp);
            System.out.printf(" Valor financiamento: R$ %.2f\n", valorFinanciamentoTemp);
        }
        System.out.println();


        System.out.println("------------- Soma de financiamentos ---------------");
        double valorTotalImoveis = 0; // criamos uma variavel valorTotalImoveis que começa com o valor 0
        double valorTotalFinanciamentos = 0; // criamos uma variavel valorTotalFinanciamentos que começa com o valor 0
        for (int i = 0; i < listaFinanciamento.size(); i++) { // começamos um loop for que vai rodar enquanto i for menor que 4
            double valorDoImovel = listaFinanciamento.get(i).getValorImovel(); // criamos uma variavel valorImovel e nela armazenamos o retorno do valorImovel do indice i da lista de financiamento
            double valorFinanciamento = listaFinanciamento.get(i).calcularTotalPag(); // criaos uma variavel totalFinanciamento e nela armazenamos o retorno do calculo do pagamento total do financiamento do indice i da lista de financiamento
            System.out.printf("Financiamento %d - Valor do imovel: R$ %.2f", i + 1, valorDoImovel); // printamos na tela o valor de cada imovel da lista, chamando a variavel declarada acima - valorImovel
            System.out.printf(" Valor financiamento: R$ %.2f\n", valorFinanciamento); // printamos na tela o valor total de financiamento de cada imovel da lista, chamando a variavel declarada acima - totalFinanciamento
            valorTotalImoveis += valorDoImovel; // somamos a variavel valorTotalImoveis - que começou om 0 - com o valorImovel - o imovel atual em que estamos
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


