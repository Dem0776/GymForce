package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DetalleDietaAlimento{
	private IntegerProperty clv_dda;
	private IntegerProperty clv_dieta;
	private IntegerProperty clv_alimento;
	private int dieta;

	public DetalleDietaAlimento(int clv_dda, int clv_dieta, int clv_alimento) { 
		this.clv_dda = new SimpleIntegerProperty(clv_dda);
		this.clv_dieta = new SimpleIntegerProperty(clv_dieta);
		this.clv_alimento = new SimpleIntegerProperty(clv_alimento);
	}

	//Metodos atributo: clv_dda
	public int getClv_dda() {
		return clv_dda.get();
	}
	public void setClv_dda(int clv_dda) {
		this.clv_dda = new SimpleIntegerProperty(clv_dda);
	}
	public IntegerProperty Clv_ddaProperty() {
		return clv_dda;
	}
	//Metodos atributo: clv_dieta
	public int getClv_dieta() {
		return clv_dieta.get();
	}
	public void setClv_dieta(int clv_dieta) {
		this.clv_dieta = new SimpleIntegerProperty(clv_dieta);
	}
	public IntegerProperty Clv_dietaProperty() {
		return clv_dieta;
	}
	//Metodos atributo: clv_alimento
	public int getClv_alimento() {
		return clv_alimento.get();
	}
	public void setClv_alimento(int clv_alimento) {
		this.clv_alimento = new SimpleIntegerProperty(clv_alimento);
	}
	public IntegerProperty Clv_alimentoProperty() {
		return clv_alimento;
	}
	
	public int guardarDetalleDietaAlim(Connection cn) {
		/*try {
			PreparedStatement consulta = cn.prepareStatement("INSERT detalle_clase_entrenador VALUES (DEFAULT,?,?)");
			consulta.setInt(1, alimento.getClv_alimento());
			consulta.setInt(2, );			
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(DetalleClaseEntrenador.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}*/
		return 0;
	}
}
