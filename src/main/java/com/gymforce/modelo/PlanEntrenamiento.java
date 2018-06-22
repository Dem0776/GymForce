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

public class PlanEntrenamiento {
	private IntegerProperty clv_pe;
	private StringProperty desc_pe;
	private StringProperty duracion_pe;
	private StringProperty frecuencia_pe;
	private StringProperty dificultad_pe;
	private StringProperty status_pe;
	private Categoria clv_categoria;
	private Ejercicio ejercicio;
	private Rutina serie;
	private Rutina rep;

	public PlanEntrenamiento(int clv_pe, String desc_pe, String duracion_pe, String frecuencia_pe, String dificultad_pe,
			String status_pe, Categoria clv_categoria) {
		this.clv_pe = new SimpleIntegerProperty(clv_pe);
		this.desc_pe = new SimpleStringProperty(desc_pe);
		this.duracion_pe = new SimpleStringProperty(duracion_pe);
		this.frecuencia_pe = new SimpleStringProperty(frecuencia_pe);
		this.dificultad_pe = new SimpleStringProperty(dificultad_pe);
		this.status_pe = new SimpleStringProperty(status_pe);
		this.clv_categoria = clv_categoria;
	}
	public PlanEntrenamiento(String desc_pe) {
		this.desc_pe = new SimpleStringProperty(desc_pe);

	}
	public PlanEntrenamiento(int clv_pe, String desc_pe, String duracion_pe, String frecuencia_pe, String dificultad_pe,
			String status_pe, Categoria clv_categoria, Ejercicio ejercicio, Rutina serie) {
		this.clv_pe = new SimpleIntegerProperty(clv_pe);
		this.desc_pe = new SimpleStringProperty(desc_pe);
		this.duracion_pe = new SimpleStringProperty(duracion_pe);
		this.frecuencia_pe = new SimpleStringProperty(frecuencia_pe);
		this.dificultad_pe = new SimpleStringProperty(dificultad_pe);
		this.status_pe = new SimpleStringProperty(status_pe);
		this.clv_categoria = clv_categoria;
		this.ejercicio = ejercicio;
		this.serie = serie;		
	}
	
	public PlanEntrenamiento() {		
	}

	// Metodos atributo: clv_pe
	public int getClv_pe() {
		return clv_pe.get();
	}

	public void setClv_pe(int clv_pe) {
		this.clv_pe = new SimpleIntegerProperty(clv_pe);
	}

	public IntegerProperty Clv_peProperty() {
		return clv_pe;
	}

	// Metodos atributo: desc_pe
	public String getDesc_pe() {
		return desc_pe.get();
	}

	public void setDesc_pe(String desc_pe) {
		this.desc_pe = new SimpleStringProperty(desc_pe);
	}

	public StringProperty Desc_peProperty() {
		return desc_pe;
	}

	// Metodos atributo: duracion_pe
	public String getDuracion_pe() {
		return duracion_pe.get();
	}

	public void setDuracion_pe(String duracion_pe) {
		this.duracion_pe = new SimpleStringProperty(duracion_pe);
	}

	public StringProperty Duracion_peProperty() {
		return duracion_pe;
	}

	// Metodos atributo: frecuencia_pe
	public String getFrecuencia_pe() {
		return frecuencia_pe.get();
	}

	public void setFrecuencia_pe(String frecuencia_pe) {
		this.frecuencia_pe = new SimpleStringProperty(frecuencia_pe);
	}

	public StringProperty Frecuencia_peProperty() {
		return frecuencia_pe;
	}

	// Metodos atributo: dificultad_pe
	public String getDificultad_pe() {
		return dificultad_pe.get();
	}

	public void setDificultad_pe(String dificultad_pe) {
		this.dificultad_pe = new SimpleStringProperty(dificultad_pe);
	}

	public StringProperty Dificultad_peProperty() {
		return dificultad_pe;
	}

	// Metodos atributo: status_pe
	public String getStatus_pe() {
		return status_pe.get();
	}

	public void setStatus_pe(String status_pe) {
		this.status_pe = new SimpleStringProperty(status_pe);
	}

	public StringProperty Status_peProperty() {
		return status_pe;
	}

	// Metodos atributo: clv_categoria
	public Categoria getClv_categoria() {
		return clv_categoria;
	}

