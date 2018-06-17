package com.gymforce.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ejercicio{
	private IntegerProperty clv_ejercicio;
	private StringProperty desc_ejercicio;
	private StringProperty complejidad_ejercicio;
	private Mobiliario clv_mobiliario;
	private StringProperty status_ejercicio;

	public Ejercicio(int clv_ejercicio, String desc_ejercicio, String complejidad_ejercicio, 
Mobiliario clv_mobiliario, String status_ejercicio) { 
		this.clv_ejercicio = new SimpleIntegerProperty(clv_ejercicio);
		this.desc_ejercicio = new SimpleStringProperty(desc_ejercicio);
		this.complejidad_ejercicio = new SimpleStringProperty(complejidad_ejercicio);
		this.clv_mobiliario = clv_mobiliario;
		this.status_ejercicio = new SimpleStringProperty(status_ejercicio);
	}

	//Metodos atributo: clv_ejercicio
	public int getClv_ejercicio() {
		return clv_ejercicio.get();
	}
	public void setClv_ejercicio(int clv_ejercicio) {
		this.clv_ejercicio = new SimpleIntegerProperty(clv_ejercicio);
	}
	public IntegerProperty Clv_ejercicioProperty() {
		return clv_ejercicio;
	}
	//Metodos atributo: desc_ejercicio
	public String getDesc_ejercicio() {
		return desc_ejercicio.get();
	}
	public void setDesc_ejercicio(String desc_ejercicio) {
		this.desc_ejercicio = new SimpleStringProperty(desc_ejercicio);
	}
	public StringProperty Desc_ejercicioProperty() {
		return desc_ejercicio;
	}
	//Metodos atributo: complejidad_ejercicio
	public String getComplejidad_ejercicio() {
		return complejidad_ejercicio.get();
	}
	public void setComplejidad_ejercicio(String complejidad_ejercicio) {
		this.complejidad_ejercicio = new SimpleStringProperty(complejidad_ejercicio);
	}
	public StringProperty Complejidad_ejercicioProperty() {
		return complejidad_ejercicio;
	}
	//Metodos atributo: clv_mobiliario
	public Mobiliario getClv_mobiliario() {
		return clv_mobiliario;
	}
	public void setClv_mobiliario(Mobiliario clv_mobiliario) {
		this.clv_mobiliario = clv_mobiliario;
	}
	//Metodos atributo: status_ejercicio
	public String getStatus_ejercicio() {
		return status_ejercicio.get();
	}
	public void setStatus_ejercicio(String status_ejercicio) {
		this.status_ejercicio = new SimpleStringProperty(status_ejercicio);
	}
	public StringProperty Status_ejercicioProperty() {
		return status_ejercicio;
	}
}
