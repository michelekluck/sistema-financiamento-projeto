package main;

import modelo.Casa;
import modelo.Financiamento;
import util.InterfaceUsuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // método principal (que seja executado)
    public static void main(String[] args) {
        // criação de uma lista do tipo financimaneto chamado listaFinanciamento
        List<Financiamento> listaFinanciamento = new ArrayList<>();

        // perguntas feitas ao usuário
        double valorImovel = InterfaceUsuario.pedirValorImovel(); // a variavel valorImovel guarda o que o usuario digitou
        // InterfaceUsuario.pedirValorImovel() -> estamos chamando o método pedirValorImovel() da classe InterfaceUsuario
        double taxaJuros = InterfaceUsuario.pedirTaxaJuros(); // a variavel pedirTaxaJuros guarda o que o usuario digitou
        int prazoFinanciamento = InterfaceUsuario.pedirPrazoFinanciamento(); // a variavel prazoFinanciamento guarda o que o usuario digitou
        int tamanhoArea = 20; // a variavel tamanhoArea guarda o valor 20
        int tamanhoTerreno = 10; // a variavel tamanhoTerreno guarda o valor 10

        // instanciamos (criamos um novo objeto) da classe casa, e damos como parametros os valores digitamos pelo usuario e o tamanhoArea e tamanhoTerreno declaradas acima
        Financiamento casa = new modelo.Casa(valorImovel, prazoFinanciamento, taxaJuros, tamanhoArea, tamanhoTerreno);
        // adicionamos esse novo objeto na listaFinanciamento
        listaFinanciamento.add(casa);

        // nessas instancias adicionamos os valores diretos nos parametros do construtor da classe e nao perguntamos ao usuario os valores
        Financiamento casa2 = new modelo.Casa(60000, 30, 5, 10, 20);
        listaFinanciamento.add(casa2);

        Financiamento apartamento = new modelo.Apartamento(100000, 15, 12, 2, 7);
        listaFinanciamento.add(apartamento);

        Financiamento apartamento2 = new modelo.Apartamento(340000, 15, 6, 1, 8);
        listaFinanciamento.add(apartamento2);

        Financiamento terreno = new modelo.Terreno(800000, 50, 10, "Residencial");
        listaFinanciamento.add(terreno);

        // CRIANDO E ESCREVENDO OBJETOS EM UM ARQUIVO DE TEXTO
        // 'FileWriter' é usado para escrever caracteres em um arquivo
        FileWriter escritor = null; // declara um objeto 'FileWriter' chamado 'escritor' e o inicializa como 'null'

        // define o caminho e o nome do arquivo de texto onde os dados do financiamento serão escritos
        String caminhoArqFinanciamentoTxt = "/tmp/financiamento.txt"; // criamos uma variavel para armazenar esse caminho
        String caminhoArqFinanciamentoDados = "/tmp/financiamento.dados";

        try { // bloco try tenta executar o codigo dentro dele
            escritor = new FileWriter(caminhoArqFinanciamentoTxt); // inicializa  o 'FileWriter' para o caminho especificado
            // isso cria ou sobrescreve o arquivo /tpm/financiamento.txt
            for (int i = 0; i < listaFinanciamento.size(); i++) { // inicia um loop que percorre todos os elementos da listaFinanciamento
                escritor.write(listaFinanciamento.get(i).toString() + "\n"); // converte o objeto 'Financiamento' atual em uma string (usando o método toString() e escreve essa string no arquivo seguido de uma nova linha
            }
            escritor.flush(); // garante que todos os dados buffered sejam escritos no arquivo
            escritor.close(); // fecha o 'FileWriter', liberando qualquer recurso associado a ele
        } catch (Exception e) { // captura qualquer exceção que possa ocorrer durante a execução do bloco try
            System.out.println(e.getMessage()); // imprime a mensagem de erro no console
        }

        // LENDO CONTEUDO DO ARQUIVO DE TEXTO FINANCIAMENTO E IMPRIMINDO CADA LINHA NO CONSOLE
        // inidica um blogo try-with-resources, que garante que o 'BufferedReader' será fechado automaticamente ao final do bloco
        // 'BufferedReader' é usado para ler texto de um 'FileWriter'
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArqFinanciamentoTxt))) {
            String linha; // declara a variavel 'linha'
            System.out.println("----------- Lista financiamento ---------------"); // imprime um cabeçakho no console para indicar o inicio da lista de financiamentos
            while ((linha = reader.readLine()) != null) { // inicia um loop while que le cada linha do arquivo até que nao hava mais linhas (quando o 'readLine()' retornar 'null')
                // linha = reader.readLine() -> le uma linha do arquivo e a armazena na variavel 'linha'
                System.out.println(linha); // imprime cada linha lida no console
            }
        } catch (
                IOException e) { // captura qualquer exceção 'IOException' que possa ocorrer durante a leitura do arquivo
            e.printStackTrace(); // imprime o stack trace da exceção no console
        }

        //  SERIALIZANDO DA LISTA E GRAVANDO EM UM ARQUIVO
        ObjectOutputStream outputStream = null; // declara um objeto 'ObjectOutputStream' chamado 'outputStream' e o inicializa com 'null'
        // ObjectOutputStream -> usado para gravar objetos em um fluxo de saida

        try { // tenta executar o codigo
            outputStream = new ObjectOutputStream(new FileOutputStream(caminhoArqFinanciamentoDados)); // cria um objeto 'ObjectOutputStream' que grava em um 'FileOutoutStream' que grava no arquivo especificado
            for (int i = 0; i < listaFinanciamento.size(); i++) { // itera sobre todos os elemnetos da lista
                outputStream.writeObject(listaFinanciamento.get(i)); // serializa cada objeto financiamento na lista e escreve no fluxo de saida
            }
        } catch (
                IOException e) { // captura exceções de entrada/saida. se uma 'IOException' ocorrer, uma mensagem é impressa no console
            System.out.println("Impossivel criar arquivo!"); // mensagem que será exibida
            e.printStackTrace();
        } catch (Exception e) { // captura outras exceções que nao sejam 'IOException'
            e.printStackTrace();
        } finally { // bloco finally garante que o codigo dentro dele será executado independentemente de uma exceção ser lançada ou não
            try {
                if (outputStream != null) { // verifica se o 'outputstream' não é 'null' antes de tentar fechá-lo
                    outputStream.flush(); // garante que todos os dados buffered no 'ObjectOutputStream' sejam gravados no arquivo
                    outputStream.close(); // fecha o objectoutputstream
                }
            } catch (
                    IOException e) { // captura qualquer IOException que possa ocorrer durante o flush ou close e imprime o stack trace.
                e.printStackTrace();
            }
        }

        // LENDO OS DADOS NA MEMORIA, DESSERIALIZANDO E ARMAZENANDO-OS EM UMA LISTA
        ArrayList<Financiamento> financiamentosTemporario = new ArrayList<>(); // declara e inicializa uma lista temporaria para armazenas objetos financiamento desserializados

        ObjectInputStream inputStream = null; // declara um objeto objectinputstream chamado inputstream e o inicializa como null
        // ObjectInputStream -> usado para ler objetos de um fluxo de entrada

        try { // tenta executar o codigo
            inputStream = new ObjectInputStream(new FileInputStream(caminhoArqFinanciamentoDados)); // cria um objeto objectinputstream que le o arquivo armazenado na variavel caminhoArqFinanciamentoDados
            Object obj = null; // declara a variavel obj que sera usada para armazenas objetos lidos do arquivo
            while ((obj = inputStream.readObject()) != null) { // inicia um loop while que le objetos do arquivo ate que 'readObject()' retorne null
                if (obj instanceof Financiamento) { // verifica se o objeto lido é uma instancia (objeto) de financiamento
                    financiamentosTemporario.add((Financiamento) obj); // se for uma instancia, adiciona-o na lista 'financiamentoTemporario'
                } else { // se o objeto não for uma instancia de financiamento
                    System.out.println("O objeto não é uma instância de financiamento."); // imprime uma mensagem
                }
            }
        } catch (
                EOFException e) { // captura a exceção que é lançada quando o final do arquivo é alcançado, indicando que a leitura do arquivo foi concluida
            System.out.println("\nArquivo lido:\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                FileNotFoundException e) { // captura exceções lançadas quando uma classe necessaria para desserialização não pode ser encontrada
            System.out.println("Arquivo com financiamentos não existe.");
            e.printStackTrace();
        } catch (IOException e) { // captura outras exceções de entrada e saida
            e.printStackTrace();
        } finally { // bloco finally garante que o codigo dentro dele sera executado independentemente de uma exceção ser lançada ou não
            try {
                if (inputStream != null) { // verifica se o inputstream não é null antes de tentar fecha-lo
                    inputStream.close();
                }
            } catch (final
            IOException e) { // captura qualquer exceção 'IOException' que possa ocorrer durante o fechamendo do inputsream
                e.printStackTrace();
            }
        }

        // LENDO LISTA FINANCIAMENTO TEMPORARIO E IMPRIMINDO NO CONSOLE
        System.out.println("------- Financiamentos da lista de objetos serializados ---------");
        for (int i = 0; i < financiamentosTemporario.size(); i++) { // loop for que itera sobre cada financiamento da lista de financiamento temporario

            double valorDoImovelTemp = financiamentosTemporario.get(i).getValorImovel(); // pega o valor do imovel do financiamento na posição 'i' da lista
            // getValorImovel() -> retorna o valor do imovel associado ao financiamento na posição i

            double valorFinanciamentoTemp = financiamentosTemporario.get(i).calcularTotalPag(); // pega o prazo do financiamento na posição i da lista
            // calcularTotalPag() -> retorna o pagamento total do financiamento na posição i

            System.out.printf("Financiamento %d - Valor do imovel: R$ %.2f", i + 1, valorDoImovelTemp); // imprime no console o valor do imovel, chamando a variavel declarada acima
            System.out.printf(" Valor financiamento: R$ %.2f\n", valorFinanciamentoTemp); // imprime na tela o valor do financiamento, chamando a variavel declarada acima
        }

        System.out.println(); // quebra de linha


        // SOMA DOS FINANCIAMENTOS
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

        // imprimimos na tela o valor total de todos os imoveis e o valor total de todos os financiamentos passados
        // fazemos esses prints fora do loop porque so queremos que eles apareçam uma vez
        System.out.printf("Total de todos os imóveis: R$ %.2f", valorTotalImoveis); // printamos na tela o valor total dos imoveis chamando a variavel valorTotalImoveis
        System.out.printf(" total de todos os financiamentos: R$ %.2f\n", valorTotalFinanciamentos); // printamos na tela o valor total dos financiamentos chamando a variavel valorTotslFinanciamentos
    }


}


