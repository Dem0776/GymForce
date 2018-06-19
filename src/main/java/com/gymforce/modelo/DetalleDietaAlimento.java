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
import javafx.collections.ObservableList;

public class DetalleDietaAlimento {
	private IntegerProperty clv_dda;
	private Dieta clv_dieta;
	private Alimento clv_alimento;
	private IntegerProperty dieta;

	public DetalleDietaAlimento(int clv_dda, Dieta clv_dieta, Alimento clv_alimento) {
		this.clv_dda = new SimpleIntegerProperty(clv_dda);
		this.clv_dieta = clv_dieta;
		this.clv_alimento = clv_alimento;
	}

	public DetalleDietaAlimento(int clv_dda, int dieta, Alimento clv_alimento) {
		this.clv_dda = new SimpleIntegerProperty(clv_dda);
		this.dieta = new SimpleIntegerProperty(dieta);
		this.clv_alimento = clv_alimento;
	}

	// Metodos atributo: clv_dda
	public int getClv_dda() {
		return clv_dda.get();
	}

	public void setClv_dda(int clv_dda) {
		this.clv_dda = new SimpleIntegerProperty(clv_dda);
	}

	public IntegerProperty Clv_ddaProperty() {
		return clv_dda;
	}

	// Metodos atributo: clv_dieta
	public Dieta getClv_dieta() {
		return clv_dieta;
	}

	public void setClv_dieta(Dieta clv_dieta) {
		this.clv_dieta = clv_dieta;
	}

	// Metodos atributo: clv_alimento
	public Alimento getClv_alimento() {
		return clv_alimento;
	}

	public void setClv_alimento(Alimento clv_alimento) {
		this.clv_alimento = clv_alimento;
	}

	// Metodos atributo: dieta
	public int getDieta() {
		return dieta.get();
	}

	public void setDieta(int dieta) {
		this.dieta = new SimpleIntegerProperty(dieta);
	}

	public IntegerProperty DietaProperty() {
		return dieta;
	}
	
	public static void lleanarTableDda(Connection conect, ObservableList<DetalleDietaAlimento> listaDda) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT " + "* "
					+ "FROM detalle_dieta_alimento "
					+ "JOIN alimento ON detalle_dieta_alimento.clv_alimento = alimento.clv_alimento "
					+ "JOIN dieta ON detalle_dieta_alimento.clv_dieta = dieta.clv_dieta "
					+ "WHERE dieta.clv_dieta = 1 ");			
			while (rs.next()) {
				listaDda.add(new DetalleDietaAlimento(rs.getInt("clv_dda"), 
						new Dieta(rs.getInt("clv_dieta"), rs.getString("nombre_dieta"), rs.getString("desc_dieta")), 
						new Alimento(rs.getInt("clv_alimento"), rs.getString("nombre_alimento"), rs.getString("desc_alimento"))));
				/*listaEjercicio.add(new Ejercicio(rs.getString("desc_ejercicio"), rs.getString("complejidad_ejercicio"),
						new Mobiliario(rs.getString("desc_mobiliario"))));*/
			}
		} catch (SQLException e) {
			Logger.getLogger(DetalleDietaAlimento.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public int guardarDetalleDietaAlim(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT detalle_dieta_alimento VALUES (DEFAULT,?,?)");
			consulta.setInt(1, clv_alimento.getClv_alimento());
			consulta.setInt(2, dieta.get());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(DetalleClaseEntrenador.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
}
