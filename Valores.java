import java.util.*;

public class Valores {
	//--------------Variáveis--------------
	private double capital = 0.0;
	private double taxa = 0.0;
	private double tempo = 0.0;
	private double montante = 0.0;
	private double juros = 0.0;
	//-------------------------------------


	//--------------Get's--------------
	public double getCapital() {
		return capital;
	}
	
	public double getTaxa() {
		return taxa;
	}
	
	public double getTempo() {
		return tempo;
	}
	
	public double getMontante(){
		return montante;
	}

	public double getJuros() {
		return juros;
	}
	//---------------------------------

	
	//--------------Set's--------------
	public void setCapital(double novoCapital) {
		this.capital = novoCapital;
	}
	
	public void setTaxa(double novaTaxa) {
		this.taxa = novaTaxa;
	}
	
	public void setTempo(double novoTempo) {
		this.tempo = novoTempo;
	}

	public void setMontante(double novoMontante){
		this.montante = novoMontante;
	}

	public void setJuros(double novoJuros) {
		this.juros = novoJuros;
	}
	//---------------------------------
	
	
	//---------------Funções---------------
	public double calcularMontante(){
		double novoMontante = this.capital*Math.pow((1+this.taxa/100.0), this.tempo);
		return novoMontante;
	}
	
	public double calcularJuros(){
		this.juros = this.montante - this.capital;
		return this.juros;
	}

	public void converterParaMeses(String sTax, String sTime, String tax, String time){
		if (sTax.equals("a.m.")){
			this.taxa = Double.parseDouble(tax);
		} else if (sTax.equals("a.b.")){
			this.taxa = (Math.pow(Double.parseDouble(tax)/100+1, 1.0/2)-1)*100;
		} else if (sTax.equals("a.t.")){
			this.taxa = (Math.pow(Double.parseDouble(tax)/100+1, 1.0/3)-1)*100;
		} else if (sTax.equals("a.s.")){
			this.taxa = (Math.pow(Double.parseDouble(tax)/100+1, 1.0/6)-1)*100;
		} else if (sTax.equals("a.a.")){
			setTaxa((Math.pow(Double.parseDouble(tax)/100+1, 1.0/12)-1)*100);
		}

		if (sTime.equals("mês")){
			this.tempo = Double.parseDouble(time);
		} else if (sTime.equals("bimestre")){
			this.tempo = Double.parseDouble(time)*2;
		} else if (sTime.equals("trimestre")){
			this.tempo = Double.parseDouble(time)*3;
		} else if (sTime.equals("semestre")){
			this.tempo = Double.parseDouble(time)*6;
		} else if (sTime.equals("ano")){
			setTempo(Double.parseDouble(time)*12);
		}
	}

	public ArrayList<Double> detalhar(String sTime){
		ArrayList<Double> lista = new ArrayList<Double>();
		double valor = 0.0;
		double tax = 0.0;
		double t = 0.0;
		if (sTime.equals("mês")){
			tax = this.taxa/100;
			t = this.tempo;
		} else if (sTime.equals("bimestre")){
			tax = Math.pow(1+taxa/100, 2)-1;
			t = this.tempo/2;
		} else if (sTime.equals("trimestre")){
			tax = Math.pow(1+taxa/100, 3)-1;
			t = this.tempo/3;
		} else if (sTime.equals("semestre")){
			tax = Math.pow(1+taxa/100, 6)-1;
			t = this.tempo/6;
		} else if (sTime.equals("ano")){
			tax = Math.pow(1+taxa/100, 12)-1;
			t = this.tempo/12;
		}
		for (int i = 1; i <= t; i++){
			valor = this.capital*Math.pow((1+tax), i);
			lista.add(valor);
		}
		return lista;
	}
}
