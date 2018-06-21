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

public class Dieta {
	private IntegerProperty clv_dieta;
	private StringProperty nombre_dieta;
	private StringProperty desc_dieta;
	private StringProperty status_dieta;
	private Alimento nombre_alimento;

	public Dieta(int clv_dieta, String nombre_dieta, String desc_dieta) {
		this.clv_dieta = new SimpleIntegerProperty(clv_dieta);
		this.nombre_dieta = new SimpleStringProperty(nombre_dieta);
		this.desc_dieta = new SimpleStringProperty(desc_dieta);
	}

	public Dieta(String nombre_dieta, String desc_dieta) {
		this.nombre_dieta = new SimpleStringProperty(nombre_dieta);
		this.desc_dieta = new SimpleStringProperty(desc_dieta);
	}

	public Dieta(int clv_dieta, String nombre_dieta, String desc_dieta, Alimento nombre_alimento) {
		this.clv_dieta = new SimpleIntegerProperty(clv_dieta);
		this.nombre_dieta = new SimpleStringProperty(nombre_dieta);
		this.desc_dieta = new SimpleStringProperty(desc_dieta);
		this.nombre_alimento = nombre_alimento;
	}
	
	public Dieta() {
		
	}

	// Metodos atributo: clv_dieta
	public int getClv_dieta() {
		return clv_dieta.get();
	}

	public void setClv_dieta(int clv_dieta) {
		this.clv_dieta = new SimpleIntegerProperty(clv_dieta);
	}

	public IntegerProperty Clv_dietaProperty() {
		return clv_dieta;
	}

	// Metodos atributo: nombre_dieta
	public String getNombre_dieta() {
		return nombre_dieta.get();
	}

	public void setNombre_dieta(String nombre_dieta) {
		this.nombre_dieta = new SimpleStringProperty(nombre_dieta);
	}

	public StringProperty Nombre_dietaProperty() {
		return nombre_dieta;
	}

	// Metodos atributo: desc_dieta
	public String getDesc_dieta() {
		return desc_dieta.get();
	}

	public void setDesc_dieta(String desc_dieta) {
		this.desc_dieta = new SimpleStringProperty(desc_dieta);
	}

	public StringProperty Desc_dietaProperty() {
		return desc_dieta;
	}

	public Alimento getNombre_alimento() {
		return nombre_alimento;
	}

	public void setNombre_alimento(Alimento nombre_alimento) {
		this.nombre_alimento = nombre_alimento;
	}

	// Metodos atributo: status_dieta
	public String getStatus_dieta() {
		return status_dieta.get();
	}

	public void setStatus_dieta(String status_dieta) {
		this.status_dieta = new SimpleStringProperty(status_dieta);
	}

	public StringProperty Status_dietaProperty() {
		return status_dieta;
	}
	
	@Override
    public String toString() {
        return String.valueOf(clv_dieta);
    }
	
	public static void llenarTableDieta(Connection conect, ObservableList<Dieta> listDieta) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT " + "* " + "FROM " + "detalle_dieta_alimento "
					+ "JOIN dieta ON detalle_dieta_alimento.clv_dieta = dieta.clv_dieta "
					+ "JOIN alimento ON detalle_dieta_alimento.clv_alimento = alimento.clv_alimento "
					+ "WHERE dieta.status_dieta = 1");

			while (rs.next()) {
				listDieta.add(new Dieta(rs.getInt("clv_dieta"),
						rs.getString("nombre_dieta"), 
						rs.getString("desc_dieta"),
						new Alimento(rs.getString("nombre_alimento"))));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Dieta.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int guardarDieta(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT dieta VALUES (DEFAULT,?,?,DEFAULT)");
			consulta.setString(1, nombre_dieta.get());
			consulta.setString(2, desc_dieta.get());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Dieta.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}

	public int cancelarDieta(Connection cn) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("UPDATE dieta SET " + "dieta.status_dieta = 0 " + "WHERE dieta.clv_dieta = ?");
			consulta.setInt(1, clv_dieta.get());

			return consulta.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Dieta.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}

	public static int obtenerUltimaDieta(Connection conect) {
		int clv = 0;
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT " + "dieta.clv_dieta " + "FROM " + "dieta ORDER BY dieta.clv_dieta DESC LIMIT 1");
			while (rs.next()) {
				clv = rs.getInt("clv_dieta");
			}
		} catch (SQLException ex) {
			Logger.getLogger(Dieta.class.getName()).log(Level.SEVERE, null, ex);
		}
		return clv;
	}
<<<<<<< HEAD
	@Override
    public String toString() {
        return nombre_dieta.get();
    }
	public static void llenarComboDieta(Connection conect, ObservableList<Dieta> listTipoDieta) {
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM dieta");
            while (rs.next()) {
            	listTipoDieta.add(
                        new Dieta(rs.getString("nombre_dieta"),
                                rs.getString("desc_dieta")
                        ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
=======

	public int actualizarDieta(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement(
					"UPDATE dieta SET " + "dieta.nombre_dieta = ?, " 
			+ "dieta.desc_dieta = ? "
			+ "WHERE dieta.clv_dieta = ? ");
			consulta.setString(1, nombre_dieta.get());
			consulta.setString(2, desc_dieta.get());			
			consulta.setInt(3, clv_dieta.get());
			return consulta.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(Dieta.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}

	public int eliminarDieta(Connection cn, int clave) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("UPDATE dieta SET " + "dieta.status_dieta = 0 " 
			+ "WHERE dieta.clv_dieta = ' " + clave  + "'");			
			return consulta.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(Dieta.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}
>>>>>>> fb756e37e437783a0d0b8c526a288d281dfc3315
}
