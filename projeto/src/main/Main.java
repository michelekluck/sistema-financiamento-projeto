package main;

import modelo.Financiamento;
import util.InterfaceUsuario;

public class Main {
    public static void main(String[] args){

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        double valorImovel = InterfaceUsuario.pedirValorImovel();
        double taxaJuros = InterfaceUsuario.pedirTaxaJuros();
        int prazoFinanciamento = InterfaceUsuario.pedirPrazoFinanciamento();


        Financiamento novoFinanciamento = new Financiamento(valorImovel, prazoFinanciamento, taxaJuros);
        novoFinanciamento.mostrarDados();
    }
}
