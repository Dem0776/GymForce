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

public class Ejercicio extends RecursiveTreeObject<Ejercicio> {
	private IntegerProperty clv_ejercicio;
	private StringProperty desc_ejercicio;
	private StringProperty complejidad_ejercicio;
	private Mobiliario clv_mobiliario;
	private StringProperty status_ejercicio;
	
	public Ejercicio(int clv_ejercicio, String desc_ejercicio, String complejidad_ejercicio, Mobiliario clv_mobiliario) {
		this.clv_ejercicio = new SimpleIntegerProperty(clv_ejercicio);
		this.desc_ejercicio = new SimpleStringProperty(desc_ejercicio);
		this.complejidad_ejercicio = new SimpleStringProperty(complejidad_ejercicio);
		this.clv_mobiliario = clv_mobiliario;
	}
	
	public Ejercicio(String desc_ejercicio, String complejidad_ejercicio, Mobiliario clv_mobiliario) {
		this.desc_ejercicio = new SimpleStringProperty(desc_ejercicio);
		this.complejidad_ejercicio = new SimpleStringProperty(complejidad_ejercicio);
		this.clv_mobiliario = clv_mobiliario;
	}

	public Ejercicio(String desc_ejercicio) {
		this.desc_ejercicio = new SimpleStringProperty(desc_ejercicio);
	}

	// Metodos atributo: clv_ejercicio
	public int getClv_ejercicio() {
		return clv_ejercicio.get();
	}

	public void setClv_ejercicio(int clv_ejercicio) {
		this.clv_ejercicio = new SimpleIntegerProperty(clv_ejercicio);
	}

	public IntegerProperty Clv_ejercicioProperty() {
		return clv_ejercicio;
	}

	// Metodos atributo: desc_ejercicio
	public String getDesc_ejercicio() {
		return desc_ejercicio.get();
	}

	public void setDesc_ejercicio(String desc_ejercicio) {
		this.desc_ejercicio = new SimpleStringProperty(desc_ejercicio);
	}

	public StringProperty Desc_ejercicioProperty() {
		return desc_ejercicio;
	}

	// Metodos atributo: complejidad_ejercicio
	public String getComplejidad_ejercicio() {
		return complejidad_ejercicio.get();
	}

	public void setComplejidad_ejercicio(String complejidad_ejercicio) {
		this.complejidad_ejercicio = new SimpleStringProperty(complejidad_ejercicio);
	}

	public StringProperty Complejidad_ejercicioProperty() {
		return complejidad_ejercicio;
	}

	// Metodos atributo: clv_mobiliario
	public Mobiliario getClv_mobiliario() {
		return clv_mobiliario;
	}

	public void setClv_mobiliario(Mobiliario clv_mobiliario) {
		this.clv_mobiliario = clv_mobiliario;
	}

	// Metodos atributo: status_ejercicio
	public String getStatus_ejercicio() {
		return status_ejercicio.get();
	}

	public void setStatus_ejercicio(String status_ejercicio) {
		this.status_ejercicio = new SimpleStringProperty(status_ejercicio);
	}

	public StringProperty Status_ejercicioProperty() {
		return status_ejercicio;
	}
	
	@Override
	public String toString() {
		return desc_ejercicio.get();
	}

	public static void lleanarTableEjercicio(Connection conect, ObservableList<Ejercicio> listaEjercicio) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT " + "ejercicio.clv_ejercicio, " + "ejercicio.desc_ejercicio, "
					+ "ejercicio.complejidad_ejercicio, " + "mobiliario.desc_mobiliario " + "FROM ejercicio "
					+ "JOIN mobiliario ON ejercicio.clv_mobiliario = mobiliario.clv_mobiliario "
					+ "WHERE ejercicio.status_ejercicio = 1");
			while (rs.next()) {
				listaEjercicio.add(new Ejercicio(rs.getInt("clv_ejercicio"), 
						rs.getString("desc_ejercicio"), 
						rs.getString("complejidad_ejercicio"), 
						new Mobiliario(rs.getString("desc_mobiliario"))));
				/*listaEjercicio.add(new Ejercicio(rs.getString("desc_ejercicio"), rs.getString("complejidad_ejercicio"),
						new Mobiliario(rs.getString("desc_mobiliario"))));*/
			}
		} catch (SQLException e) {
			Logger.getLogger(Ejercicio.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public static void llenarComboEjercicio(Connection cn, ObservableList<Ejercicio> lista) {
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT * FROM ejercicio " + "WHERE ejercicio.status_ejercicio = 1");
			while (rs.next()) {
				lista.add(new Ejercicio(rs.getInt("clv_ejercicio"), 
						rs.getString("desc_ejercicio"), 
						rs.getString("complejidad_ejercicio"), 
						new Mobiliario(rs.getString("clv_mobiliario"))));

			}
		} catch (SQLException ex) {
			Logger.getLogger(Mobiliario.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int guardarEjercicio(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT ejercicio VALUES (DEFAULT,?,?,?,DEFAULT)");
			consulta.setString(1, desc_ejercicio.get());
			consulta.setString(2, complejidad_ejercicio.get());
			consulta.setInt(3, clv_mobiliario.getClv_mobiliario());
			return consulta.executeUpdate();
		} catch (Exception e) {
			Logger.getLogger(Ejercicio.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
}
