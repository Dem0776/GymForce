package com.gymforce.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marca{
	private IntegerProperty clv_marca;
	private StringProperty desc_marca;
	private StringProperty status_marca;

	public Marca(int clv_marca, String desc_marca, String status_marca) { 
		this.clv_marca = new SimpleIntegerProperty(clv_marca);
		this.desc_marca = new SimpleStringProperty(desc_marca);
		this.status_marca = new SimpleStringProperty(status_marca);
	}
	public Marca(String desc_marca) {
		this.desc_marca = new SimpleStringProperty(desc_marca);
			
	}

	//Metodos atributo: clv_marca
	public int getClv_marca() {
		return clv_marca.get();
	}
	public void setClv_marca(int clv_marca) {
		this.clv_marca = new SimpleIntegerProperty(clv_marca);
	}
	public IntegerProperty Clv_marcaProperty() {
		return clv_marca;
	}
	//Metodos atributo: desc_marca
	public String getDesc_marca() {
		return desc_marca.get();
	}
	public void setDesc_marca(String desc_marca) {
		this.desc_marca = new SimpleStringProperty(desc_marca);
	}
	public StringProperty Desc_marcaProperty() {
		return desc_marca;
	}
	//Metodos atributo: status_marca
	public String getStatus_marca() {
		return status_marca.get();
	}
	public void setStatus_marca(String status_marca) {
		this.status_marca = new SimpleStringProperty(status_marca);
	}
	public StringProperty Status_marcaProperty() {
		return status_marca;
	}
	
	public String toString() {
		return String.valueOf(desc_marca.get());
		
	}
	
}