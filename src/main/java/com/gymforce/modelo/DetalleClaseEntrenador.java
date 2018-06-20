package com.gymforce.modelo;

import java.sql.Connection;
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

public class DetalleClaseEntrenador {
	private IntegerProperty clv_dce;
	private Empleado rfc_empleado;
	private SimpleIntegerProperty clv_clase;
	private DoubleProperty precio_clase;

	public DetalleClaseEntrenador(Empleado rfc_empleado,  int clv_clase, Double precio_clase) {
		this.rfc_empleado = rfc_empleado;
		this.clv_clase = new SimpleIntegerProperty(clv_clase);
		this.precio_clase = new SimpleDoubleProperty(precio_clase);
	}
	
	public DetalleClaseEntrenador(Double precio_clase) {
		this.precio_clase = new SimpleDoubleProperty(precio_clase);
	}

	// Metodos atributo: clv_dce
	public int getClv_dce() {
		return clv_dce.get();
	}

	public void setClv_dce(int clv_dce) {
		this.clv_dce = new SimpleIntegerProperty(clv_dce);
	}

	public IntegerProperty Clv_dceProperty() {
		return clv_dce;
	}

	// Metodos atributo: rfc_empleado
	public Empleado getRfc_empleado() {
		return rfc_empleado;
	}

	public void setRfc_empleado(Empleado rfc_empleado) {
		this.rfc_empleado = rfc_empleado;
	}

	// Metodos atributo: clv_clase
	public int getClv_clase() {
		return clv_clase.get();
	}
	public void setClv_clase(int clv_clase) {
		this.clv_clase = new SimpleIntegerProperty(clv_clase);
	}
	public IntegerProperty Clv_claseProperty() {
		return clv_clase;
	}

	// Metodos atributo: precio_clase
	public Double getPrecio_clase() {
		return precio_clase.get();
	}

	public void setPrecio_clase(Double precio_clase) {
		this.precio_clase = new SimpleDoubleProperty(precio_clase);
	}

	public DoubleProperty Precio_claseProperty() {
		return precio_clase;
	}
	
	@Override
    public String toString() {
        return String.valueOf(precio_clase.get());
    }

	public int guardarDetalleClaseEntr(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT detalle_clase_entrenador VALUES (DEFAULT,?,?,?)");
			consulta.setString(1, rfc_empleado.getRfc_empleado());
			consulta.setInt(2, clv_clase.get());
			consulta.setDouble(3, precio_clase.get());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(DetalleClaseEntrenador.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	public static int obtenerUltimoDetalleDce(Connection conect) {
		int clv = 0;
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT " + "detalle_clase_entrenador.clv_dce " + "FROM " 
			+ "detalle_clase_entrenador ORDER BY detalle_clase_entrenador.clv_dce DESC LIMIT 1");

			while (rs.next()) {
				clv = rs.getInt("clv_dce");
			}
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
		}
		return clv;
	}
}
