package util;

import java.util.Scanner;

public class InterfaceUsuario {

    // método que pergunta ao usuario qual o valor do imovel
    public static double pedirValorImovel() {
        Scanner scanner = new Scanner(System.in); // cria uma instancia da classe "scanner" -> usada para obter a entrada do usuario
        double valor = 0; // iniciamos uma variavel do tipo double, chamada valor, com o valor 0
        boolean valid = false; // iniciamos uma variavel do tipo boleano, chamada valid, false

        while (!valid) { // enquanto NÃO FOR VALIDO o loop continuará repetindo até que 'valid' seja 'true'
            try { // o bloco dentro do try TENTA executar a leitura do valor digitado pelo usuario
                System.out.println("Qual o valor do imóvel?"); // perguntamos ao usuario o valor do imovel
                valor = scanner.nextDouble(); // lemos a resposta do usuario e armazenamos na variavel "valor"
                if (valor <= 0) { // se "valor" for menor ou igual a 0
                    throw new ValorNegativoException("O valor que você digitou é negativo ou zero. Digite um valor positivo."); // instanciamos a classe "ValorNegativoException" caso o if acima seja verdadeiro, lançamos a exceção
                }
                valid = true; // agora que 'valid' é 'true'. isso faz com que a condição while (!valid) se torna 'false', encerrando o loop
            } catch (ValorNegativoException e) { // se exceção 'ValorNegativoException' for lançada, a mensagem de erro é exibida e o loop continua, porque 'valid' ainda é 'false'
                System.out.println(e.getMessage());
            } catch (Exception e) { // se qualquer outra exceção (como um valor nao numero digitado) for lançada, uma mensagem de erro é exibida e o loop continua, porque 'valid' ainda é 'false'
                System.out.println("Caractere inválido. Você deve digitar um número positivo.");
                scanner.next();
            }
        }
        return valor; // o método retorna o valor digitado pelo usuario
    }

    // método que pergunta ao usuario o prazo do financiamento
    public static int pedirPrazoFinanciamento() {
        Scanner scanner = new Scanner(System.in);  // cria uma instancia da classe "scanner" -> usada para obter a entrada do usuario
        int prazo = 0; // iniciamos uma variavel do tipo int, chamado 'prazo' com o valor 0
        boolean valid = false; // iniciamos uma variavel do tipo boleano, chamada valid, false

        while (!valid) { // enquanto NÃO FOR VALIDO o loop continuará repetindo até que 'valid' seja 'true'
            try { // o bloco dentro do try TENTA executar a leitura do valor digitado pelo usuario
                System.out.println("Qual o prazo de financiamento do imovel?");
                prazo = scanner.nextInt();
                if (prazo <= 0) { // se 'prazo' for menor ou igual a 0
                    throw new ValorNegativoException("O valor que você digitou é negativo ou zero. Digite um valor positivo."); // instanciamos a classe "ValorNegativoException" caso o if acima seja verdadeiro, lançamos a exceção
                }
                if (prazo > 75) { // se 'prazo' for maior que 75
                    throw new LimiteExcedidoException("O limite de prazo de financiamento é 75 anos."); // instanciamos a classe "LimiteExcedidoException" caso o if acima seja verdadeiro, lançamos a exceção
                }
                valid = true;   // agora que 'valid' é 'true'. isso faz com que a condição while (!valid) se torna 'false', encerrando o loop
            } catch (ValorNegativoException | LimiteExcedidoException e) { // se exceção 'ValorNegativoException' OU "LimiteExcedidoException" for lançada, a mensagem de erro é exibida e o loop continua, porque 'valid' ainda é 'false'
                System.out.println(e.getMessage());
                // as exceções personalizadas são lançadas quando a entrada do usuario é um numero inteiro valido, mas fora do intervalo permitido
                // nessas exceções personalizadas nao precisa de 'scanner.next()' porque a entrada ja foi lida corretamente como um inteiro
            } catch (Exception e) { // se qualquer outra exceção (como um valor nao numero digitado) for lançada, uma mensagem de erro é exibida e o loop continua, porque 'valid' ainda é 'false'
                System.out.println("Caractere inválido. Você deve digitar um valor de prazo até 75 anos.");
                scanner.next(); // consome a entrada invalida e permite que o lop continue a pedir uma entrada valida
                // exceções genericas (expcetion) capturam erros de formatação (como uma entrada nao numerica) e requerem 'scanner.next()' para ler e descartar a entrada invalida
            }
        }
        return prazo; // o método retorna o prazo digitado pelo usuario
    }


    // método que pede ao usuario a taxa de juros
    public static double pedirTaxaJuros() {
        Scanner scanner = new Scanner(System.in);
        double taxa = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Qual a taxa de juros anual do financiamento?");
                taxa = scanner.nextDouble();
                if (taxa > 12) { // se a taxa digitada for maior que 12
                    throw new LimiteExcedidoException("O limite de prazo de financiamento é 12% ao ano.");  // instanciamos a classe "LimiteExcedidoException" caso o if acima seja verdadeiro, lançamos a exceção
                }
                if (taxa < 0) { // se a taxa digitada for menor que 0
                    throw new ValorNegativoException("O valor que você digitou é negativo ou zero. Digite um numero positivo."); // instanciamos a classe "ValorNegativoException" caso o if acima seja verdadeiro, lançamos a exceção
                }
                valid = true; // agora que 'valid' é 'true'. isso faz com que a condição while (!valid) se torna 'false', encerrando o loop
            } catch (LimiteExcedidoException | ValorNegativoException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Caractere inválido. Você deve digitar uma taxa de juros de até 12%");
                scanner.next();
            }
        }
        return taxa; // o método retorna a taxa digitada pelo usuario
    }
}


