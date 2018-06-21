package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DetalleDiaClaseEnt {
	private IntegerProperty clv_ddced;
	private Dias dia;
	private DetalleClaseEntrenador dce;
	private Time horaInicio;
	private Time horaFin;
	private IntegerProperty numDia;
	private DoubleProperty hora_inicio;
	private DoubleProperty hora_fin;
	private IntegerProperty d_ce;

	public DetalleDiaClaseEnt(int clv_ddced, Dias dia, DetalleClaseEntrenador dce, Time horaInicio, Time horaFin) {
		this.clv_ddced = new SimpleIntegerProperty(clv_ddced);
		this.dia = dia;
		this.dce = dce;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public DetalleDiaClaseEnt(int clv_ddced, int numDia, int d_ce, Time horaInicio, Time horaFin) {
		this.clv_ddced = new SimpleIntegerProperty(clv_ddced);
		this.numDia = new SimpleIntegerProperty(numDia);
		this.d_ce = new SimpleIntegerProperty(d_ce);
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	// Metodos atributo: clv_ddced
	public int getClv_ddced() {
		return clv_ddced.get();
	}

	public void setClv_ddced(int clv_ddced) {
		this.clv_ddced = new SimpleIntegerProperty(clv_ddced);
	}

	public IntegerProperty Clv_ddcedProperty() {
		return clv_ddced;
	}

	// Metodos atributo: dia
	public Dias getDia() {
		return dia;
	}

	public void setDia(Dias dia) {
		this.dia = dia;
	}

	// Metodos atributo: dce
	public DetalleClaseEntrenador getDce() {
		return dce;
	}

	public void setDce(DetalleClaseEntrenador dce) {
		this.dce = dce;
	}

	// Metodos atributo: horaInicio
	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	// Metodos atributo: horaFin
	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	// Metodos atributo: numDia
	public int getNumDia() {
		return numDia.get();
	}

	public void setNumDia(int numDia) {
		this.numDia = new SimpleIntegerProperty(numDia);
	}

	public IntegerProperty NumDiaProperty() {
		return numDia;
	}
	
	public DoubleProperty getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(DoubleProperty hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public DoubleProperty getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(DoubleProperty hora_fin) {
		this.hora_fin = hora_fin;
	}

	public int guardarDomingo(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT detalle_dcedia VALUES (DEFAULT,1,?,?,?)");
			consulta.setInt(1, dce.getClv_dce());
			consulta.setDouble(2, horaInicio.getTime());
			consulta.setDouble(3, horaFin.getTime());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Dieta.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
}
