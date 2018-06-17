package com.gymforce.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mobiliario{
	private IntegerProperty clv_mobiliario;
	private StringProperty desc_mobiliario;
	private DoubleProperty costo_mobiliario;
	private StringProperty status_mobiliario;

	public Mobiliario(int clv_mobiliario, String desc_mobiliario, Double costo_mobiliario, 
String status_mobiliario) { 
		this.clv_mobiliario = new SimpleIntegerProperty(clv_mobiliario);
		this.desc_mobiliario = new SimpleStringProperty(desc_mobiliario);
		this.costo_mobiliario = new SimpleDoubleProperty(costo_mobiliario);
		this.status_mobiliario = new SimpleStringProperty(status_mobiliario);
	}

	//Metodos atributo: clv_mobiliario
	public int getClv_mobiliario() {
		return clv_mobiliario.get();
	}
	public void setClv_mobiliario(int clv_mobiliario) {
		this.clv_mobiliario = new SimpleIntegerProperty(clv_mobiliario);
	}
	public IntegerProperty Clv_mobiliarioProperty() {
		return clv_mobiliario;
	}
	//Metodos atributo: desc_mobiliario
	public String getDesc_mobiliario() {
		return desc_mobiliario.get();
	}
	public void setDesc_mobiliario(String desc_mobiliario) {
		this.desc_mobiliario = new SimpleStringProperty(desc_mobiliario);
	}
	public StringProperty Desc_mobiliarioProperty() {
		return desc_mobiliario;
	}
	//Metodos atributo: costo_mobiliario
	public Double getCosto_mobiliario() {
		return costo_mobiliario.get();
	}
	public void setCosto_mobiliario(Double costo_mobiliario) {
		this.costo_mobiliario = new SimpleDoubleProperty(costo_mobiliario);
	}
	public DoubleProperty Costo_mobiliarioProperty() {
		return costo_mobiliario;
	}
	//Metodos atributo: status_mobiliario
	public String getStatus_mobiliario() {
		return status_mobiliario.get();
	}
	public void setStatus_mobiliario(String status_mobiliario) {
		this.status_mobiliario = new SimpleStringProperty(status_mobiliario);
	}
	public StringProperty Status_mobiliarioProperty() {
		return status_mobiliario;
	}
}