package com.gymforce.modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.collections.ObservableList;

public class Mobiliario extends RecursiveTreeObject<Mobiliario> {



	private IntegerProperty clv_mobiliario;
	private StringProperty desc_mobiliario;
	private DoubleProperty costo_mobiliario;
	private StringProperty status_mobiliario;


	public Mobiliario(String desc_mobiliario, Double costo_mobiliario) {

		this.desc_mobiliario = new SimpleStringProperty(desc_mobiliario);
		this.costo_mobiliario = new SimpleDoubleProperty(costo_mobiliario);

	}

	public Mobiliario(int clv_mobiliario, String desc_mobiliario, Double costo_mobiliario, String status_mobiliario) {
	 

		this.clv_mobiliario = new SimpleIntegerProperty(clv_mobiliario);
		this.desc_mobiliario = new SimpleStringProperty(desc_mobiliario);
		this.costo_mobiliario = new SimpleDoubleProperty(costo_mobiliario);
		this.status_mobiliario = new SimpleStringProperty(status_mobiliario);
	}
	
	public Mobiliario(String desc_mobiliario) {		
		this.desc_mobiliario = new SimpleStringProperty(desc_mobiliario);
	}

	// Metodos atributo: clv_mobiliario
	public int getClv_mobiliario() {
		return clv_mobiliario.get();
	}

	public void setClv_mobiliario(int clv_mobiliario) {
		this.clv_mobiliario = new SimpleIntegerProperty(clv_mobiliario);
	}

	public IntegerProperty Clv_mobiliarioProperty() {
		return clv_mobiliario;
	}

	// Metodos atributo: desc_mobiliario
	public String getDesc_mobiliario() {
		return desc_mobiliario.get();
	}

	public void setDesc_mobiliario(String desc_mobiliario) {
		this.desc_mobiliario = new SimpleStringProperty(desc_mobiliario);
	}

	public StringProperty Desc_mobiliarioProperty() {
		return desc_mobiliario;
	}

	// Metodos atributo: costo_mobiliario
	public Double getCosto_mobiliario() {
		return costo_mobiliario.get();
	}

	public void setCosto_mobiliario(Double costo_mobiliario) {
		this.costo_mobiliario = new SimpleDoubleProperty(costo_mobiliario);
	}

	public DoubleProperty Costo_mobiliarioProperty() {
		return costo_mobiliario;
	}

	// Metodos atributo: status_mobiliario
	public String getStatus_mobiliario() {
		return status_mobiliario.get();
	}

	public void setStatus_mobiliario(String status_mobiliario) {
		this.status_mobiliario = new SimpleStringProperty(status_mobiliario);
	}

	public StringProperty Status_mobiliarioProperty() {
		return status_mobiliario;
	}
	
	@Override
	public String toString() {
		return desc_mobiliario.get();
	}
	public static void llenarComboEquipamento(Connection cn, ObservableList<Mobiliario> lista) {
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT * FROM mobiliario " + "WHERE mobiliario.status_mobiliario = 1");
			while (rs.next()) {
				lista.add(new Mobiliario(rs.getInt("clv_mobiliario"), rs.getString("desc_mobiliario"),
						rs.getDouble("costo_mobiliario"), rs.getString("status_mobiliario")));

			}
		} catch (SQLException ex) {
			Logger.getLogger(Mobiliario.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void llenarTableMobiliario(Connection conexion, ObservableList<Mobiliario> listmobiliario) {
		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT" + " desc_mobiliario," + "costo_mobiliario"
				+ " FROM " + "mobiliario ");
			while (rs.next()) {
				listmobiliario.add(new Mobiliario(rs.getString("desc_mobiliario"), rs.getDouble("costo_mobiliario")));
			}

		} catch (SQLException ex) {
			// TODO: handle exception
			Logger.getLogger(Mobiliario.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	 public int guardarMobiliario(Connection cn){
	        try {
	            PreparedStatement consulta = cn.prepareStatement(
	                    "INSERT INTO mobiliario VALUES(DEFAULT,?,?,DEFAULT)"
	            );
	            
	            consulta.setString(1, desc_mobiliario.get());
	            consulta.setDouble(2,costo_mobiliario.get());
	            
	            
	            
	            return consulta.executeUpdate();
	                
	        } catch (SQLException ex) {
	            Logger.getLogger(Mobiliario.class.getName()).log(Level.SEVERE, null, ex);
	            return 0;
	        }
	    }
	
}

	


