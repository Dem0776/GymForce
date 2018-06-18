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

public class Categoria extends RecursiveTreeObject<Categoria> {
	private IntegerProperty clv_categoria;
	private StringProperty desc_categoria;
	private StringProperty status_categoria;

	public Categoria(int clv_categoria, String desc_categoria) {
		this.clv_categoria = new SimpleIntegerProperty(clv_categoria);
		this.desc_categoria = new SimpleStringProperty(desc_categoria);
	}

	public Categoria(String desc_categoria) {
		this.desc_categoria = new SimpleStringProperty(desc_categoria);
	}

	// Metodos atributo: clv_categoria
	public int getClv_categoria() {
		return clv_categoria.get();
	}

	public void setClv_categoria(int clv_categoria) {
		this.clv_categoria = new SimpleIntegerProperty(clv_categoria);
	}

	public IntegerProperty Clv_categoriaProperty() {
		return clv_categoria;
	}

	// Metodos atributo: desc_categoria
	public String getDesc_categoria() {
		return desc_categoria.get();
	}

	public void setDesc_categoria(String desc_categoria) {
		this.desc_categoria = new SimpleStringProperty(desc_categoria);
	}

	public StringProperty Desc_categoriaProperty() {
		return desc_categoria;
	}

	// Metodos atributo: status_categoria
	public String getStatus_categoria() {
		return status_categoria.get();
	}

	public void setStatus_categoria(String status_categoria) {
		this.status_categoria = new SimpleStringProperty(status_categoria);
	}

	public StringProperty Status_categoriaProperty() {
		return status_categoria;
	}
	
	@Override
    public String toString() {
        return desc_categoria.get();
    }

	public static void llenarTableCategoria(Connection conect, ObservableList<Categoria> listaCategoria) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT " + "categoria.desc_categoria " + "FROM " + "categoria "
					+ "WHERE categoria.status_categoria = 1");
			while (rs.next()) {
				listaCategoria.add(new Categoria(rs.getString("desc_categoria")));
			}
		} catch (SQLException e) {
			Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public static void llenarComboCategoria(Connection conect, ObservableList<Categoria> listCate) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM categoria " + "WHERE categoria.status_categoria = 1");
			while (rs.next()) {
				listCate.add(new Categoria(rs.getInt("clv_categoria"), rs.getString("desc_categoria")));

			}
		} catch (SQLException ex) {
			Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int guardarCategoria(Connection conect) {
		try {
			PreparedStatement consulta = conect.prepareStatement("INSERT categoria VALUES (DEFAULT,?,DEFAULT)");
			consulta.setString(1, desc_categoria.get());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
}
