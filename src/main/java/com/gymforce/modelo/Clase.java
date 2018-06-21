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

public class Clase{
	private IntegerProperty clv_clase;
	private StringProperty nombre_clase;
	private StringProperty desc_clase;
	private StringProperty status_clase;
	private Empleado nombreInstructor;
	private DetalleClaseEntrenador precio;

	public Clase(String nombre_clase, String desc_clase) {
		this.nombre_clase = new SimpleStringProperty(nombre_clase);
		this.desc_clase = new SimpleStringProperty(desc_clase);
	}
	
	public Clase(int clv_clase, String nombre_clase, String desc_clase, Empleado nombreInstructor,
			DetalleClaseEntrenador precio) {
		this.clv_clase = new SimpleIntegerProperty(clv_clase);
		this.nombre_clase = new SimpleStringProperty(nombre_clase);
		this.desc_clase = new SimpleStringProperty(desc_clase);
		this.nombreInstructor = nombreInstructor;
		this.precio = precio;
	}
	
	public Clase(int clv_clase) {
		this.clv_clase = new SimpleIntegerProperty(clv_clase);		
	}
	
	public Clase() {		
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

	// Metodos atributo: nombre_clase
	public String getNombre_clase() {
		return nombre_clase.get();
	}

	public void setNombre_clase(String nombre_clase) {
		this.nombre_clase = new SimpleStringProperty(nombre_clase);
	}

	public StringProperty Nombre_claseProperty() {
		return nombre_clase;
	}

	// Metodos atributo: desc_clase
	public String getDesc_clase() {
		return desc_clase.get();
	}

	public void setDesc_clase(String desc_clase) {
		this.desc_clase = new SimpleStringProperty(desc_clase);
	}

	public StringProperty Desc_claseProperty() {
		return desc_clase;
	}

	// Metodos atributo: status_clase
	public String getStatus_clase() {
		return status_clase.get();
	}

	public void setStatus_clase(String status_clase) {
		this.status_clase = new SimpleStringProperty(status_clase);
	}

	public StringProperty Status_claseProperty() {
		return status_clase;
	}
	
	public Empleado getNombreInstructor() {
		return nombreInstructor;
	}

	public void setNombreInstructor(Empleado nombreInstructor) {
		this.nombreInstructor = nombreInstructor;
	}

	public DetalleClaseEntrenador getPrecio() {
		return precio;
	}

	public void setPrecio_clase(DetalleClaseEntrenador precio) {
		this.precio = precio;
	}
	
	public static void llenarTableClase(Connection conect, ObservableList<Clase> listClase) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT " + "clase.clv_clase, " + "clase.nombre_clase, " + "clase.desc_clase, "
					+ "empleado.rfc_empleado, " + "empleado.nombre_empleado, " + "detalle_clase_entrenador.precio_clase " + "FROM "
					+ "detalle_clase_entrenador " + "JOIN clase ON detalle_clase_entrenador.clv_clase = clase.clv_clase "
					+ "JOIN empleado ON detalle_clase_entrenador.rfc_empleado = empleado.rfc_empleado "
					+ "WHERE clase.status_clase = 1");

			while (rs.next()) {
				listClase.add(new Clase(rs.getInt("clv_clase"),
						rs.getString("nombre_clase"), 
						rs.getString("desc_clase"),
						new Empleado(rs.getString("rfc_empleado"), rs.getString("nombre_empleado")),
						//new Empleado(rs.getString("nombre_empleado")),
						new DetalleClaseEntrenador(rs.getDouble("precio_clase"))							
						));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int guardarClase(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT clase VALUES (DEFAULT,?,?,DEFAULT)");
			consulta.setString(1, nombre_clase.get());
			consulta.setString(2, desc_clase.get());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	public int cancelarClase(Connection cn) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("UPDATE clase SET " + "clase.status_clase = 0 " + "WHERE clase.clv_clase = ?");
			consulta.setInt(1, clv_clase.get());

			return consulta.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}

	public static int obtenerUltimaClase(Connection conect) {
		int clv = 0;
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT " + "clase.clv_clase " + "FROM " + "clase ORDER BY clase.clv_clase DESC LIMIT 1");

			while (rs.next()) {
				clv = rs.getInt("clv_clase");
			}
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
		}
		return clv;
	}
	@Override
    public String toString() {
        return desc_clase.get();
    }
	public static void llenarComboClase(Connection conect, ObservableList<Clase> listTipoClase) {
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clase");
            while (rs.next()) {
            	listTipoClase.add(
                        new Clase(rs.getString("nombre_clase"),
                                rs.getString("desc_clase")
                        ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public int actualizarClase(Connection cn) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("UPDATE clase SET " + "clase.nombre_clase = ?, " 
			+ "clase.desc_clase = ? " + "WHERE clase.clv_clase = ?");
			consulta.setString(1, nombre_clase.get());
			consulta.setString(2, desc_clase.get());
			consulta.setInt(3, clv_clase.get());
			return consulta.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}
	
	public int eliminarClase(Connection cn, int clave) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("UPDATE clase SET " + "clase.status_clase = 0 " 
			+ "WHERE clase.clv_clase = ' " + clave  + "'");			
			return consulta.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}
}
