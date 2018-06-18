package com.gymforce.modelo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dieta extends RecursiveTreeObject<Dieta> {
	private IntegerProperty clv_dieta;
	private StringProperty nombre_dieta;
	private StringProperty desc_dieta;

	public Dieta(int clv_dieta, String nombre_dieta, String desc_dieta) { 
		this.clv_dieta = new SimpleIntegerProperty(clv_dieta);
		this.nombre_dieta = new SimpleStringProperty(nombre_dieta);
		this.desc_dieta = new SimpleStringProperty(desc_dieta);
	}

	//Metodos atributo: clv_dieta
	public int getClv_dieta() {
		return clv_dieta.get();
	}
	public void setClv_dieta(int clv_dieta) {
		this.clv_dieta = new SimpleIntegerProperty(clv_dieta);
	}
	public IntegerProperty Clv_dietaProperty() {
		return clv_dieta;
	}
	//Metodos atributo: nombre_dieta
	public String getNombre_dieta() {
		return nombre_dieta.get();
	}
	public void setNombre_dieta(String nombre_dieta) {
		this.nombre_dieta = new SimpleStringProperty(nombre_dieta);
	}
	public StringProperty Nombre_dietaProperty() {
		return nombre_dieta;
	}
	//Metodos atributo: desc_dieta
	public String getDesc_dieta() {
		return desc_dieta.get();
	}
	public void setDesc_dieta(String desc_dieta) {
		this.desc_dieta = new SimpleStringProperty(desc_dieta);
	}
	public StringProperty Desc_dietaProperty() {
		return desc_dieta;
	}
}
