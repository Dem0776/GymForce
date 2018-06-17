package com.gymforce.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rutina{
	private IntegerProperty clv_rutina;
	private IntegerProperty serie_rutina;
	private IntegerProperty repeticion_rutina;
	private StringProperty duracion_rutina;
	private StringProperty peso_rutina;
	private Ejercicio clv_ejercicio;

	public Rutina(int clv_rutina, int serie_rutina, int repeticion_rutina, 
String duracion_rutina, String peso_rutina, Ejercicio clv_ejercicio) { 
		this.clv_rutina = new SimpleIntegerProperty(clv_rutina);
		this.serie_rutina = new SimpleIntegerProperty(serie_rutina);
		this.repeticion_rutina = new SimpleIntegerProperty(repeticion_rutina);
		this.duracion_rutina = new SimpleStringProperty(duracion_rutina);
		this.peso_rutina = new SimpleStringProperty(peso_rutina);
		this.clv_ejercicio = clv_ejercicio;
	}

	//Metodos atributo: clv_rutina
	public int getClv_rutina() {
		return clv_rutina.get();
	}
	public void setClv_rutina(int clv_rutina) {
		this.clv_rutina = new SimpleIntegerProperty(clv_rutina);
	}
	public IntegerProperty Clv_rutinaProperty() {
		return clv_rutina;
	}
	//Metodos atributo: serie_rutina
	public int getSerie_rutina() {
		return serie_rutina.get();
	}
	public void setSerie_rutina(int serie_rutina) {
		this.serie_rutina = new SimpleIntegerProperty(serie_rutina);
	}
	public IntegerProperty Serie_rutinaProperty() {
		return serie_rutina;
	}
	//Metodos atributo: repeticion_rutina
	public int getRepeticion_rutina() {
		return repeticion_rutina.get();
	}
	public void setRepeticion_rutina(int repeticion_rutina) {
		this.repeticion_rutina = new SimpleIntegerProperty(repeticion_rutina);
	}
	public IntegerProperty Repeticion_rutinaProperty() {
		return repeticion_rutina;
	}
	//Metodos atributo: duracion_rutina
	public String getDuracion_rutina() {
		return duracion_rutina.get();
	}
	public void setDuracion_rutina(String duracion_rutina) {
		this.duracion_rutina = new SimpleStringProperty(duracion_rutina);
	}
	public StringProperty Duracion_rutinaProperty() {
		return duracion_rutina;
	}
	//Metodos atributo: peso_rutina
	public String getPeso_rutina() {
		return peso_rutina.get();
	}
	public void setPeso_rutina(String peso_rutina) {
		this.peso_rutina = new SimpleStringProperty(peso_rutina);
	}
	public StringProperty Peso_rutinaProperty() {
		return peso_rutina;
	}
	//Metodos atributo: clv_ejercicio
	public Ejercicio getClv_ejercicio() {
		return clv_ejercicio;
	}
	public void setClv_ejercicio(Ejercicio clv_ejercicio) {
		this.clv_ejercicio = clv_ejercicio;
	}
}
