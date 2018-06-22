package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Venta{
	private IntegerProperty clv_venta;
	private Date fecha_venta;
	private DoubleProperty total_venta;
	private Socio rfc_socio;

	
	
	public Venta() {
		
	}
	public Venta(int clv_venta, Date fecha_venta, Double total_venta, 
Socio rfc_socio) { 
		this.clv_venta = new SimpleIntegerProperty(clv_venta);
		this.fecha_venta = fecha_venta;
		this.total_venta = new SimpleDoubleProperty(total_venta);
		this.rfc_socio = rfc_socio;
	}

	//Metodos atributo: clv_venta
	public int getClv_venta() {
		return clv_venta.get();
	}
	public void setClv_venta(int clv_venta) {
		this.clv_venta = new SimpleIntegerProperty(clv_venta);
	}
	public IntegerProperty Clv_ventaProperty() {
		return clv_venta;
	}
	//Metodos atributo: fecha_venta
	public Date getFecha_venta() {
		return fecha_venta;
	}
	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}
	//Metodos atributo: total_venta
	public Double getTotal_venta() {
		return total_venta.get();
	}
	public void setTotal_venta(Double total_venta) {
		this.total_venta = new SimpleDoubleProperty(total_venta);
	}
	public DoubleProperty Total_ventaProperty() {
		return total_venta;
	}
	//Metodos atributo: rfc_socio
	public Socio getRfc_socio() {
		return rfc_socio;
	}
	public void setRfc_socio(Socio rfc_socio) {
		this.rfc_socio = rfc_socio;
	}
	
	public int guardarVenta(Connection cn,String rfc) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT venta VALUES (DEFAULT,NOW(),DEFAULT,'"+rfc+"',"+"DEFAULT)");
			//consulta.setString(1, desc_marca.get());
			
			
			
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	
}


