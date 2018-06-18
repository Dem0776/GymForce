package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlanEntrenamiento extends RecursiveTreeObject<PlanEntrenamiento>{
	private IntegerProperty clv_pe;
	private StringProperty desc_pe;
	private StringProperty duracion_pe;
	private StringProperty frecuencia_pe;
	private StringProperty dificultad_pe;
	private StringProperty status_pe;
	private Categoria clv_categoria;

	public PlanEntrenamiento(int clv_pe, String desc_pe, String duracion_pe, String frecuencia_pe, String dificultad_pe,
			String status_pe, Categoria clv_categoria) {
		this.clv_pe = new SimpleIntegerProperty(clv_pe);
		this.desc_pe = new SimpleStringProperty(desc_pe);
		this.duracion_pe = new SimpleStringProperty(duracion_pe);
		this.frecuencia_pe = new SimpleStringProperty(frecuencia_pe);
		this.dificultad_pe = new SimpleStringProperty(dificultad_pe);
		this.status_pe = new SimpleStringProperty(status_pe);
		this.clv_categoria = clv_categoria;
	}

	// Metodos atributo: clv_pe
	public int getClv_pe() {
		return clv_pe.get();
	}

	public void setClv_pe(int clv_pe) {
		this.clv_pe = new SimpleIntegerProperty(clv_pe);
	}

	public IntegerProperty Clv_peProperty() {
		return clv_pe;
	}

	// Metodos atributo: desc_pe
	public String getDesc_pe() {
		return desc_pe.get();
	}

	public void setDesc_pe(String desc_pe) {
		this.desc_pe = new SimpleStringProperty(desc_pe);
	}

	public StringProperty Desc_peProperty() {
		return desc_pe;
	}

	// Metodos atributo: duracion_pe
	public String getDuracion_pe() {
		return duracion_pe.get();
	}

	public void setDuracion_pe(String duracion_pe) {
		this.duracion_pe = new SimpleStringProperty(duracion_pe);
	}

	public StringProperty Duracion_peProperty() {
		return duracion_pe;
	}

	// Metodos atributo: frecuencia_pe
	public String getFrecuencia_pe() {
		return frecuencia_pe.get();
	}

	public void setFrecuencia_pe(String frecuencia_pe) {
		this.frecuencia_pe = new SimpleStringProperty(frecuencia_pe);
	}

	public StringProperty Frecuencia_peProperty() {
		return frecuencia_pe;
	}

	// Metodos atributo: dificultad_pe
	public String getDificultad_pe() {
		return dificultad_pe.get();
	}

	public void setDificultad_pe(String dificultad_pe) {
		this.dificultad_pe = new SimpleStringProperty(dificultad_pe);
	}

	public StringProperty Dificultad_peProperty() {
		return dificultad_pe;
	}

	// Metodos atributo: status_pe
	public String getStatus_pe() {
		return status_pe.get();
	}

	public void setStatus_pe(String status_pe) {
		this.status_pe = new SimpleStringProperty(status_pe);
	}

	public StringProperty Status_peProperty() {
		return status_pe;
	}

	// Metodos atributo: clv_categoria
	public Categoria getClv_categoria() {
		return clv_categoria;
	}

	public void setClv_categoria(Categoria clv_categoria) {
		this.clv_categoria = clv_categoria;
	}
	
	public int guardarPlanE(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT clase VALUES (DEFAULT,?,?,?,?,DEFAULT,?)");
			consulta.setString(1, desc_pe.get());
			consulta.setString(2, duracion_pe.get());
			consulta.setString(3, frecuencia_pe.get());
			consulta.setString(4, dificultad_pe.get());
			consulta.setInt(5, clv_categoria.getClv_categoria());			
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	public static int obtenerUltimoPlan(Connection conect) {
		int clv = 0;
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT " + "plan_entrenamiento.clv_pe " + "FROM " + "plan_entrenamiento ORDER BY plan_entrenamiento.clv_pe DESC LIMIT 1");
			while (rs.next()) {
				clv = rs.getInt("clv_pe");
			}
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
		}
		return clv;
	}
}
