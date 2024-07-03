package util;

import java.util.Scanner;


public class InterfaceUsuario {
    public static double pedirValorImovel() {
        Scanner scanner = new Scanner(System.in);
        double valor = 0; // variavel do tipo double inicializada com 0
        boolean valid = false; // valid é uma variavel booleana inicializada com ' false', usada para controlar o loop

        while (!valid) { // esse loop while continua executando enquanto 'valid' for 'false'. !valid é 'true'
            try {
                System.out.println("Qual o valor do imóvel?"); // pedimos ao usuario o valor do imovel
                valor = scanner.nextDouble();
                if (valor <= 0) {
                    throw new ValorNegativoException("O valor que você digitou é negativo ou zero. Digite um valor positivo.");
                }
                valid = true;
            } catch (ValorNegativoException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Caractere inválido. Você deve digitar um número positivo.");
                scanner.next();
            }
        }
        return valor;
    }

    public static int pedirPrazoFinanciamento() {
        Scanner scanner = new Scanner(System.in);
        int prazo = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Qual o prazo de financiamento do imovel?");
                prazo = scanner.nextInt();
                if (prazo <= 0) {
                    throw new ValorNegativoException("O valor que você digitou é negativo ou zero. Digite um valor positivo.");
                }
                if (prazo > 75) {
                    throw new LimiteExcedidoException("O limite de prazo de financiamento é 75 anos.");
                }
                valid = true;
            } catch (ValorNegativoException | LimiteExcedidoException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Caractere inválido. Você deve digitar um valor de prazo até 75 anos.");
                scanner.next();
            }
        }
        return prazo;
    }


    public static double pedirTaxaJuros() {
        Scanner scanner = new Scanner(System.in);
        double taxa = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Qual a taxa de juros anual do financiamento?");
                taxa = scanner.nextDouble();
                if (taxa > 12) {
                    throw new LimiteExcedidoException("O limite de prazo de financiamento é 12% ao ano.");
                }
                if (taxa < 0) {
                    throw new ValorNegativoException("O valor que você digitou é negativo ou zero. Digite um numero positivo.");
                }
                valid = true;
            } catch (LimiteExcedidoException | ValorNegativoException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Caractere inválido. Você deve digitar uma taxa de juros de até 12%");
                scanner.next();
            }
        }
        return taxa;
    }
}


