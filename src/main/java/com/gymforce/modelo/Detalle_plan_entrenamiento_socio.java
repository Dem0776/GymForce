package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gymforce.modelo.PlanEntrenamiento;
import com.gymforce.modelo.Socio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

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
	                            "INSERT detalle_plan_entrenamiento_socio VALUES (DEFAULT,?,?)");
	            consulta.setString(1, rfc_socio.getRfc_socio());
	            consulta.setInt(2, clv_pe.getClv_pe());
	            return consulta.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(Detalle_plan_entrenamiento_socio.class.getName()).log(Level.SEVERE, null, ex);
	            return 0;
	        }
	    }
	 public static void llenarTableTipoEntre(Connection conect, ObservableList<Detalle_plan_entrenamiento_socio> listTipoE, String Socio) {
			try {
				Statement st = conect.createStatement();
				ResultSet rs = st.executeQuery("SELECT socio.rfc_socio, plan_entrenamiento.desc_pe FROM detalle_plan_entrenamiento_socio INNER JOIN plan_entrenamiento ON detalle_plan_entrenamiento_socio.clv_pe=plan_entrenamiento.clv_pe INNER JOIN socio ON detalle_plan_entrenamiento_socio.rfc_socio=socio.rfc_socio WHERE socio.rfc_socio='"+Socio+"'");

				while (rs.next()) {
					listTipoE.add(new Detalle_plan_entrenamiento_socio(
							new Socio(rs.getString("rfc_socio")),
							new PlanEntrenamiento(rs.getString("desc_pe"))));
				}
			} catch (SQLException ex) {
				Logger.getLogger(Detalle_plan_entrenamiento_socio.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	 
}