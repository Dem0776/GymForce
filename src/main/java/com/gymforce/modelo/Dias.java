package com.gymforce.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dias{
	private IntegerProperty clv_dia;
	private StringProperty desc_dia;
	private StringProperty status_dia;

	public Dias(int clv_dia, String desc_dia, String status_dia) { 
		this.clv_dia = new SimpleIntegerProperty(clv_dia);
		this.desc_dia = new SimpleStringProperty(desc_dia);
		this.status_dia = new SimpleStringProperty(status_dia);
	}

	//Metodos atributo: clv_dia
	public int getClv_dia() {
		return clv_dia.get();
	}
	public void setClv_dia(int clv_dia) {
		this.clv_dia = new SimpleIntegerProperty(clv_dia);
	}
	public IntegerProperty Clv_diaProperty() {
		return clv_dia;
	}
	//Metodos atributo: desc_dia
	public String getDesc_dia() {
		return desc_dia.get();
	}
	public void setDesc_dia(String desc_dia) {
		this.desc_dia = new SimpleStringProperty(desc_dia);
	}
	public StringProperty Desc_diaProperty() {
		return desc_dia;
	}
	//Metodos atributo: status_dia
	public String getStatus_dia() {
		return status_dia.get();
	}
	public void setStatus_dia(String status_dia) {
		this.status_dia = new SimpleStringProperty(status_dia);
	}
	public StringProperty Status_diaProperty() {
		return status_dia;
	}
}