package view;

import java.util.Scanner;
import model.Boleto;

public class Main {

	private static Scanner input;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		long sequencia;
		double multa;
		double juros;
		Boleto boleto;
		
		System.out.println("Informe a última sequencia numérica do boleto: ");
		sequencia = input.nextLong();
		
		System.out.println("Informe o valor da multa por atraso: ");
		multa = input.nextDouble();
		
		System.out.println("Informe o valor da porcentagem por atraso: ");
		juros = input.nextDouble();
		
		boleto = new Boleto(sequencia, multa, juros);
		
		System.out.println("Valor do boleto: R$ " + boleto.getValor());
		System.out.println("Valor da multa: R$ " + boleto.getMulta());
		System.out.println("Valor dos juros: R$ " + boleto.getJuros());
		System.out.println("Valor total: R$ " + boleto.getTotal());
		System.out.println("Data de Vencimento: " + boleto.getVencimento());
	}

}
