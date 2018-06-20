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
import javafx.collections.ObservableList;

public class Rutina extends RecursiveTreeObject<Rutina> {
	private IntegerProperty clv_rutina;
	private IntegerProperty serie_rutina;
	private IntegerProperty repeticion_rutina;
	private StringProperty duracion_rutina;
	private IntegerProperty peso_rutina;
	private Ejercicio clv_ejercicio;

	public Rutina(int serie_rutina, int repeticion_rutina, String duracion_rutina, int peso_rutina,
			Ejercicio clv_ejercicio) {
		this.serie_rutina = new SimpleIntegerProperty(serie_rutina);
		this.repeticion_rutina = new SimpleIntegerProperty(repeticion_rutina);
		this.duracion_rutina = new SimpleStringProperty(duracion_rutina);
		this.peso_rutina = new SimpleIntegerProperty(peso_rutina);
		this.clv_ejercicio = clv_ejercicio;
	}

	public Rutina(Ejercicio ejercicio, int serie_rutina, int repeticion_rutina,
			String duracion_rutina, int peso_rutina) {
		this.clv_ejercicio = ejercicio;		
		this.serie_rutina = new SimpleIntegerProperty(serie_rutina);
		this.repeticion_rutina = new SimpleIntegerProperty(repeticion_rutina);
		this.duracion_rutina = new SimpleStringProperty(duracion_rutina);
		this.peso_rutina = new SimpleIntegerProperty(peso_rutina);		
	}

	// Metodos atributo: clv_rutina
	public int getClv_rutina() {
		return clv_rutina.get();
	}

	public void setClv_rutina(int clv_rutina) {
		this.clv_rutina = new SimpleIntegerProperty(clv_rutina);
	}

	public IntegerProperty Clv_rutinaProperty() {
		return clv_rutina;
	}

	// Metodos atributo: serie_rutina
	public int getSerie_rutina() {
		return serie_rutina.get();
	}

	public void setSerie_rutina(int serie_rutina) {
		this.serie_rutina = new SimpleIntegerProperty(serie_rutina);
	}

	public IntegerProperty Serie_rutinaProperty() {
		return serie_rutina;
	}

	// Metodos atributo: repeticion_rutina
	public int getRepeticion_rutina() {
		return repeticion_rutina.get();
	}

	public void setRepeticion_rutina(int repeticion_rutina) {
		this.repeticion_rutina = new SimpleIntegerProperty(repeticion_rutina);
	}

	public IntegerProperty Repeticion_rutinaProperty() {
		return repeticion_rutina;
	}

	// Metodos atributo: duracion_rutina
	public String getDuracion_rutina() {
		return duracion_rutina.get();
	}

	public void setDuracion_rutina(String duracion_rutina) {
		this.duracion_rutina = new SimpleStringProperty(duracion_rutina);
	}

	public StringProperty Duracion_rutinaProperty() {
		return duracion_rutina;
	}

	// Metodos atributo: peso_rutina
	public int getPeso_rutina() {
		return peso_rutina.get();
	}

	public void setPeso_rutina(int peso_rutina) {
		this.peso_rutina = new SimpleIntegerProperty(peso_rutina);
	}

	public IntegerProperty Peso_rutinaProperty() {
		return peso_rutina;
	}

	// Metodos atributo: clv_ejercicio
	public Ejercicio getClv_ejercicio() {
		return clv_ejercicio;
	}

	public void setClv_ejercicio(Ejercicio clv_ejercicio) {
		this.clv_ejercicio = clv_ejercicio;
	}
	
	@Override
	public String toString() {
		return String.valueOf(serie_rutina.get());
	}

	public static void llenarTableRutina(Connection conect, ObservableList<Rutina> listRutina) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT " + "ejercicio.desc_ejercicio, "
					+ "rutina.serie_rutina, " + "rutina.repeticion_rutina, "
					+ "rutina.duracion_rutina, " + "rutina.peso_rutina " + "FROM " + "rutina "					
					+ "JOIN ejercicio ON rutina.clv_ejercicio = ejercicio.clv_ejercicio");

			while (rs.next()) {
				listRutina.add(new Rutina(new Ejercicio(rs.getString("desc_ejercicio")), 
						rs.getInt("serie_rutina"), 
						rs.getInt("repeticion_rutina"), 
						rs.getString("duracion_rutina"), 
						rs.getInt("peso_rutina")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Rutina.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int guardarRutina(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT rutina VALUES (DEFAULT,?,?,?,?,?)");
			consulta.setInt(1, serie_rutina.get());
			consulta.setInt(2, repeticion_rutina.get());
			consulta.setString(3, duracion_rutina.get());
			consulta.setInt(4, peso_rutina.get());
			consulta.setInt(5, clv_ejercicio.getClv_ejercicio());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Rutina.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	public int obtenerRutina(Connection cn) {
		return 0;
	}
}
