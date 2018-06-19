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

public class Alimento{
	private IntegerProperty clv_alimento;
	private StringProperty nombre_alimento;
	private StringProperty desc_alimento;

	public Alimento(int clv_alimento, String nombre_alimento, String desc_alimento) {
		this.clv_alimento = new SimpleIntegerProperty(clv_alimento);
		this.nombre_alimento = new SimpleStringProperty(nombre_alimento);
		this.desc_alimento = new SimpleStringProperty(desc_alimento);
	}

	public Alimento(String nombre_alimento, String desc_alimento) {
		this.nombre_alimento = new SimpleStringProperty(nombre_alimento);
		this.desc_alimento = new SimpleStringProperty(desc_alimento);
	}
	
	public Alimento(String nombre_alimento) {
		this.nombre_alimento = new SimpleStringProperty(nombre_alimento);
	}

	// Metodos atributo: clv_alimento
	public int getClv_alimento() {
		return clv_alimento.get();
	}

	public void setClv_alimento(int clv_alimento) {
		this.clv_alimento = new SimpleIntegerProperty(clv_alimento);
	}

	public IntegerProperty Clv_alimentoProperty() {
		return clv_alimento;
	}

	// Metodos atributo: nombre_alimento
	public String getNombre_alimento() {
		return nombre_alimento.get();
	}

	public void setNombre_alimento(String nombre_alimento) {
		this.nombre_alimento = new SimpleStringProperty(nombre_alimento);
	}

	public StringProperty Nombre_alimentoProperty() {
		return nombre_alimento;
	}

	// Metodos atributo: desc_alimento
	public String getDesc_alimento() {
		return desc_alimento.get();
	}

	public void setDesc_alimento(String desc_alimento) {
		this.desc_alimento = new SimpleStringProperty(desc_alimento);
	}

	public StringProperty Desc_alimentoProperty() {
		return desc_alimento;
	}
	
	@Override
	public String toString() {
		return nombre_alimento.get();
	}
	
	public static void llenarTableAlimento(Connection conect, ObservableList<Alimento> listAlimento) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT " + "alimento.nombre_alimento, " + "alimento.desc_alimento " + "FROM " + "alimento");

			while (rs.next()) {
				listAlimento.add(new Alimento(rs.getString("nombre_alimento"), rs.getString("desc_alimento")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Alimento.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void llenarComboAlimento(Connection cn, ObservableList<Alimento> listaAlimento) {
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM alimento ");
			while (rs.next()) {
				listaAlimento.add(new Alimento(rs.getInt("clv_alimento"), rs.getString("nombre_alimento"),
						rs.getString("desc_alimento")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Alimento.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int guardarAlimento(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT alimento VALUES (DEFAULT,?,?)");
			consulta.setString(1, nombre_alimento.get());
			consulta.setString(2, desc_alimento.get());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}

}