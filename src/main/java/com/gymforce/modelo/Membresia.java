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

public class Membresia extends RecursiveTreeObject<Membresia>{
	private IntegerProperty clv_membresia;
	private StringProperty desc_membresia;
	private DoubleProperty precio_membresia;
	private IntegerProperty diasValidos_membresia;
	private StringProperty status_tm;

	public Membresia(String desc_membresia, Double precio_membresia) { 
					
					this.desc_membresia = new SimpleStringProperty(desc_membresia);
					this.precio_membresia = new SimpleDoubleProperty(precio_membresia);
		
				}
	

	public Membresia(int clv_membresia, String desc_membresia, Double precio_membresia, 
int diasValidos_membresia, String status_tm) { 
		this.clv_membresia = new SimpleIntegerProperty(clv_membresia);
		this.desc_membresia = new SimpleStringProperty(desc_membresia);
		this.precio_membresia = new SimpleDoubleProperty(precio_membresia);
		this.diasValidos_membresia = new SimpleIntegerProperty(diasValidos_membresia);
		this.status_tm = new SimpleStringProperty(status_tm);
	}
	
	public Membresia(String desc_membresia, Double precio_membresia, int diasValidos_membresia) {
		this.desc_membresia = new SimpleStringProperty(desc_membresia);
		this.precio_membresia = new SimpleDoubleProperty(precio_membresia);
		this.diasValidos_membresia = new SimpleIntegerProperty(diasValidos_membresia);
	}
	
	//Metodos atributo: clv_membresia
	public int getClv_membresia() {
		return clv_membresia.get();
	}
	public void setClv_membresia(int clv_membresia) {
		this.clv_membresia = new SimpleIntegerProperty(clv_membresia);
	}
	public IntegerProperty Clv_membresiaProperty() {
		return clv_membresia;
	}
	//Metodos atributo: desc_membresia
	public String getDesc_membresia() {
		return desc_membresia.get();
	}
	public void setDesc_membresia(String desc_membresia) {
		this.desc_membresia = new SimpleStringProperty(desc_membresia);
	}
	public StringProperty Desc_membresiaProperty() {
		return desc_membresia;
	}
	//Metodos atributo: precio_membresia
	public Double getPrecio_membresia() {
		return precio_membresia.get();
	}
	public void setPrecio_membresia(Double precio_membresia) {
		this.precio_membresia = new SimpleDoubleProperty(precio_membresia);
	}
	public DoubleProperty Precio_membresiaProperty() {
		return precio_membresia;
	}
	//Metodos atributo: diasValidos_membresia
	public int getDiasValidos_membresia() {
		return diasValidos_membresia.get();
	}
	public void setDiasValidos_membresia(int diasValidos_membresia) {
		this.diasValidos_membresia = new SimpleIntegerProperty(diasValidos_membresia);
	}
	public IntegerProperty DiasValidos_membresiaProperty() {
		return diasValidos_membresia;
	}
	//Metodos atributo: status_tm
	public String getStatus_tm() {
		return status_tm.get();
	}
	public void setStatus_tm(String status_tm) {
		this.status_tm = new SimpleStringProperty(status_tm);
	}
	public StringProperty Status_tmProperty() {
		return status_tm;
	}
	public static void llenarTableMembresia(Connection conect, ObservableList<Membresia> listMembresia) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT membresia.desc_membresia, membresia.precio_membresia, membresia.diasValidos_membresia FROM membresia");

			while (rs.next()) {
				listMembresia.add(new Membresia(
						rs.getString("desc_membresia"), 
						rs.getDouble("precio_membresia"),
						rs.getInt("diasValidos_membresia")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public int guardarMembresia(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT membresia VALUES (DEFAULT,?,?,?,DEFAULT)");
			consulta.setString(1, desc_membresia.get());
			consulta.setDouble(2, precio_membresia.get());
			consulta.setInt(3, diasValidos_membresia.get());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	 public int actualizarMembresia(Connection cn) {
	        try {
	            PreparedStatement consulta = cn.prepareStatement("UPDATE membresia SET "
	           /* 1 */         + "membresia.desc_membresia = ?, "
	           /* 2 */         + "membresia.precio_membresia = ?, " 
	                           + "membresia.diasValidos_membresia = ? " 
	           /* 3 */         + "WHERE membresia.desc_membresia = ? ");
	            consulta.setString(1, desc_membresia.get());
	            consulta.setDouble(2, precio_membresia.get());
	            consulta.setInt(3, diasValidos_membresia.get());
	            consulta.setString(4, desc_membresia.get());
	            
	            return consulta.executeUpdate();
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(Membresia.class.getName()).log(Level.SEVERE, null, ex);
	            return 0;
	        }
	 }
	 public int eliminarMembresia(Connection cn) {
	        try {
	            PreparedStatement consulta = cn.prepareStatement(
	                    "DELETE FROM membresia "
	                    + "WHERE membresia.desc_membresia = ?");
	            consulta.setString(1, desc_membresia.get());
	            return consulta.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(Membresia.class.getName()).log(Level.SEVERE, null, ex);
	            return 0;
	        }
	    }
	 
	 public static void extraerDatosMembresiaTable(Connection cn, ObservableList<Membresia> lstM) {
		    try {
		        Statement st = cn.createStatement();
		        ResultSet rs = st.executeQuery("SELECT desc_membresia, precio_membresia FROM membresia where status_tm = 1");
		        while (rs.next()) {
		            lstM.add(new Membresia(rs.getString("desc_membresia"),rs.getDouble("precio_membresia")));
		                   
		        }
		    } catch (SQLException ex) {
		        Logger.getLogger(Membresia.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	 
	 
}