	public void setClv_categoria(Categoria clv_categoria) {
		this.clv_categoria = clv_categoria;
	}
	
	public Ejercicio getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

	public Rutina getSerie() {
		return serie;
	}

	public void setSerie(Rutina serie) {
		this.serie = serie;
	}

	public Rutina getRep() {
		return rep;
	}

	public void setRep(Rutina rep) {
		this.rep = rep;
	}

	public int guardarPlanE(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT plan_entrenamiento VALUES (DEFAULT,?,?,?,?,DEFAULT,?)");
			consulta.setString(1, desc_pe.get());
			consulta.setString(2, duracion_pe.get());
			consulta.setString(3, frecuencia_pe.get());
			consulta.setString(4, dificultad_pe.get());
			consulta.setInt(5, clv_categoria.getClv_categoria());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(PlanEntrenamiento.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	public static void llenarTablePlanE(Connection conect, ObservableList<PlanEntrenamiento> listPlan) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT " + "* " + "FROM " + "detalle_rutina_plane "
					+ "JOIN plan_entrenamiento ON detalle_rutina_plane.clv_pe = plan_entrenamiento.clv_pe "
					+ "JOIN rutina ON detalle_rutina_plane.clv_rutina = rutina.clv_rutina "
					+ "JOIN categoria ON plan_entrenamiento.clv_categoria = categoria.clv_categoria "
					+ "JOIN ejercicio ON rutina.clv_ejercicio = ejercicio.clv_ejercicio "
					+ "WHERE plan_entrenamiento.status_pe = 1");

			while (rs.next()) {
				listPlan.add(new PlanEntrenamiento(rs.getInt("clv_pe"), 
						rs.getString("desc_pe"),
						rs.getString("duracion_pe"), 
						rs.getString("frecuencia_pe"), 
						rs.getString("dificultad_pe"),
						rs.getString("status_pe"),
						new Categoria(rs.getInt("clv_categoria"), rs.getString("desc_categoria")),
						new Ejercicio(rs.getString("desc_ejercicio")),
						new Rutina(rs.getInt("serie_rutina"), 
								rs.getInt("repeticion_rutina"), 
								rs.getString("duracion_rutina"), 
								rs.getInt("peso_rutina"), 
								new Ejercicio("desc_ejercicio"))));
			}
		} catch (SQLException ex) {
			Logger.getLogger(PlanEntrenamiento.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static int obtenerUltimoPlan(Connection conect) {
		int clv = 0;
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT " + "plan_entrenamiento.clv_pe " + "FROM " + "plan_entrenamiento ORDER BY plan_entrenamiento.clv_pe DESC LIMIT 1");
			while (rs.next()) {
				clv = rs.getInt("clv_pe");
			}
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
		}
		return clv;
	}

	@Override
    public String toString() {
        return desc_pe.get();
    }
	public static void llenarComboPE(Connection conect, ObservableList<PlanEntrenamiento> listTipoPE) {
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM plan_entrenamiento");
            while (rs.next()) {
            	listTipoPE.add(
                        new PlanEntrenamiento(rs.getString("desc_pe"))
                        );
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanEntrenamiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

	
	public int actualizarPlanE(Connection cn) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("UPDATE plan_entrenamiento SET " 
			+ "plan_entrenamiento.desc_pe = ?, " 
			+ "plan_entrenamiento.duracion_pe = ?, "
			+ "plan_entrenamiento.frecuencia_pe = ?, " 
			+ "plan_entrenamiento.dificultad_pe = ?, "			
			+ "plan_entrenamiento.clv_categoria = ? " 
			+ "WHERE plan_entrenamiento.clv_pe = ?");
			consulta.setString(1, desc_pe.get());
			consulta.setString(2, duracion_pe.get());
			consulta.setString(3, frecuencia_pe.get());
			consulta.setString(4, dificultad_pe.get());
			consulta.setInt(5, clv_categoria.getClv_categoria());
			consulta.setInt(6, clv_pe.get());
			return consulta.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(PlanEntrenamiento.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}
	
	public int eliminarPlanE(Connection cn, int clave) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("UPDATE plan_entrenamiento SET " 
			+ "plan_entrenamiento.status_pe = 0 " 
			+ "WHERE plan_entrenamiento.clv_pe = ' " + clave  + "'");			
			return consulta.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(PlanEntrenamiento.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}
}
