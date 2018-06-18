package com.gymforce.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DetalleDietaAlimento{
	private IntegerProperty clv_dda;
	private Alimento clv_alimento;
	private Dieta clv_dieta;

	public DetalleDietaAlimento(int clv_dda, Alimento clv_alimento, Dieta clv_dieta) { 
		this.clv_dda = new SimpleIntegerProperty(clv_dda);
		this.clv_alimento = clv_alimento;
		this.clv_dieta = clv_dieta;
	}

	//Metodos atributo: clv_dda
	public int getClv_dda() {
		return clv_dda.get();
	}
	public void setClv_dda(int clv_dda) {
		this.clv_dda = new SimpleIntegerProperty(clv_dda);
	}
	public IntegerProperty Clv_ddaProperty() {
		return clv_dda;
	}
	//Metodos atributo: clv_alimento
	public Alimento getClv_alimento() {
		return clv_alimento;
	}
	public void setClv_alimento(Alimento clv_alimento) {
		this.clv_alimento = clv_alimento;
	}
	//Metodos atributo: clv_dieta
	public Dieta getClv_dieta() {
		return clv_dieta;
	}
	public void setClv_dieta(Dieta clv_dieta) {
		this.clv_dieta = clv_dieta;
	}
}
