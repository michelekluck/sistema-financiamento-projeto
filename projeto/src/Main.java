public class Main {
    public static void main(String[] args){

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        double taxaJuros = InterfaceUsuario.pedirTaxaJuros();
        int prazoFinanciamento = InterfaceUsuario.pedirPrazoFinanciamento();
        double valorImovel = InterfaceUsuario.pedirValorImovel();

        Financiamento novoFinanciamento = new Financiamento(valorImovel, prazoFinanciamento, taxaJuros);
    }
}
