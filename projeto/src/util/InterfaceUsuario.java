package util;
import java.util.Scanner;

// verificamos se o que o usuario digitou é valido
// enquanto (while) o usuario digitar um numero negativo ficaremos (do) perguntando o valor do imovel novamente em loop
// o loop acaba quando o usuario digitar um numero valido (ou seja, um numero positivo)
public class InterfaceUsuario {
        public static double pedirValorImovel(){
            Scanner scanner = new Scanner(System.in);
            double valor;
            do {
                System.out.println("Qual o valor do imovel?");
                valor = scanner.nextDouble();
                if (valor < 0 ) {
                    System.out.println("Valor invalido! O valor deve ser um numero positivo.");
                }
            } while (valor < 0 );
            return valor;
        }

//vamos verificar novamente se o numero digitado pelo usuario é valido
// criamos a variavel chamada 'prazo' que armazenará o valor digitado pelo usuario
// criamos uma variavel chamada isValid que sera usada para controlar a validade do valor digitado
// perguntamos o prazo do financiamento para o usuario
    // a variavel isValid é definida como 'true'
// se o usuario digitar um numero negativo:
    //mostramos uma mensagem que o numero é invalido
    //isValid é definido como 'false'
// se o usuario digitar um numero maior que 75
    // mostramos uma mensagem que o numero é invalido
    //isValid é definido como 'false'
// o loop do-while continuará se repetindo enquanto 'isValid' for 'false'
// quando um valor valido foi inserido, ele é retornado pelo método

public static int pedirPrazoFinanciamento() {
    Scanner scanner = new Scanner(System.in);
    int prazo;
    boolean isValid;
    do {
        System.out.println("Qual o prazo de financiamento do imovel?");
        prazo = scanner.nextInt();
        isValid = true;

        if (prazo < 0) {
            System.out.println("Prazo invalido! O numero não pode ser negativo pois deve ser um ano.");
            isValid = false;
        } else if (prazo > 75) {
            System.out.println("Prazo invalido! O maximo de anos que se pode financiar um imovel é de 75 anos");
            isValid = false;
        }
    } while (!isValid); // enquanto isValid for falso, o loop continuará
        return prazo;
}


public static double pedirTaxaJuros(){
    Scanner scanner = new Scanner(System.in);
    double taxa;
    boolean isValid;
    do {
        System.out.println("Qual a taxa de juros anual do financiamento?");
        taxa = scanner.nextDouble();
        isValid = true;

        if (taxa > 12) {
            System.out.println("Taxa de juros anual invalida! O limite maximo é de 12% ao ano. ");
            isValid = false;
        } else if (taxa < 0) {
            System.out.println("Valor de taxa invalida! A taxa deve ser um numero positivo.");
            isValid = false;
        }
    } while (!isValid);
    return taxa;
}

}


