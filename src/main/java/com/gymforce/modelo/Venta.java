package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Venta {
	private IntegerProperty clv_venta;
	private Date fecha_venta;
	private DoubleProperty total_venta;
	private Socio rfc_socio;

	public Venta(int clv_venta, Date fecha_venta, Double total_venta, Socio rfc_socio) {
		this.clv_venta = new SimpleIntegerProperty(clv_venta);
		this.fecha_venta = fecha_venta;
		this.total_venta = new SimpleDoubleProperty(total_venta);
		this.rfc_socio = rfc_socio;
	}

	// Metodos atributo: clv_venta
	public int getClv_venta() {
		return clv_venta.get();
	}

	public void setClv_venta(int clv_venta) {
		this.clv_venta = new SimpleIntegerProperty(clv_venta);
	}

	public IntegerProperty Clv_ventaProperty() {
		return clv_venta;
	}

	// Metodos atributo: fecha_venta
	public Date getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	// Metodos atributo: total_venta
	public Double getTotal_venta() {
		return total_venta.get();
	}

	public void setTotal_venta(Double total_venta) {
		this.total_venta = new SimpleDoubleProperty(total_venta);
	}

	public DoubleProperty Total_ventaProperty() {
		return total_venta;
	}

	// Metodos atributo: rfc_socio
	public Socio getRfc_socio() {
		return rfc_socio;
	}

	public void setRfc_socio(Socio rfc_socio) {
		this.rfc_socio = rfc_socio;
	}

	public int generarVenta(Connection cn) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("INSERT venta VALUES (DEFAULT,NOW(),0,'XAXXX10000',0)");
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	public static int obtenerNumeroVentas(Connection conect) {
		int noVenta = 0;
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT venta.clv_venta FROM venta ORDER BY venta.clv_venta DESC LIMIT 1");
			while (rs.next()) {
				noVenta = rs.getInt("venta.clv_venta");
			}
			return noVenta;
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}		
	}

}
