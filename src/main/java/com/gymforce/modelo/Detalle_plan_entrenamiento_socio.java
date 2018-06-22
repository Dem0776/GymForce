package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gymforce.modelo.PlanEntrenamiento;
import com.gymforce.modelo.Socio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Detalle_plan_entrenamiento_socio{
	private IntegerProperty clv_dpes;
	private Socio rfc_socio;
	private PlanEntrenamiento clv_pe;

	public Detalle_plan_entrenamiento_socio(int clv_dpes, Socio rfc_socio, PlanEntrenamiento clv_pe) { 
		this.clv_dpes = new SimpleIntegerProperty(clv_dpes);
		this.rfc_socio = rfc_socio;
		this.clv_pe = clv_pe;
	}
	public Detalle_plan_entrenamiento_socio(Socio rfc_socio, PlanEntrenamiento clv_pe) { 
		this.rfc_socio = rfc_socio;
		this.clv_pe = clv_pe;
	}
	

	//Metodos atributo: clv_dpes
	public int getClv_dpes() {
		return clv_dpes.get();
	}
	public void setClv_dpes(int clv_dpes) {
		this.clv_dpes = new SimpleIntegerProperty(clv_dpes);
	}
	public IntegerProperty Clv_dpesProperty() {
		return clv_dpes;
	}
	//Metodos atributo: rfc_socio
	public Socio getRfc_socio() {
		return rfc_socio;
	}
	public void setRfc_socio(Socio rfc_socio) {
		this.rfc_socio = rfc_socio;
	}
	//Metodos atributo: clv_pe
	public PlanEntrenamiento getClv_pe() {
		return clv_pe;
	}
	public void setClv_pe(PlanEntrenamiento clv_pe) {
		this.clv_pe = clv_pe;
	}
	
	 public int guardar(Connection cn) {
	        try {
	            PreparedStatement consulta
	                    = cn.prepareStatement(
	                            "INSERT detalle_plan_entrenamiento_socio VALUES (null,?,?)");
	            consulta.setString(2, rfc_socio.getRfc_socio());
	            consulta.setInt(3, clv_pe.getClv_pe());
	            return consulta.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(Detalle_plan_entrenamiento_socio.class.getName()).log(Level.SEVERE, null, ex);
	            return 0;
	        }
	    }
}