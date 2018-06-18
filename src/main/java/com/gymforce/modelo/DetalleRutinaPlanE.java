package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DetalleRutinaPlanE{
	private IntegerProperty clv_detalleRPE;
	private PlanEntrenamiento clv_pe;
	private IntegerProperty clv_pent;	
	private Rutina clv_rutina;

	public DetalleRutinaPlanE(int clv_detalleRPE, PlanEntrenamiento clv_pe, Rutina clv_rutina) { 
		this.clv_detalleRPE = new SimpleIntegerProperty(clv_detalleRPE);
		this.clv_pe = clv_pe;
		this.clv_rutina = clv_rutina;
	}
	
	public DetalleRutinaPlanE(int clv_pent, Rutina clv_rutina) { 
		this.clv_pent = new SimpleIntegerProperty(clv_pent);;
		this.clv_rutina = clv_rutina;
	}

	//Metodos atributo: clv_detalleRPE
	public int getClv_detalleRPE() {
		return clv_detalleRPE.get();
	}
	public void setClv_detalleRPE(int clv_detalleRPE) {
		this.clv_detalleRPE = new SimpleIntegerProperty(clv_detalleRPE);
	}
	public IntegerProperty Clv_detalleRPEProperty() {
		return clv_detalleRPE;
	}
	//Metodos atributo: clv_pe
	public PlanEntrenamiento getClv_pe() {
		return clv_pe;
	}
	public void setClv_pe(PlanEntrenamiento clv_pe) {
		this.clv_pe = clv_pe;
	}
	//Metodos atributo: clv_rutina
	public Rutina getClv_rutina() {
		return clv_rutina;
	}
	public void setClv_rutina(Rutina clv_rutina) {
		this.clv_rutina = clv_rutina;
	}
	
	public IntegerProperty getClv_pent() {
		return clv_pent;
	}

	public void setClv_pent(IntegerProperty clv_pent) {
		this.clv_pent = clv_pent;
	}
	
	public int guardarDetalleRutinaPlanE(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT detalle_rutina_plane VALUES (DEFAULT,?,?)");
			consulta.setInt(1, clv_pent.get());
			consulta.setInt(2, clv_rutina.getClv_rutina());
			return consulta.executeUpdate();
		} catch (Exception e) {	
			Logger.getLogger(DetalleRutinaPlanE.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
}