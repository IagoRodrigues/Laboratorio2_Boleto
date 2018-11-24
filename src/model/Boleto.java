package model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Boleto {
	@SuppressWarnings("unused")
	private long numeros;
	private LocalDate dataVencimento;
	private double valor;
	private int diasAtraso;
	private double multa;
	private double juros;
	private double vJuros;
	private double total;
	
	//Construtor para boleto com todos os dados
	public Boleto(long numeros, double multa, double juros) {
		this.numeros = numeros;
		this.multa = multa;
		this.juros = juros/100.0;
		extrairValor(numeros);
		extrairVencimento(numeros);
		extraiJuros();
	}
		
	//Extrair valor do boleto
	private void extrairValor(long nro){
		long value;
		
		value = (long) (nro % Math.pow(10, 10));
		valor = value / 100.0;
	}
	
	//Retorna valor do boleto
	public double getValor() {
		return this.valor;
	}
	
	//Extrair vencimento do boleto
	private void extrairVencimento(long nro){
		int dias;
		
		dias = (int) (nro / Math.pow(10,10));
		LocalDate dataBase = LocalDate.of(1997, Month.OCTOBER, 07);
		this.dataVencimento = dataBase.plusDays(dias);
	}
	
	//Retorna data de vencimento
	public LocalDate getDataVencimento(){
		return this.dataVencimento;
	}
	
	//Extrai o atraso em dias
	private void extraiAtraso(){
		LocalDate hoje = LocalDate.now();
		
		Period periodo = Period.between(dataVencimento, hoje);
		
		this.diasAtraso = (periodo.getDays());
	}
	
	//Retorna o atraso em dias
	public int getAtraso(){
		return this.diasAtraso;
	}
		
	//Extrai o valor dos juros
	private void extraiJuros(){
		extraiAtraso();
		vJuros = (valor*(Math.pow((1+juros), diasAtraso)));
		vJuros = vJuros - valor;
	}
	
	//Retorna o valor dos juros
	public String getJuros(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(this.vJuros);
		return moneyString;
	}
	
	//Extrai total
	public void calculaTotal(){
		this.total = vJuros + multa + valor;
	}
	
	//Retorna o total
	public String getTotal(){
		calculaTotal();
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(this.total);
		return moneyString;
	}
	
	//Traz o vencimento da maneira padrão "bonita"
	public String getVencimento(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataVencimento.format(formatter);
	}
	
	//Retorna multa
	public String getMulta(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(this.multa);
		return moneyString;
	}
}