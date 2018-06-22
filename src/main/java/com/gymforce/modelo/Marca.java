package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Marca{
	
	private IntegerProperty clv_marca;
	private StringProperty desc_marca;
	private StringProperty status_marca;
	public Marca() {
	
			
	}
	
	public Marca(int clv_marca, String desc_marca, String status_marca) { 
		this.clv_marca = new SimpleIntegerProperty(clv_marca);
		this.desc_marca = new SimpleStringProperty(desc_marca);
		this.status_marca = new SimpleStringProperty(status_marca);
	}
	public Marca(int clv_marca, String desc_marca) { 
		this.clv_marca = new SimpleIntegerProperty(clv_marca);
		this.desc_marca = new SimpleStringProperty(desc_marca);
		
	}
	
	public Marca(String desc_marca) {
		this.desc_marca = new SimpleStringProperty(desc_marca);
			
	}
	public Marca(int clv_marca) {
		this.clv_marca = new SimpleIntegerProperty(clv_marca);
			
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
	
	public int guardarMarca(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT marca VALUES (DEFAULT,?,DEFAULT)");
			consulta.setString(1, desc_marca.get());
			
			
			
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	 public static void extraerDatosMarca(Connection cn, ObservableList<Marca> lstMarca) {
	        try {
	            Statement st = cn.createStatement();
	            ResultSet rs = st.executeQuery("SELECT DISTINCT desc_marca FROM marca where status_marca='1'");
	            while (rs.next()) {
	                lstMarca.add(new Marca(rs.getString("desc_marca")));
	                       
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	
	 public static void llenarComboMarca(Connection cn, ObservableList<Marca> listMar) {
	        try {
	            Statement st = cn.createStatement();
	            ResultSet rs = st.executeQuery("SELECT "+"DISTINCT "+ "desc_marca "+ "FROM "+ "marca");
	            while (rs.next()) {
	            	listMar.add(
	                        new Marca(rs.getString("desc_marca")
	                                
	                        ));

	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(TipoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }
	 public int eliminaMarca(Connection cn,String Mar) {
			
			
			PreparedStatement consulta;
			try {
				consulta = cn.prepareStatement("UPDATE marca SET status_marca='0' where desc_marca='"+Mar+"' and status_marca='1'");
				

				return consulta.executeUpdate();			

					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return 0;
			}
			
			}
	
	
}